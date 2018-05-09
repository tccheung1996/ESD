<%-- 
    Document   : error_page
    Created on : Nov 20, 2017, 6:08:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-learning system</title>
    </head>
    <body>
        <h1>Error</h1>
        <a href="<%=request.getParameter("url")%>">try again</a>

    </body>
</html>
