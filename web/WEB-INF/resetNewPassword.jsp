<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Reset</title>
    </head>
    <body>
        <h1>Enter a new password</h1>

        <form action="reset" method="post">
            <input type="text" name="newPass" value="">
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="resetPass">
            <input type="hidden" name="uuid" value="${uuid}">
        </form>
    </body>
</html>
