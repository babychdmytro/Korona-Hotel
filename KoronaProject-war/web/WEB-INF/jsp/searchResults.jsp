<%-- 
    Document   : searchResults
    Created on : 21-Mar-2017, 5:33:56 PM
    Author     : Julia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!doctype html>
<html>
    <head>
        <title>Korona Hotel</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.12.4.js"></script>
        <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <style>
        .rateDetails{
            padding-top: 10%;
        }
        hr {
            display: block;
            height: 1px;
            border: 0;
            border-top: 1px solid #6699FF;
            margin: 1em 0;
            padding: 0;
        }
        .top{
            background-color: #d9d9d9;
        }
        
         .banner{
                height: 150px;
                background-color: black;
                color: white;
                text-align: center;

            }
            .login{
                color: white;
            }
            
            .innerTable {
                width: 50%;
                margin: 0 auto;
}
.header{
    padding-top: 20px;
    text-align: center;
    font-size: 20px;
}
    </style>
    <body> 


        <div class="container">
           <div class="row" >
                <div class="col-sm-12" >

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
                                <li class="active"><a href="first.htm">Main Page</a></li>

                                <% if ((session.getAttribute("userSession") == null)) { %>
                                <li><a href="registration.htm">Registration</a></li>
                                <li>
                                    <form:form action="login.htm" method="post" modelAttribute="user">
                                        <table width="100%" border="0" cellpadding="3" cellspacing="0" align="center">
                                            <tr>
                                                <td colspan="2" align="right"><span class="login">Email:</span>
                                                </td>
                                                <td colspan="2"> <form:input path="email" name="email" type="text" size="25" maxlength="50" readonly="readonly"></form:input></td>


                                                    <td  colspan="2" align="right"><span class="login">Password:</span>
                                                    </td>
                                                    <td colspan="2"><form:input path="password" name="password" type="password" size="25" maxlength="50" readonly="readonly"></form:input></td>

                                                    <td width="100%" align="center"><input align="right" type="SUBMIT" value="Login" class="btn btn-warning"></td>

                                                    <td>${errorLogin}</td>
                                            </tr>
                                        </table>
                                    </form:form>
                                    <% } else { %>
                                <li><a href="userAccount.htm">My Account</a></li>
                                <li><a href="logout.htm">Logout</a></li>
                                    <%  }%>

                                </li>

                            </ul>
                        </div>
                    </nav>
                </div>
            </div>

                <div class="row">
                    <div class="col-sm-5">
                        Booking Details
                    </div>

                    <div class="col-sm-5">

                        Check-In: ${bookingInfo.getCheckIn()}
                    </div>
                    <div class="col-sm-2">
                        Number of People: ${bookingInfo.getNumPeople()}
                    </div>
                </div>
                <div class="row">
                  <div class="col-sm-5">
                       
                    </div>
                    <div class="col-sm-5">
                        Check-Out: ${bookingInfo.getCheckOut()}
                    </div>
<div class="col-sm-2">
                        <a href='first.htm'>Change Details</a>
                    </div>
                </div>
           
            
            <div class="row">
                 <div class="header">
                <div class='col-sm-12'>
                    <b>Search Results</b>
                    <h3>${errorMessage}</h3>
                    <hr>
                </div>
                </div>
            </div><c:forEach var="listVar" items="${availableRoom}">
                <form:form action="addBooking.htm" method="post" modelAttribute="room">

                    <div class="col-lg-12 well">
                        <div class="row">
                            <div class="col-sm-12">
                                ${listVar.getName()} 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-5">
                                <img src="${listVar.getImage()}" width="300" height="200">
                            </div>
                            <div class="col-sm-7">
                                ${listVar.getDescription()}


                            </div>
                        </div>
                        <div class="row" >

                            <div class="col-sm-4">
                                


                            </div>
                             
                            <div class="rateDetails">
                               <hr>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            
                                        </div>
                                       
                                        <div class="col-sm-2">
                                            Room Price
                                        </div>
                                        <div class="col-sm-2">

                                            ${listVar.getRoomPrice()} 
                                        </div>
                                        <div class="col-sm-2">
                                            <form:hidden path='name' value="${listVar.getName()}"></form:hidden>
                                            <form:hidden path='roomId' value="${listVar.getRoomId()}"></form:hidden>
                                            
                                            <form:hidden path='description' value="${listVar.getDescription()}"></form:hidden>

                                            <form:hidden path='roomPrice' value="${listVar.getRoomPrice()}"></form:hidden>
                                              <% if (!(session.getAttribute("userSession") == null)) { %>   
                                                <input type="submit" class="btn btn-warning" value="Select">
                                                <% } 
                                                else { %>
                                                <input type="submit" class="btn btn-warning" value="Select" disabled="disabled">
                                                  <%   } %>
                                            </div>
                                        </div>
                                        <hr></div>
                                </div>
                            </div>
                        </div>  </form:form> </c:forEach>
        </div>
    </body>
</html>

