package com.crudexample.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crudexample.db.DBConnection;
import com.crudexample.models.ISGUser;
import com.crudexample.models.MessageHandler;
import com.google.gson.Gson;

@WebServlet("/ISGServlet")
public class ISGServlet extends HttpServlet implements Servlet{
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String jsonString = "";
		String nameParam  = req.getParameter("name");
		if(nameParam != null){
			System.out.println("Name Param : " + nameParam);
			List<ISGUser> isgList = DBConnection.getAllISGUsers(nameParam);
			String isgJson = new Gson().toJson(isgList);
			jsonString = isgJson;
		}
	    	resp.setContentType("application/json");
	    	resp.setCharacterEncoding("UTF-8");
	    	resp.getWriter().write(jsonString);
	} 
	
	enum ISG{
		INSERT,
		DELETE,
		UPDATE
	};
	
	public String getMessageString(ISG isg){
		if(isg == ISG.DELETE)
			return "Deleted";
		else if(isg == ISG.INSERT)
			return "";
		else if(isg == ISG.UPDATE)
			return "Updated";
		else
			return "";
	}
	
	public String getMessage(int prm, ISG isg ){
		String msgPrm = getMessageString(isg);
		return ( prm !=0 ) ? "Record "+ msgPrm+ " Successfull": "Record Not " + msgPrm + " Successfull";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		MessageHandler msgHandler = new MessageHandler();

		String delParam = req.getParameter("delete");
		if(delParam != null){
			int res = DBConnection.deleteISGUser(delParam);
			msgHandler.setMessage(getMessage(res, ISG.DELETE));
			msgHandler.setCode(res);
		}
		
		String updParam = req.getParameter("update");
		if(updParam != null){
			String uname = req.getParameter("uname");
			if(uname != null){
				int res = DBConnection.updateISGUser(updParam, uname);
				msgHandler.setMessage(getMessage(res, ISG.UPDATE));
				msgHandler.setCode(res);
			}
		}
		
		String insParam = req.getParameter("insert");
		if(insParam != null){
			int res = DBConnection.insertISGUser(insParam);
			msgHandler.setMessage(getMessage(res, ISG.INSERT));
			msgHandler.setCode(res);
		}
		

		String json = new Gson().toJson(msgHandler);
		resp.setContentType("application/json");
	    	resp.setCharacterEncoding("UTF-8");
	    	resp.getWriter().write(json);
	}

	private static final long serialVersionUID = 9211521441180764083L;

}
