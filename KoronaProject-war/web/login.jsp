<%-- 
    Document   : login
    Created on : 19-Mar-2017, 1:38:25 PM
    Author     : Julia
--%>

<%@page import="entity.Roombooking"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="session.UserLoginSessionBeanRemote"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="get" action="login.jsp">
            
           Username <input type="text" name="username">
           Password<input type="text" name="password">
           <input type="submit" value="login" name="Login">
        </form>
        
        <% InitialContext ic = new InitialContext();
        Object o = ic.lookup(UserLoginSessionBeanRemote.class.getName());
        UserLoginSessionBeanRemote loginBean = (UserLoginSessionBeanRemote) o;
        if (loginBean.checkLogin(request.getParameter("username"), request.getParameter("password"))){
            out.println("Sucess");
        };
        List <Roombooking> list = (List)loginBean.getBookingList();
        for (int i=0; i<list.size(); i++){
            out.println(list.get(i).toString() + "<br>");
        }
        %>
    </body>
</html>
