package Java;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import java.sql.Statement;



@WebServlet(name="ValidaLogin", urlPatterns="/validar")
public class ValidaLogin extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataSource datSources;
	private Connection conDB;
	private Statement stat;
	
	public ValidaLogin(){
		super();
	}
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		ServletContext application =  config.getServletContext();
    	InitialContext initCtx;
		try {
			initCtx = new InitialContext();
			datSources = (DataSource) initCtx.lookup("java:jboss/datasources/logins");
			conDB = datSources.getConnection();
			stat = conDB.createStatement();
	        

		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String elementPulsado = request.getParameter("button");
		
		switch(elementPulsado){
		case("Sing in"):
				ValidarUser(request, response);
			break;
			
		case("Sing up"):
            request.getRequestDispatcher("registrar.jsp").forward(request, response);
			break;
			
		case("Validar"):
			Registrar(request, response);
			break;
		}
		
	}
	
	public void ValidarUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ResultSet result = null;
		String error="OK";
		String nombreUser = "";
		try {
			String user = request.getParameter("nameUser");
			String clave = request.getParameter("password");
			if(user != null && clave != null){
			result = stat.executeQuery("SELECT * FROM LOGIN WHERE login = '"+user+"'"+"AND clave = '"+clave+"'");
			
			if(result.next()){
				if(result.getString("clave").equals(clave)){
					if(!result.getString("login").equals(user)){
						error = "Usuario o password incorrecto";
					}else{
						nombreUser = result.getString("nombre");
					}
				}else{
					error = "Usuario o password incorrecto";
				}
				
			}else{
				error = "Usuario o password incorrecto";
			}
			}else{
				error = "Debes introducir un usuario y un password";	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "Usuario o password incorrecto";
		} catch(NullPointerException e){
			error = "Usuario o password incorrecto";
		}
	
		if(error.equals("OK")){
			PrintWriter out = response.getWriter();
			out.write("<html>");
			out.write("<head><title>Bienvenido</title></head>");
			out.write("<body><p>");
			out.write("<br>");
			out.write(" \nBienvenido "+nombreUser);
			out.write("</body>");
			out.write("</html>");
			out.close();
		}else{
			request.setAttribute("error", error);
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}
	
	public void Registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ResultSet result = null;
		String error="OK";
		String nombre = "";
		try {
			String user = request.getParameter("nameUser");
			nombre = request.getParameter("nombre");
			String clave = request.getParameter("password");
			if(user!= null && clave!=null && nombre != null){
			result = stat.executeQuery("SELECT login FROM LOGIN WHERE login = '"+user+"'");
			
			if(result.next()){
				if(!result.getString("login").equals(user)){
					result = stat.executeQuery("INSERT INTO login VALUES('"+user+"','"+clave+"',"+"'"+nombre+"'");
				}else{
					error = "El usuario introducido ya existe";
				}
				
			}
			}else{
				error = "Debes introducir todos los campos";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "Error en el registro";
		} catch(NullPointerException e){
			error = "Error en el registro";
		}
	
		if(error.equals("OK")){
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			request.setAttribute("error", error);
		    request.getRequestDispatcher("registrar.jsp").forward(request, response);
		}
		
	}
	
}
