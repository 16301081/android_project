package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.info.Trainer_info;
import com.service.Service;

/**
 * Servlet implementation class Trainer
 */

public class Trainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Service s =new Service();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trainer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Trainer_info> trainer_list=s.get_trainers();
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		for(Trainer_info t2:trainer_list){
			json.append('{').append("\"name\":").append("\""+t2.getName()+"\"").append(",");                      
            json.append("\"motto\":").append("\""+t2.getMotto()+"\"").append(",");
            json.append("\"intoduce\":").append("\""+t2.getIntroduce()+"\"");
            json.append('}').append(",");
		}
//		for(Trainer_info t2:trainer_list){
//			json.append('{').append("name:").append(t2.getName()).append(",");                      
//            json.append("motto:").append(t2.getMotto()).append(",");
//            json.append("intoduce:").append(t2.getIntroduce());
//            json.append('}').append(",");
//		}
		
		
		
		json.deleteCharAt(json.length()-1);//删除最后一个逗号
		json.append("]");
//        request.setAttribute("json", json.toString());
//        request.getRequestDispatcher("/1.jsp").forward(request, response);



		
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        
//        
//        
        out.print(json);
        out.flush();
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
