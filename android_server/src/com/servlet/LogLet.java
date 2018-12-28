package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Service;

public class LogLet extends HttpServlet {

    private static final long serialVersionUID = 369840050351775312L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ���տͻ�����Ϣ
    	String username="";
        username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(username + "--" + password);
        // �½��������
        Service serv = new Service();
        
        // ������Ϣ���ͻ���
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
        
        
        // ��֤����
        boolean loged = serv.login(username, password);
        if (loged) {
            System.out.println("login Success");
            
            request.getSession().setAttribute("username", username);
            out.print("�û�����" + username);
            out.print("���룺" + password);
            out.flush();
            out.close();
            // response.sendRedirect("welcome.jsp");
        } else {
            System.out.println("login Failed");
            out.print("failed");
           
            out.flush();
            out.close();
           
        }

       

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}