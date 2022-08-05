<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Reset Request</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p>
        
        <form action="reset" method="post">
            Email Address: <input type="text" name="emailReset">
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="sendReset">
        </form>
        
    </body>
</html>
