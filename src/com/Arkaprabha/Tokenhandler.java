package com.Arkaprabha;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

public class Tokenhandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tokenhandler() {
    	
        super();
        
        // TODO Auto-generated constructor stub
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		Connection conn = null;
		ResultSet rs,rsMain;
		int result = 0;
		

		String v_token = request.getParameter("voter_token");
		String candidate_sel = request.getParameter("selected_party");
		int rowCount = 0, rowCountMain = 0;
		
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinevotingsys", "root", "Arko1997");
			PreparedStatement psMain = conn.prepareStatement("select * from users where token = ?");
			psMain.setString(1,v_token);
			rsMain = psMain.executeQuery();
			while ( rsMain.next() )
			{
				rowCountMain++;
			}
				
			if(rowCountMain != 0) {
				PreparedStatement ps = conn.prepareStatement("select * from voted where voter_token = ?");
				ps.setString(1, v_token);
				rs = ps.executeQuery();
				
				while ( rs.next() )
				{
				    rowCount++;
				}
				
				if(rowCount==0) {
					PreparedStatement ps1 = conn.prepareStatement("insert into voted (voter_token, sel_candidate) values (?,?)");
					ps1.setString(1, v_token);
					ps1.setString(2, candidate_sel);
					result = ps1.executeUpdate();
					if(result == 1) {
						out.println("<!DOCTYPE html>");
						out.println("<html>");
						out.println("<head>");
						out.println("<title>Online Voting System</title>");
						out.println("</head>");
						
						out.println("<body>");
						out.println("<div style='text-align:center;transform:translateY(40vh);'>");
						out.println("<h1>You voted for candidate "+candidate_sel+", Thank you.</h1>");
						out.println("<a href='index.jsp'>Take me back to voting page</a>");
						out.println("</div>");
						
						out.println("</body>");
						out.println("</html>");
					}

				}else {
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Online Voting System</title>");
					out.println("</head>");
					
					out.println("<body>");
					out.println("<div style='text-align:center;transform:translateY(40vh);'>");
					out.println("<h1>Voter token already used to cast vote. No more voting allowed.</h1>");
					out.println("<a href='index.jsp'>Take me back to voting page</a>");
					out.println("</div>");
					
					out.println("</body>");
					out.println("</html>");
				}
				/*if(rs!=null) {
					 while(rs.next()) {
						 	String s = rs.getString("VOTER_TOKEN");
							String s1 = rs.getString("sel_candidate");
							out.println(s+" "+s1);
					 }
					
				}else {
					out.println("rs is null");
				}*/
			}else {
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Online Voting System</title>");
				out.println("</head>");
				
				out.println("<body>");
				out.println("<div style='text-align:center;transform:translateY(40vh);'>");
				out.println("<h1>Token not registered to any user. Register as a new user first.</h1>");
				out.println("<a href='register.jsp'>Register as a user</a><br><br>");
				out.println("<a href='index.jsp'>Take me back to voting page</a>");
				out.println("</div>");
				
				out.println("</body>");
				out.println("</html>");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 /*ps.setString(1, un);
		 ps.setString(2, pw);*/
	
	 
		
		
		/*if(v_token != null) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Online Voting System</title>");
			out.println("</head>");
			
			out.println("<body>");
			out.println("<div style='text-align:center;transform:translateY(40vh);'>");
			out.println("<h1>The voter token is "+v_token+" and candidate is "+candidate_sel+"</h1>");
			out.println("<a href='index.jsp'>Take me back to voting page</a>");
			out.println("</div>");
			
			out.println("</body>");
			out.println("</html>");
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
