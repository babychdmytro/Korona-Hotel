<%@page import="entity.Room"%>
<%@page import="session.RoomSessionBeanRemote"%>
<%@page import="session.BookingSessionBeanRemote"%>
<%@page import="entity.Roomtype"%>
<%@page import="java.util.List"%>
<%@page import="session.SearchSessionBeanRemote"%>
<%@page import="javax.naming.InitialContext"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

    <head>
        <meta charset="UTF-8">
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

            .mainImage{
                width: 100%;
                height: 50%;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row" >
                <div class="col-sm-12" >

                    <img src="http://fs9.fex.net/get/579481818798/7153750/thumbImage.jpg" class="mainImage" alt="banner">
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

                    <form:form action="submitCheck.htm" method="post" modelAttribute="roomBooking" >

                        <table width="100%" border="0" cellpadding="3" cellspacing="0" align="center">
                            <tr>
                                <td width="15%">
                                </td>
                                <td width="15%">
                                </td>
                                <td width="13%">
                                </td>
                                <td width="13%">
                                </td>
                                <td width="13%">
                                </td>
                                <td width="22%">
                                </td>
                                <td width="5%">
                                </td>
                                <td width="4%">
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                <td colspan="5" class="HeaderText1">Choose your selection details</td>
                            </tr>
                            <tr>
                                ${errorMsg}
                                <td colspan="8"><form:errors class="error" path="checkIn"></form:errors></td>
                                </tr>
                                <tr>
                                    <td colspan="8"><form:errors class="error" path="checkOut"></form:errors></td>
                                </tr>


                                <tr>
                                    <td colspan="2"></td>
                                    <td colspan="1" class="DateLabelText"></td>
                                    <td colspan="1" class="DateLabelText"></td>
                                    <td colspan="1" class="DateLabelText"></td>
                                </tr>
                                <tr>
                                <div class="form-group">
                                    <td colspan="2"></td>
                                    <td align="right">Check-In:
                                    </td>
                                    <td colspan="2"> <form:input path="checkIn" type="text" class="datepicker" name="startDate" size="10" maxlength="10" readonly="readonly"></form:input></td>
                                </div>
                                </tr>
                                <tr>
                                    <td colspan="2"></td>
                                    <td align="right">Check-Out:
                                    </td>
                                    <td colspan="2"><form:input path="checkOut" type="text" class="datepicker" name="endDate" size="10" maxlength="10" readonly="readonly"></form:input></td>
                                </tr>

                                <script>
                                    $(document).ready(function () {
                                        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}
                                        );
                                        $(".datepicker").attr('readonly', 'readonly');
                                    });
                                </script>
                                <tr>
                                <% InitialContext ic = new InitialContext();
                                    Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
                                    SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
                                    //get roomtypes
                                    List<Roomtype> room = (List) searchBean.getAllRoomType();
                                %>
                                <td colspan="2"></td>
                                <td align="right">Room Type:
                                </td> 
                                <td>   
                                    <form:form action="submitCheck.htm" method="post" modelAttribute="room" >
                                        <form:select path="roomTypeId"> 
                                            <%   for (int i = 0; i < room.size(); i++) {%>
                                            <form:option value="<%= room.get(i).getRoomTypeId()%>" label="<%=room.get(i).getRoomTypeName()%>"/>
                                            <% }%>
                                        </form:select>
                                    </form:form>
                                </td>


                            </tr>
                            <tr>
                                <td colspan="2"></td>
                                <td align="right">Adults:
                                </td>
                                <td colspan="2">
                                    <form:select path="numPeople"> 
                                        <form:option value="1" label="1"/>
                                        <form:option value="2" label="2" selected="true"/>
                                        <form:option value="3" label="3" />
                                        <form:option value="4" label="4" />
                                    </form:select></td>
                            </tr>


                        </table>
                        <table width="100%" border="0" cellpadding="0" cellspacing="1">
                            <tr>
                                <td width="24%">
                                </td>
                                <td width="24%">
                                </td>
                                <td width="24%">
                                </td>
                                <td width="24%">
                                </td>
                                <td width="4%">
                                </td>
                            </tr>
                            <tr>
                                <td width="100%" align="center"><input align="right" type="SUBMIT" class="btn btn-warning" value="Check Availability &amp; Rates"></td>
                            </tr>
                        </table>
                        <form:errors class="error" path="numPeople"></form:errors>


                    </form:form>
                </div>
            </div>
        </div>

    </body>
</html>