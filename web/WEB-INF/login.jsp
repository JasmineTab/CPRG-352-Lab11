<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Notes</title>
    </head>
    <body>
        <h1>Notes App</h1>
        <h2>Login</h2>
        <form action="login" method="post">
            email: <input type="text" name="email" value="${email}"><br>
            password: <input type="password" name="password"><br>
            <input type="submit" value="Sign in">
            <a href="reset">Forgot Password?</a>
        </form>
            
            <p>${message}</p>
    </body>
</html>
