<%-- 
    Document   : account
    Created on : 25-Mar-2017, 10:50:01 AM
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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.12.4.js"></script>
        <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
                                <li class="active"><a href="first.htm">Main Page</a></li>

                                <% if ((session.getAttribute("userSession") == null)) { %>
                                <li><a href="registration.htm">Registration</a></li>
                                <li>
                                    <form:form action="loginIndex.htm" method="post" modelAttribute="user">
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
                <div class="col-sm-12">
                    <div class="header">
                        <h1>My Account</h1>
                    </div>
                    <h1>Cart</h1>
                    <c:forEach var="listVarPr" items="${previousBookings}">

                        <form:form action="editBooking.htm" method="post" modelAttribute="roombooking">
                            <div class="col-lg-12 well">
                                <div class="row">
                                    <div class="col-sm-12">
                                        ${listVarPr.getRoomId().getName()} 
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-5">
                                        Check-In Date - <b>${listVarPr.getCheckIn()}</b><br>
                                        Check-Out Date - <b>${listVarPr.getCheckOut()}</b>

                                    </div>
                               
                                   
                                        <div class="col-sm-5">

                                           
                                           
                                           Room Price - <b>${listVarPr.getPriceTotal()} </b>
                                                </div>
                                                <div class="col-sm-2">
                                                    <form:hidden path='roomBookingId' value="${listVarPr.getRoomBookingId()}"></form:hidden>



                                                        <input type="submit" class="btn btn-danger" value="Delete" name='Delete'>
                                                    </div>
                                               
                                                </div>
                                        </div>
                                      </form:form> </c:forEach>
                    <c:if test="${not empty previousBookings}">
                        <div class="row">
<div class="col-sm-12">
    <button class="btn btn-success" onclick="location.href = 'checkout.htm'">Check Out</button></div> </div>
                    </c:if>
                    <h1>Previous Bookings</h1>
                    <c:forEach var="listVar" items="${activeBookings}">
                        <form:form action="editBooking.htm" method="post" modelAttribute="roombooking">

                           <div class="col-lg-12 well">
                                <div class="row">
                                    <div class="col-sm-12">
                                        ${listVar.getRoomId().getName()} 
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-5">
                                        Check-In Date - <b>${listVar.getCheckIn()}</b><br>
                                        Check-Out Date - <b>${listVar.getCheckOut()}</b>

                                    </div>
                               
                                   
                                        <div class="col-sm-5">

                                           
                                           
                                           Room Price - <b>${listVar.getPriceTotal()} </b>
                                                </div>
                                                <div class="col-sm-2">

                                                    <form:hidden path='roomBookingId' value="${listVar.getRoomBookingId()}"></form:hidden>
                                                     <input type="submit" value="Edit" class="btn btn-warning" name='Edit'>
                                                        <input type="submit" value="Delete" class="btn btn-danger" name='DeletePrevious'>
                                                       

                                                    </div>
                                                </div>
                                               
                                    
                                </div> </form:form>  </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
