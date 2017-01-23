<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String error = (String) request.getParameter("error"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenido</title>
</head>
<body>
<center>
<form action="/login/validar">
	<label>Introduce tu nombre de usuario</label>
	<input type="text" name="nameUser">
	<br/>
	<br/>
	<label>Introduce tu password<label/>
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
	<input type="submit" name="button" value="Sing in">
	<input type="submit" name="button" value="Sing up">
</form>
</center>
</body>
</html>