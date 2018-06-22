package shareinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class refreshUsers
 */
@WebServlet("/refreshUsers")
public class refreshUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public refreshUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperar el contexto
		ServletContext context = request.getServletContext();
		//recuperamos la lista de usuarios activos
		HashMap<String, String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");
		Collection<String> keys=new ArrayList<String>();
		keys=loggedinUsers.keySet();
		//generamos el json
		Gson gson = new Gson();
		String json = gson.toJson(keys);
		//escribimos el formato en la respuesta
		response.setContentType("application/json");
		//escribir json en la respuesta
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
