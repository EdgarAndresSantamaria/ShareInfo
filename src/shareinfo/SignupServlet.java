package shareinfo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import helper.db.*;

public class SignupServlet extends HttpServlet{
	
	private MySQLdb mySQLdb;
	
	public void init(final ServletConfig config) {
		System.out.println("---> Entering init() SignupServlet");
		
		mySQLdb = new MySQLdb();
		
		System.out.println("---> Exiting init() SignupServlet");
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	//recuperar información de usuario
    	String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		//recuperar sesión
		HttpSession session = request.getSession(false);
		if(session==null || session.getAttribute("username")==null) {
			//si no logueado
			//crear nuevo usuario
			mySQLdb.setUserInfo(email, password, nickname);
		}else {
			//si logueado
			//cambiar contraseña
			mySQLdb.updatepUsr(email, password, nickname);
		}
		//establecer redirección
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
		//redireccionar
		rd.forward(request, response);
		
    }
}

