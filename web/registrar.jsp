<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String error = (String) request.getParameter("error"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrate</title>
</head>
<body>
<center>
<form action="/login/validar" method="POST">
	<label>Introduce un nombre de usuario</label>
	<input type="text" name="nameUser">
	<br/>
	<br/>
	<label>Introduce tu nombre</label>
	<input type="text" name="nombre">
	<br/>
	<br/>
	<label>Introduce un password<label/>
	<input type="password" name="password"/>
	<br/>
	<br/>
	<%if(error!=null){
		%>
			<p style="color: red"><%=error %></p>
			<br>
			<br>
		<%
	}	
		%>
	<input type="submit" name="button" value="Validar">
</form>
</center>
</body>
</html>