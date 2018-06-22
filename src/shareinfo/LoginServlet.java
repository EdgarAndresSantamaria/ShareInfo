package shareinfo;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import helper.db.*;

public class LoginServlet extends HttpServlet{

	private MySQLdb mySQLdb;
	
	public void init(ServletConfig config) {
		mySQLdb = new MySQLdb();
	}
 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	//recuperar datos de inicio
    	String email = request.getParameter("email");
		String password = request.getParameter("password");
		//comprobar login
		String username = mySQLdb.getUsername(email, password);
		if(username == null) {
			//si no existe
			//establecer mensaje de error
			request.setAttribute("errormessage","la identificacion fue insatisfactoria");
			//devolver parámetros
			request.setAttribute("email",email);
			request.setAttribute("password",password);
			//establecer redirección a login
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
			//redireccionar
			rd.forward(request, response);
		} else {
			//generar sesión
			HttpSession session = request.getSession(true);
			String sessionID = session.getId();		
			//añadir el nombre de usuario actual como variable de sesion
			session.setAttribute("username", username);
			session.setAttribute("email", email);
			//recuperar información de contexto
			ServletContext context = request.getServletContext();
			//cargar lista de usuarios activos
			HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
			if(loggedinUsers == null) {
				//si vacia..
				//añadir al actual usuario
				loggedinUsers = new HashMap();
				loggedinUsers.put(username, sessionID);
			} else {
				//si no vacia...
				if(!loggedinUsers.containsKey(username)) {
					//si no está añadido..
					//añadir al usuario recien logueado
					loggedinUsers.put(username, sessionID);
				} 
			}
			//añadir la nueva información de contexto
			context.setAttribute("loggedin_users", loggedinUsers);
			//establecer siguiente redirección
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/viewMessages.jsp");
			//redireccionar
			rd.forward(request, response);	
		}
    }
}
