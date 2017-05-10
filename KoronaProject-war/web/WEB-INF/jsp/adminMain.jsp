<%-- 
    Document   : adminMain
    Created on : 25-Mar-2017, 9:39:08 PM
    Author     : Julia
--%>

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

        <style>

        </style>

    </head>
    <body>
        
         <% if ((session.getAttribute("sessionAdmin") == null)) { 
      response.sendRedirect("loginAdmin.htm");
  }%>
        
        
         <div class="container">
             <div class="row">
                 <div class="col-sm-12">
                ${errorMessage}     
                 </div>
                 </div>
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
        
            
            <div>
                <br>
                <div class="row">
                    <div class="col-sm-2"><b>Room name</b></div>
                    <div class="col-sm-4"><b>Description</b></div>
                    <div class="col-sm-1"><b>Room type</b></div>
                    <div class="col-sm-1"><b>Floor</b></div>
                    <div class="col-sm-1"><b>Room price</b></div>
                    <div class="col-sm-1"><b>Capacity</b></div>
                </div>

                <c:forEach var="listRoom" items="${roomsList}">
                    
                    <form:form action="editRoom.htm" method="post" modelAttribute="room" >
                    <hr>
                    <div class="row">
                        <form:hidden path='roomId' value="${listRoom.getRoomId()}"></form:hidden>
                        <form:hidden path='name' value="${listRoom.getName()}"></form:hidden>
                        <form:hidden path='description' value="${listRoom.getDescription()}"></form:hidden>
                            <form:form action="editRoom.htm" method="post" modelAttribute="roomType" >
                                <form:hidden path='roomTypeId' value="${listRoom.getRoomType().getRoomTypeId()}"></form:hidden>
                            </form:form>
                        <form:hidden path='floor' value="${listRoom.getFloor()}"></form:hidden>
                        <form:hidden path='roomPrice' value="${listRoom.getRoomPrice()}"></form:hidden>
                        <form:hidden path='capacity' value="${listRoom.getCapacity()}"></form:hidden>
                        
                        
                        <div class="col-sm-2">${listRoom.getName()}</div>
                        <div class="col-sm-4">${listRoom.getDescription()}</div>
                        <div class="col-sm-1">${listRoom.getRoomType().getRoomTypeName()}</div>
                        <div class="col-sm-1">${listRoom.getFloor()}</div>
                        <div class="col-sm-1">${listRoom.getRoomPrice()}</div>
                        <div class="col-sm-1">${listRoom.getCapacity()}</div>
                        <div class="col-sm-2">
                        <button class="btn btn-warning" type="submit" name="edit"  value="edit">Update</button> 
                        <button class="btn btn-danger" type="submit" name="delete" value="${listRoom.getRoomId()}">Delete</button></div>
                    </div>
                      </form:form>
                </c:forEach>
            </div>
        
         </div>
    </body>
</html>