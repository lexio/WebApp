/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.gcm;

import com.google.android.gcm.server.Message;

import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.teide.dam.planfinder.bean.MensajeBean;
import com.teide.dam.planfinder.dao.GrupoDAO;
import com.teide.dam.planfinder.dao.PerteneceDAO;
import com.teide.dam.planfinder.dao.UsuarioDAO;
import com.teide.dam.planfinder.pojos.Grupo;
import com.teide.dam.planfinder.pojos.Pertenece;
import com.teide.dam.planfinder.pojos.Usuario;
import com.teide.dam.planfinder.util.Estados;
import com.teide.dam.planfinder.util.HibernateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.classic.Session;

/**
 *
 * @author dam2
 */
public class SendAllMessagesServlet extends BaseServlet {

    private static final int MULTICAST_SIZE = 1000;
    private Sender sender;
    private static final Executor threadPool = Executors.newFixedThreadPool(5);
    private String eliminado = "NOK";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("Estoy en el doPost de enviar mensajes");
        ServletConfig config = getServletConfig();
        ArrayList<Usuario> devices = new ArrayList<>();
        String MESSAGE;
        if (req.getAttribute("msg") != null) {
            MESSAGE = req.getAttribute("msg").toString();
            req.setAttribute("msg", null);
        } 
        else if (req.getAttribute("msgB") != null){
            System.out.println("Entro aqui");
            MESSAGE = req.getAttribute("msgB").toString();
            req.setAttribute("msgB", null);
            eliminado = "OK";
        }
        else {
            MESSAGE = (String) req.getParameter("messageToSend");
        }
        if (MESSAGE == null) {
            MESSAGE = "NULL";
        }
        String idGrupo;
        if (req.getAttribute("idGrupoC") != null) {
            idGrupo = req.getAttribute("idGrupoC").toString();
            req.setAttribute("idGrupoC", null);
        }
        else if (req.getAttribute("idGrupoB") != null){
            idGrupo = req.getAttribute("idGrupoB").toString();
            req.setAttribute("idGrupoB", null);
            
        }
        else {
            idGrupo = req.getParameter("idGrupo").toString();
        }
        String sim;
        if (req.getAttribute("simC") != null) {
            sim = req.getAttribute("simC").toString();
            req.setAttribute("simC", null);
        }
        else if (req.getAttribute("simB") != null){
            sim = req.getAttribute("simB").toString();
            req.setAttribute("simB", null);
        }
        else {
            sim = req.getParameter("sim").toString();
        }
        System.out.println("La sim es: "+sim+", el idGrupo es: "+idGrupo+", el mensaje es: "+MESSAGE);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        GrupoDAO gDAO = new GrupoDAO(session);
        Grupo g = gDAO.comprobarGrupo(idGrupo);
        PerteneceDAO pDAO = new PerteneceDAO(session);
        ArrayList<Pertenece> usuarios = pDAO.devolverPersonasGrupo(idGrupo);
        UsuarioDAO uDAO = new UsuarioDAO(session);

        Usuario uc = uDAO.comprobarUsuario(sim);
        for (Pertenece pertenece : usuarios) {
            System.out.println("Esta es la sim:" + pertenece.getUsuario().getSim() + "hasta aqui");
            if (eliminado.equals("OK")){
                Usuario u = uDAO.comprobarUsuario(pertenece.getUsuario().getSim());
                devices.add(u);
            }
            else if (pertenece.getEstado().equals(Estados.ACEPTADO)){
                Usuario u = uDAO.comprobarUsuario(pertenece.getUsuario().getSim());
                devices.add(u);
            }           
        }

        String status = "";
        status = status + "The message is: " + MESSAGE + " ";
        System.out.println(status);
        System.out.println("El tamaño de devices es :"+devices.size());
        if (devices.isEmpty()||devices.size()==0) {
            status = "Message ignored as there is no device registered!";
            System.out.println(status);
        } else {
            // NOTE: check below is for demonstration purposes; a real application
            // could always send a multicast, even for just one recipient
            MensajeBean mb = new MensajeBean();
            mb.setIdGrupo(idGrupo);
            mb.setMensaje(MESSAGE);
            mb.setDescripcion(g.getDescripcion());
            mb.setEliminado(eliminado);
            if (mb.getDescripcion() == null) {
                mb.setDescripcion("");
            }
            GregorianCalendar fecha = new GregorianCalendar();
            mb.setFecha(fecha.getTime());
            mb.setNombreGrupo(g.getNombre());
            mb.setUsuario(uc.getNombre());
            Gson json = new Gson();
            String resultado = json.toJson(mb);
            System.out.println(resultado);
            if (devices.size() == 1) {
                // send a single message using plain post
//                String registrationId = devices.get(0).getClaveGcm();
//                System.out.println(registrationId);
//                System.out.println("Voy a mandarlo a :" + registrationId);
//                Message.Builder builder = new Message.Builder();
//                //Here we decide what message and data to send
//                builder.addData("message", MESSAGE);
//                //end messsage decisions
//                Message message = builder.build();
//                Result result = sender.send(message, registrationId, 5);
//                status = "Sent message to one device: " + result;
//                System.out.println(status);
            } else {
                System.out.println("Tengo más de un dispositivo");
                // send a multicast message using JSON
                // must split in chunks of 1000 devices (GCM limit)
                System.out.println("Antes de antes");
                int total = devices.size();
                System.out.println("El total es: " + total);
                List<String> partialDevices = new ArrayList<String>(total);
                int counter = 0;
                int tasks = 0;
                for (Usuario device : devices) {
                    System.out.println("He dado una vuelta");
                    System.out.println(device.getClaveGcm());
                    counter++;
                    if (!uc.getClaveGcm().equals(device.getClaveGcm())) {
                        if (!uc.getEstado().equals(Estados.INVISIBLE)){ 
                             partialDevices.add(device.getClaveGcm());
                        }
                    }
                   
                    int partialSize = partialDevices.size();
                if (partialSize == MULTICAST_SIZE || counter == total) {
                        asyncSend(partialDevices, resultado);
                        partialDevices.clear();
                        tasks++;
                    }
                }
                status = "Asynchronously sending " + tasks + " multicast messages to "
                        + total + " devices";
                System.out.println("Este es el status:" + status);
            }            
            eliminado = "NOK";
        }
        
        req.setAttribute(HomeServlet.ATTRIBUTE_STATUS, status.toString()); //why are we dispatchign the request?  doesnt it automactially handle it or do we have to do it every time we ant to send a apramter between servelets?
//        getServletContext().getRequestDispatcher("/home").forward(req, resp); //what is goign on with this by the way why is it sending this to home,is it sesnindg it toth ehome servelt?
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("En el init");
        super.init(config);
        System.out.println("La key desde el apikeyinitializer:" + ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);
        sender = new Sender(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY);

    }

    private void asyncSend(List<String> partialDevices, final String MESSAGE) {
        // make a copy
        final List<String> devices = new ArrayList<String>(partialDevices);
        threadPool.execute(new Runnable() {
            public void run() {
                Message.Builder builder = new Message.Builder();
                //Here we decide what message and data to send
                builder.addData("message", MESSAGE);
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
                        logger.fine("Succesfully sent message to device: " + regId
                                + "; messageId = " + messageId);
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
            }
        });
    }
}
