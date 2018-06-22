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

import helper.db.MySQLdb;
import helper.info.MessageInfo;

/**
 * Servlet implementation class refreshMsg
 */
@WebServlet("/refreshMsg")
public class refreshMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MySQLdb mySQLdb = new MySQLdb();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public refreshMsg() {
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
		ArrayList<MessageInfo> messageList = mySQLdb.getAllMessages();
		//generamos el json
		Gson gson = new Gson();
		String json = gson.toJson(messageList);
		//escribimos el formato en la respuesta
		response.setContentType("application/json");
		//escribir json en la respuestas
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
