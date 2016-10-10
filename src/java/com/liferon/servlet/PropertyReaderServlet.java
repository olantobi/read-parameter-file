/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferon.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ebenezer
 */
public class PropertyReaderServlet extends HttpServlet {
    private final Properties properties = new Properties();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean status = true;
        
        String fileName = "/WEB-INF/parameter.properties";
        ServletContext context = req.getServletContext();
        String fullPath = context.getRealPath(fileName);
        try (FileInputStream propsFile = new FileInputStream(fullPath)){                            
            properties.load(propsFile);                                   
            
        } catch(Exception __e) {
            __e.printStackTrace();
            status  = false;
        }
        
        String username = properties.getProperty("USERNAME");
        String password = properties.getProperty("PASSWORD");
        System.out.println("Username: "+username+" :: Password: "+password);
        PrintWriter out = resp.getWriter();
        out.println("Username: "+username);
        out.println("Password: "+password);
    }
        
}
