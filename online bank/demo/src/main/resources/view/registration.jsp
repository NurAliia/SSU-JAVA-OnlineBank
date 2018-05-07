<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 27.10.2017
  Time: 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>

<form name="loginform" action="/registration" method="post">
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <td>Login:   </td>
            <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password' /></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type='adr' name='j_ard' /></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type='number' name='j_phone' /></td>
        </tr>

        <tr>
            <td colspan='2'>
                <input name="Enter" type="submit" value="Enter"/>
            </td>
        </tr>
    </table>

    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
</form>

</body>
</html>
