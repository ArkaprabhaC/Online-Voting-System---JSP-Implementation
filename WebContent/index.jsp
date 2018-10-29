<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset- UTF-8">
<title>Online Voting System</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
	<h1 class="mt-5" style="text-align:center;">Hello Voter!</h1>
	<p style="text-align:center;">Please enter voter token and select the party to vote</p>
	
	<%
		    try{
		    	Class.forName("com.mysql.cj.jdbc.Driver");
		       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinevotingsys", "root", "Arko1997");
		       Statement statement = conn.createStatement() ;
		
		       resultset =statement.executeQuery("select * from candidates") ;
		%>
	
	<form action="Tokenhandler" method="POST">
		 <div class="form-group col-3 mx-auto" style="text-align:center;">
		   <label>Enter voter token:</label>
		   <input type="text" name="voter_token" class="form-control" >
		 </div>
	
	
		<div class="form-group col-3 mx-auto" style="text-align:center;">
		    <label>Choose party to cast vote to:</label>
		    <select class="form-control" name="selected_party">
			      <%  while(resultset.next()){ %>
			            <option><%= resultset.getString("candidate")%></option>
			        <% } %>
		    </select>
		</div>
		<%
			//**Should I input the codes here?**
			        }
			        catch(Exception e)
			        {
			             out.println("wrong entry"+e);
			        }
			%>
		
		<div style="text-align:center;">
			<input  class="btn btn-primary" type="submit" value="submit"/>
		</div>
	
	</form>
	
	<div style="text-align:center;transform:translateY(10vh);">
		<p>If you're a new voter and haven't got your voter token yet, register here</p>
		<a href="register.jsp">Register</a> 
	</div>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
</html>