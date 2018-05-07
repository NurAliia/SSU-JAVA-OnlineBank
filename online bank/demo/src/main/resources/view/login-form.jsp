<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Login page</title>
    <style>
        .error {
            color: red;
        }
        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>Login page</h1>

<p>
    <c:if test="${error == true}">
        <b class="error">Invalid login or password.</b>
    </c:if>
</p>

<form method="post" action="/login" method="post">
    <table>
        <tbody>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="j_username" id="j_username" size="30" maxlength="40"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password" id="j_password" size="30" maxlength="32"/></td>
        </tr>
        <tr>
            <td style="width: 50%;"><input type="submit" value="Login" style="width: 100%"/></td>
            <td style="width: 50%;"><button style="width: 100%;"><a href="/registration">Sign up</a></button></td>
        </tr>
        </tbody>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <csrf disabled="true"/>
    </table>
</form>
</body>
</html>