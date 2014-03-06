/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.gcm;


import com.google.android.gcm.server.Message;

import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam2
 */
public class SendAllMessagesServlet extends BaseServlet {
     private static final int MULTICAST_SIZE = 1000;
     
  private Sender sender;

  private static final Executor threadPool = Executors.newFixedThreadPool(5);

  @Override
  public void init(ServletConfig config) throws ServletException {
      System.out.println("En el init");
    super.init(config);
    sender = newSender(config);
  }

  /**
   * Creates the {@link Sender} based on the servlet settings.
   */
  protected Sender newSender(ServletConfig config) {
      System.out.println("En el newSender");
    String key = (String) config.getServletContext()
        .getAttribute(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
      System.out.println("La key desde el apikeyinitializer:"+ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
      System.out.println("Esta es la key: "+key);
    return new Sender(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
  }

  /**
   * Processes the request to add a new message.
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
      System.out.println("Estoy en el doPost de enviar mensajes");
      ServletConfig config = getServletConfig();
      init(config);
    List<String> devices = Datastore.getDevices();
    String MESSAGE;
    if (req.getAttribute("msg")!=null) MESSAGE = req.getAttribute("msg").toString();
    else MESSAGE = (String)req.getParameter("messageToSend");
    if (MESSAGE == null)
      MESSAGE="NULL";
    
    String status = "";
    status = status + "The message is: " + MESSAGE + " ";
      System.out.println(status);
    if (devices.isEmpty()) {        
      status = "Message ignored as there is no device registered!";
        System.out.println(status);
    } else {
      // NOTE: check below is for demonstration purposes; a real application
      // could always send a multicast, even for just one recipient
      if (devices.size() == 1) {
        // send a single message using plain post
        String registrationId = devices.get(0).toString();
          System.out.println(registrationId);
          System.out.println("Voy a mandarlo a :"+registrationId);
         Message.Builder builder = new Message.Builder();
        //Here we decide what message and data to send
        builder.addData("message",MESSAGE);
        //end messsage decisions
        Message message = builder.build();
        Result result = sender.send(message, registrationId, 5);
        status = "Sent message to one device: " + result;
          System.out.println(status);
      } else {
          System.out.println("Tengo más de un dispositivo");
        // send a multicast message using JSON
        // must split in chunks of 1000 devices (GCM limit)
          System.out.println("Antes de antes");
        int total = devices.size();
          System.out.println("El total es: "+total);
        List<String> partialDevices = new ArrayList<String>(total);
        int counter = 0;
        int tasks = 0;
        for (String device : devices) {
            System.out.println(device);
          counter++;
          partialDevices.add(device);
          int partialSize = partialDevices.size();
          if (partialSize == MULTICAST_SIZE || counter == total) {
            asyncSend(partialDevices,MESSAGE);
            partialDevices.clear();
            tasks++;
          }
        }
        status = "Asynchronously sending " + tasks + " multicast messages to " +
            total + " devices";
          System.out.println("Este es el status:"+status);
      }
    }
    req.setAttribute(HomeServlet.ATTRIBUTE_STATUS, status.toString()); //why are we dispatchign the request?  doesnt it automactially handle it or do we have to do it every time we ant to send a apramter between servelets?
    getServletContext().getRequestDispatcher("/home").forward(req, resp); //what is goign on with this by the way why is it sending this to home,is it sesnindg it toth ehome servelt?
  }

  private void asyncSend(List<String> partialDevices,final String MESSAGE) {
    // make a copy
    final List<String> devices = new ArrayList<String>(partialDevices);
    threadPool.execute(new Runnable() {

      public void run() {
        Message.Builder builder = new Message.Builder();
        //Here we decide what message and data to send
        builder.addData("message",MESSAGE);
        //end messsage decisions
        Message message = builder.build();
        
        MulticastResult multicastResult;
        try {
          multicastResult = sender.send(message, devices, 5);
        } catch (IOException e) {
          logger.log(Level.SEVERE, "Error posting messages", e);
          return;
        }
        List<Result> results = multicastResult.getResults();
        // analyze the results
        for (int i = 0; i < devices.size(); i++) {
          String regId = devices.get(i);
          Result result = results.get(i);
          String messageId = result.getMessageId();
          if (messageId != null) {
            logger.fine("Succesfully sent message to device: " + regId +
                "; messageId = " + messageId);
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId != null) {
              // same device has more than on registration id: update it
              logger.info("canonicalRegId " + canonicalRegId);
              Datastore.updateRegistration(regId, canonicalRegId);
            }
          } else {
            String error = result.getErrorCodeName();
            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
              // application has been removed from device - unregister it
              logger.info("Unregistered device: " + regId);
              Datastore.unregister(regId);
            } else {
              logger.severe("Error sending message to " + regId + ": " + error);
            }
          }
        }
      }});
  }

}
