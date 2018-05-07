<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html s xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>OBank: welcome page</title>

    <style>
        body {
            width: 1600px;
            margin: auto;
            background-color: white;
        }
        header {
            width: 100%;
        }
        .div-style {
            border-radius: 2px;
            background-color: white;
            padding: 5px;
        }
        .content {
            width: 100%;
            white-space: nowrap;
        }
        .cnt-elem {
            display: inline-block;
        }
        .menu {
            width: 20%;
            float: left;
        }
        .info {
            width: 75%;
            float: right;
            margin-top: 16px;
        }
        a {
            display: block;
            text-decoration: none;
            color: black;
            font-family: "Arial Black", sans-serif;
            font-size: 15px;
            height: 25px;
            border: 2px solid;
            margin-bottom: 3px;
        }
        a:hover {
            color: red;
        }

        table {
            border: 2px solid black;
            width: 100%;
        }
        tr {
            border: 2px solid black;
        }
        td {
            text-align: center;
        }
        .field-name {
            width: 20%;
        }
        .current-value {
            width: 40%
        }
        .new-value {
            width: 40%;
        }

    </style>
</head>
<body>
<header>
    <h1>Welcome [${username}] to OnlineBank!</h1>
</header>

<div class="content">
    <div class="menu cnt-elem div-style">
        <ul>
            <a href="/success-login">
                <li>Show bank accounts</li>
            </a>
            <a href="/changepersonaldata">
                <li>Change personal data</li>
            </a>
            <a href="/addbankaccount">
                <li>Add bank account</li>
            </a>
            <a href="/deletebankaccount">
                <li>Delete bank account</li>
            </a>
            <a href="/moneyoperation">
                <li>Do money transfer</li>
            </a>
            <c:if test="${isAdmin}">
                <a href="/rollbacktransaction">
                    <li>Rollback money transfer</li>
                </a>
            </c:if>
            <a href="/logout">
                <li>Logout</li>
            </a>
        </ul>
    </div>
    <div class="info cnt-elem div-style">
        <form action="/changepersonaldata" method="post">
            <table>
                <thead>
                    <th class="field-name">Field name</th>
                    <th class="current-value">Current value</th>
                    <th class="new-value">New value</th>
                </thead>
                <tbody>
                    <tr>
                        <td>Login</td>
                        <td>${username}</td>
                        <td><input type="text" style="width: 100%" name="new-login"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td>${address}</td>
                        <td><input type="text" style="width: 100%" name="new-address"/></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td>${phone}</td>
                        <td><input type="text" style="width: 100%" name="new-phone"/></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="Change" style="width: 100%"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>