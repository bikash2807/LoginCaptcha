package com.logincaptecha.samplewar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaServlet extends HttpServlet {
	
	static Logger log = Logger.getAnonymousLogger();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("******check before create session*******");
    	
    	HttpSession ss = request.getSession(); 
    	
    	String captch = (String)ss.getAttribute("captcha");
    	
    	log.info("******Check captcha****************"+captch);
    	
    	System.out.println(" *********************** check after create session ************");
    	
        int width = 150;
        int height = 50;
        List arrayList = new ArrayList();
        
        String capcode = "abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMONOPQURSTUVWXYZ0123456789!@#$%&*";
        
        for (int i = 1; i < capcode.length() - 1; i++) {
            arrayList.add(capcode.charAt(i));
        }
        
        Collections.shuffle(arrayList);
        Iterator itr = arrayList.iterator();
        String s = "";
        String s2 = "";
        Object obj;
        
        while (itr.hasNext()) {
            obj = itr.next();
            s = obj.toString();
            s2 = s2 + s;
        }
        
        String s1 = s2.substring(0, 6);
        char[] s3 = s1.toCharArray();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        GradientPaint gp = new GradientPaint(0, 0, Color.red, 0, height / 2, Color.black, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(255, 153, 0));
        Random r = new Random();
        int index = Math.abs(r.nextInt()) % 5;
        String captcha = String.copyValueOf(s3);
        
        log.info("*********Random generated CAptecha string when application  is start : "+ captcha);
        //request.getSession().setAttribute("captcha", captcha);
        ss.setAttribute("captcha", captcha);
        log.info("*************************************sessionid in Captacha Servlet : "+request.getSession().getId());
        
        int x = 0;
        int y = 0;
        for (int i = 0; i < s3.length; i++) {
            x += 10 + (Math.abs(r.nextInt()) % 15);
            y = 20 + Math.abs(r.nextInt()) % 20;
            g2d.drawChars(s3, i, 1, x, y);
        }
        g2d.dispose();
        response.setContentType("image/png");
        
        OutputStream os = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        os.flush();
        os.close();
        System.out.println("  *************************************  Check session in CaptchaServlet "+ss);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
