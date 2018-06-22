package shareinfo;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class MySessionListener implements HttpSessionListener {
 
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("     A session is being created");
	}
 
	public void sessionDestroyed(HttpSessionEvent event) {
		//recuperar sesión
		HttpSession session = event.getSession();
		//recuperar id sesion
		String sessionID = session.getId();
		//recuperar contexto de sesión
		ServletContext context = session.getServletContext();
		//recuperar usuarios logueados
		HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
		//buscar usuario de la sesión caducada
		for(Map.Entry<String, String> entry : loggedinUsers.entrySet()) {
            if(entry.getValue().equals(sessionID)) {
            	//eliminar usuario cuya sesión caducó
            	loggedinUsers.remove(entry.getKey());
            	//añadir nueva información de contexto
            	context.setAttribute("loggedin_users", loggedinUsers);
            	break;
            }
		}
	}
 
}

 