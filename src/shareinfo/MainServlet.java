package shareinfo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import helper.db.*;
import helper.info.*;

public class MainServlet extends HttpServlet{

	private MySQLdb mySQLdb;
	
	public void init(ServletConfig config) {
		mySQLdb = new MySQLdb();
	}

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	//recuperar sesión
    	HttpSession session = request.getSession(false);
    	if(session == null) {
    		//si no existe sesión.
    		//añadir mensaje de error
    		request.setAttribute("errormessage","su sesion caduco");
    		//añadir información de login (vacio)
    		request.setAttribute("email","");
			request.setAttribute("password","");
			//establecer redirección
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			//redireccionar
			rd.forward(request, response);
    	} else {
    		//si esta logueado..
    		//recuperar mensaje
    		String message = request.getParameter("message");
    		String username="";
    		if(message != null) {	
    			//si existe mensaje
    			//recuperar usuario de sesión
    			username =(String)session.getAttribute("username");		
    		}
    		//añadir mensaje
    		mySQLdb.setMessageInfo(message, username);
    		//establecer redirección
    		RequestDispatcher rd = request.getRequestDispatcher("/jsp/viewMessages.jsp");
    		//redireccionar
    		rd.forward(request, response);
    	}
    }

}

