package shareinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import helper.info.MessageInfo;

/**
 * Servlet implementation class myUser
 */
@WebServlet("/myUser")
public class myUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myUser() {
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
		//generamos el json
		Gson gson = new Gson();
		String json = gson.toJson(username);
		//escribimos el formato en la respuesta
		response.setContentType("application/json");
		//añadir el json a la respuesta
		PrintWriter out= response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
