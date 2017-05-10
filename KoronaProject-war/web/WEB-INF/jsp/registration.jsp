<%-- 
    Document   : registration
    Created on : 25-Mar-2017, 9:39:43 PM
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
            
            .login{
                color: white;
            }

        </style>
    </head>
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
 <div class="innerTable">
            <div class="row">

                <div class="col-sm-12">

<h3 style="color: red">${registrationError}</h3>
                    <form:form action="registration.htm" method="post" modelAttribute="user" >
                       
                            <table>
                                <tr>
                                    <td>Email:</td>
                                    <td><form:input path="email" type="text" name="userEmail"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Password:</td>
                                        <td><form:input path="password" type="password" name="userPassword"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Confirm password:</td>
                                        <td><input path="password" type="password" name="confirmUserPassword"></td>
                                    </tr>
                                    <tr>
                                        <td>First name:</td>
                                        <td><form:input path="firstName" type="text" name="userFirstName"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Last name:</td>
                                        <td><form:input path="lastName" type="text" name="userLastName"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Address</td>
                                        <td><form:input path="address" type="text" name="userAddress"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>City:</td>
                                        <td><form:input path="city" type="text" name="userCity"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Country:</td>
                                        <td><form:input path="country" type="text" name="userCountry"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Postal code:</td>
                                        <td><form:input path="postalCode" type="text" name="userPostalCode"></form:input></td>
                                    </tr>
                                    <tr>
                                        <td>Phone number:</td>
                                        <td><form:input path="phoneNumber" type="text" name="userPhoneNumber"></form:input></td>
                                    </tr>
                                   
                                    <tr>
                                         <div class="innerTable">
                                        <td><input type="submit" class="btn btn-warning" value="Register"></td>
                                         </div>
                                    </tr>
                                    
                                </table>
                                
                        </div>
                    </form:form>
                </div>

            </div>
        </div>
    </body>
</html>
