package com.Arkaprabha;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addpartyhandler
 */
public class Addpartyhandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addpartyhandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int result;
		
		Connection conn= null;
		PrintWriter out = response.getWriter();

		String candidate = request.getParameter("candidate");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinevotingsys", "root", "Arko1997");
			PreparedStatement ps1 = conn.prepareStatement("insert into candidates (candidate) values (?)");
			ps1.setString(1, candidate);
			
			result = ps1.executeUpdate();
			
			if(result != 0) {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Admin Dashboard</title>");
				out.println("</head>");
				
				out.println("<body>");
				out.println("<div style='text-align:center;transform:translateY(40vh);'>");
				out.println("<h1>Candidate added successfully</h1>");
				out.println("<a href='admin_dashboard.jsp'>Take me back</a>");
				out.println("</div>");
				
				out.println("</body>");
				out.println("</html>");
			}
			
		}catch (SQLException e) {
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
