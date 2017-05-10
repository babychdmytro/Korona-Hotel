<%-- 
    Document   : adminForm
    Created on : 25-Mar-2017, 9:38:33 PM
    Author     : Julia
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Roomtype"%>
<%@page import="session.SearchSessionBeanRemote"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Korona Hotel</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.12.4.js"></script>
        <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <title>JSP Page</title>
           <style>

            .banner{

                background-color: black;
                color: white;
                text-align: center;
                border: 1px solid black;
            }
            #title{
                padding-top: 50px;
                font-size: 25px;
            }

            .login{
                color: white;
            }

            .HeaderText1{
                font-size: 40px;
            }

            .header{
                text-align: center;
            }

            .mainImage{
                width: 100%;
               height: 20%;
            }

            .innerTable {
                width: 50%;
                margin: 0 auto;
}
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row" >
                <div class="col-sm-12" class="banner">

                    <img src="http://fs9.fex.net/get/579481818798/7153750/thumbImage.jpg" style="width:100%;height:330px;" alt="banner">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12" >
                    <nav class="navbar navbar-inverse">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <a class="navbar-brand" href="#">Korona Hotel</a>
                            </div>
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="admin.htm">Main Admin Page</a></li>
                                  <li class="active"><a href="addNewRoomForm.htm">Add new room</a></li>
                                 <li class="active"><a href="reporstForm.htm">Reports</a></li>
        <li class="active"><a href="logOut.htm">Logout</a></li>
                                

                            

                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
             <div class="row">
                <div class="col-sm-12" >
                    <div class="innerTable">
        <form:form action="${action}" method="post" modelAttribute="room" >
            <div class="innerTable">
            <table>
                <tr>
                    <form:hidden path='roomId' ></form:hidden>
                    <td>Room name:</td>
                    <td><form:input type="text"  path="name" required="true"></form:input></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><form:textarea path="description" required="true"></form:textarea></td>
                    </tr>
                    <tr>
                        <td>Room type:</td>
                        <td>
                        <% InitialContext ic = new InitialContext();
                            Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
                            SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
                            //get roomtypes
                            List<Roomtype> room = (List) searchBean.getAllRoomType();
                        %>
                        <form:form action="addNewRoom.htm" method="post" modelAttribute="roomType">
                            <form:select path="roomTypeId">
                                <%   for (int i = 0; i < room.size(); i++) {%>
                                <form:option value="<%= room.get(i).getRoomTypeId()%>" label="<%=room.get(i).getRoomTypeName()%>" />
                                <% }%>
                            </form:select>
                        </form:form></td>
                </tr>
                <tr>
                    <td>Floor:</td>
                    <td><form:select path="floor"> 
                            <form:option value="1" label="1" selected="true"/>
                            <form:option value="2" label="2" />
                            <form:option value="3" label="3" />
                            <form:option value="4" label="4" />
                            <form:option value="5" label="5" />
                            <form:option value="6" label="6" />
                        </form:select></td>
                </tr>
                <tr>
                    <td>Room price:</td>
                    <td><form:input type="number" step="0.01" path="roomPrice" required="true"></form:input></td>
                    </tr>
                    <tr>
                        <td>Capacity:</td>
                        <td>
                            <form:select path="capacity">
                            <form:option value="1" label="1" selected="true"/>
                            <form:option value="2" label="2" />
                            <form:option value="3" label="3" />
                            <form:option value="4" label="4" />
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td><div class="innerTable"><input class="btn btn-warning" type="submit" value="${confirmButton}"></div></td>
                    </tr>
                    <form:errors class="error" path="roomPrice"></form:errors>
                </table><div class="innerTable">
        </form:form>
                    </div>
                </div>
             </div>
        </div>
             </div>
        </div>
    </body>
</html>