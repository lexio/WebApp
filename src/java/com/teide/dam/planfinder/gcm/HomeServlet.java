/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teide.dam.planfinder.gcm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam2
 */
public class HomeServlet extends BaseServlet {
    static final String ATTRIBUTE_STATUS = "status";
   
      /**
   * Displays the existing messages and offer the option to send a new one.
   */
  @Override //thi sis a resultof a get reqeust and we want send a paramter useing a post reqeust
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter(); //we get a writer to output a writer

    out.print("<html><body>");
    out.print("<head>");
    out.print("  <title>GCM Demo</title>");
    out.print("  <link rel='icon' href='favicon.png'/>");
    out.print("</head>");
    //req.setAttribute("MESSAGE","TEST MESSSAGE");
    String status = (String) req.getAttribute(ATTRIBUTE_STATUS);
    if (status != null) {
      out.print(status);
    }
    List<String> devices = Datastore.getDevices();
    if (devices.isEmpty()) {
      out.print("<h2>No devices registered!</h2>");
    } else {
      out.print("<H1>GCM DEMO SERVER</H1>");
      out.print("<h2>" + devices.size() + " device(s) registered!</h2>");
      out.print("<form name='form' method='POST' action='sendAll'>");
      out.print("<input type='submit' value='Send Regular Message' /><BR>");
      out.print("<input name='messageToSend' type='text' value='type a custom message here' />");
      out.print("</form><BR><BR>");
      out.print("<form name='form2' method='POST' action='sendPic'>");
      out.print("<input type='submit' value='Send Picture Message' /><BR>");
      out.print("<img src='pic_2.png'/><BR>");
      out.print("<input name='pictureToSend' type='hidden' value='LOADPIC' />");
      out.print("</form>");
      
    }
    out.print("</body></html>");
    req.setAttribute("MESSAGE","TEST MESSSAGE");
    /*
    try {
    req.getRequestDispatcher("/sendAll").forward(req,resp);
    }
    catch(Exception e) {

    }
    */
    resp.setStatus(HttpServletResponse.SC_OK);//qwe can set the status code of the rresponse by force thoruhg this method
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
     //   req.setAttribute("MESSAGE","TEST MESSSAGE");
    doGet(req, resp);
  }
}
