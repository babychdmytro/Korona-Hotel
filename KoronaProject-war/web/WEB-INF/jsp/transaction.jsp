<%-- 
    Document   : transaction
    Created on : 25-Mar-2017, 9:19:26 PM
    Author     : Julia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en" xmlns:p="http://primefaces.org/ui"  >
    <head>
        <meta charset="UTF-8">
        <title>Korona Hotel</title>
        <link rel="stylesheet" href="<c:url value="mainStyle.css" />">
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
               .innerTable {
            width: 50%;
            margin: 0 auto;
        }
         .customInput{
            text-align: right;
            vertical-align: middle;
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
            <div class="header">
                <h1> Total Price - ${totalPrice}
                </h1>
            </div>
<div class="innerTable">
            <form:form method="post" action="${action}" modelAttribute="booking" >
                
               <div class="row">
                   <div class="col-sm-6" > <div class="customInput">       
                            Credit Card Type: 
                       </div>
                            </div>
                    <div class="col-sm-6" >   <form:select path="creditCardType">
                                    <form:option value="American Express">American Express</form:option>
                                    <form:option value="Diners Card">Diners Card</form:option>
                                    <form:option value="Discover Card">Discover Card</form:option>
                                    <form:option value="Master Card">Master Card</form:option>
                                    <form:option value="Visa">VISA</form:option>
                                </form:select>
                                <form:errors class="error" path="creditCardType"></form:errors></td>
               </div>   
               </div>
                                  <div class="row">
                   <div class="col-sm-6" > <div class="customInput">
                              Credit Card Number:
                          </div>
                            </div>
                    <div class="col-sm-6" > <form:input path="creditCardNumber" id="creditCardNumberInput"></form:input>

                                <form:errors class="error" path="creditCardNumber"></form:errors></td>
                               </div>   
               </div>
                               
                               <div class="row">
                   <div class="col-sm-6" > <div class="customInput">
                                CVV Code:
                                   </div>
                            </div>
                                   <div class="col-sm-6" >
                                    <form:input  path="cvv" id="cvvInput"></form:input>

                                <form:errors class="error" path="cvv"></form:errors>
                                
                            </div>   
               </div>
                                <div class="row">
                   <div class="col-sm-6" > <div class="customInput">
                                Expiry Date:
                                </div>
                            </div>
                          <div class="col-sm-6" >
                                    Month  
                                <form:select path="expMonth"><form:option value="01">
                                        01
                                    </form:option>
                                    <form:option value="02">
                                        02
                                    </form:option>
                                    <form:option value="03">
                                        03
                                    </form:option>
                                    <form:option value="04">
                                        04
                                    </form:option>
                                    <form:option value="05">
                                        05
                                    </form:option>
                                    <form:option value="06">
                                        06
                                    </form:option>
                                    <form:option value="07">
                                        07
                                    </form:option>
                                    <form:option value="08">
                                        08
                                    </form:option>
                                    <form:option value="09">
                                        09
                                    </form:option>
                                    <form:option value="10">
                                        10
                                    </form:option>
                                    <form:option value="11">
                                        11
                                    </form:option>
                                    <form:option value="12">
                                        12
                                    </form:option></form:select>
                                <form:errors class="error" path="expMonth"></form:errors>
                                    Year  
                                <form:select path="expYear">

                                    <form:option value="2017">2017</form:option>
                                    <form:option value="2018">2018</form:option>
                                    <form:option value="2019">2019</form:option>
                                    <form:option value="2020">2020</form:option>
                                    <form:option value="2021">2021</form:option>
                                    <form:option value="2022">2022</form:option>
                                    <form:option value="2023">2023</form:option>
                                    <form:option value="2024">2024</form:option></form:select>
                                <form:errors class="error" path="expYear"></form:errors>
                           
                                  
                   </div>
            
                         <div class="header"><input type="submit" class="btn btn-warning" value="Confirm"></div>
                                <form:hidden path='bookingId' value="${booking.getBookingId()}"></form:hidden>         
        </form:form>
</div>
      
    </div>
</body>
</html>
