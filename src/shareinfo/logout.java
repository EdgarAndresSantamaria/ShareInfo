package shareinfo;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperar sesión
		HttpSession sesion = request.getSession();
		//recuperar usuario logueado
		String username =(String) sesion.getAttribute("username");	
		//destruir sesión
		sesion.invalidate();
		//recuperar información de contexto
		ServletContext context = request.getServletContext();
		HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
		//eliminar al usuario deslogueado
		loggedinUsers.remove(username);
		//añadir la nueva información de contexto
		context.setAttribute("loggedin_users", loggedinUsers);
		//añadir mensaje de error
		request.setAttribute("errormessage","su sesion se cerró");
		//añadir información de login
		request.setAttribute("email","");
		request.setAttribute("password","");
		//establecer siguiente redirección 
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
		//redireccionar
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
