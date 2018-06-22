package shareinfo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.db.MySQLdb;

/**
 * Servlet implementation class delMsg
 */
@WebServlet("/delMsg")
public class delMsg extends HttpServlet {
	private MySQLdb mySQLdb;

	public void init(ServletConfig config) {
		System.out.println("---> Entering init() delMsg");

		mySQLdb = new MySQLdb();

		System.out.println("---> Exiting init() delMsg");
	}
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//recuperar sesión
				HttpSession session = request.getSession(false);
				//recuperar usuario logueado
				String username =(String) session.getAttribute("username");
				//eliminar mensajes de usuario
				mySQLdb.dropMsg(username);
				//establecer siguiente dirección
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/viewMessages.jsp");
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
