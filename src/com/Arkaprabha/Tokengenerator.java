package com.Arkaprabha;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class Tokengenerator
 */
public class Tokengenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tokengenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

    String randomizer(int size) {
    	final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	StringBuilder builder = new StringBuilder();
    	while (size-- != 0) {
    	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
    		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    	}

    	return builder.toString();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn = null;
		int result;
		PrintWriter out = response.getWriter();

		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String aadhar = request.getParameter("aadhar");
		
		String unique_token = randomizer(10);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinevotingsys", "root", "Arko1997");
			
			PreparedStatement ps = conn.prepareStatement("insert into users (name, email, age, gender, adharNum,token) values (?, ?, ?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, age);
			ps.setString(4, gender);
			ps.setString(5, aadhar);
			ps.setString(6, unique_token);
			result= ps.executeUpdate();
			
			if(result != 0) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Online Voting System</title>");
				out.println("</head>");
				
				out.println("<body>");
				out.println("<div style='text-align:center;transform:translateY(40vh);'>");
				out.println("<h2>Your unique voter token:</h2>");
				out.println("<h1>"+unique_token+"</h1>");
				out.println("<a href='index.jsp'>Take me back to voting page</a>");
				out.println("</div>");
				
				out.println("</body>");
				out.println("</html>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
