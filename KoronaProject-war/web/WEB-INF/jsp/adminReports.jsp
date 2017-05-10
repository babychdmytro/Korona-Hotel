<%-- 
    Document   : adminReports
    Created on : 27-Mar-2017, 10:34:32 PM
    Author     : Julia
--%>


<%@page import="session.SearchSessionBeanRemote"%>
<%@page import="entity.Roomtype"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    </head>
    <style>
        
            .header{
                text-align: center;
            }
            hr{
                border-top: 3px double #8c8b8b;
            }
    </style>
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
     <div class="header">
                       <h3>Admin Reports</h3>
                    </div>
 <div class="row">
     <div class="col-sm-6">
        <form:form action="processDateReport.htm" method="post" target="_blank" modelAttribute="roomBooking">
            <table>
                <h4>Reports for reserved rooms by dates</h4>
                <tr>
                    <td>Check in:</td>
                    <td><form:input path="checkIn" type="text" class="datepicker" name="startDate" size="10" maxlength="10" readonly="readonly"></form:input></td>
                    </tr>
                    <tr>
                        <td>Check out:</td>
                        <td><form:input path="checkOut" type="text" class="datepicker" name="endDate" size="10" maxlength="10" readonly="readonly"></form:input></td>
                    </tr>
                    <tr>
                        <td><input type="submit" class="btn btn-warning" value="Generate report"></td>
                    </tr>

                    <script>
                        $(document).ready(function () {
                            $(".datepicker").datepicker({dateFormat: 'yy/mm/dd'}
                            );
                            $(".datepicker").attr('readonly', 'readonly');
                        });
                    </script>
                </table>
        </form:form>

     </div>
     <div class="col-sm-6">
        <form:form action="processFloorReport.htm" target="_blank" method="post" modelAttribute="room">
            <table>
                <h4>Reports for reserved rooms by floor</h4>

                <tr>
                    <td><form:select path="floor"> 
                            <form:option value="1" label="1" selected="true"/>
                            <form:option value="2" label="2" />
                            <form:option value="3" label="3" />
                            <form:option value="4" label="4" />
                            <form:option value="5" label="5" />
                            <form:option value="6" label="6" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" class="btn btn-warning" value="Generate report"></td>
                </tr>


            </table>
        </form:form>
     </div>
 </div>
     <hr>
     <div class="row">
        <div class="col-sm-6">
        <form:form action="processUnpaidBookingsReport.htm" target="_blank" method="post" modelAttribute="room">
            <table>
                <h4>Reports for unpaid rooms</h4>
                <tr>
                    <td><input type="submit" class="btn btn-warning" value="Generate report"></td>
                </tr>

            </table>
        </form:form>

        </div>
 <div class="col-sm-6">
        <h4>Reports for room type</h4>

        <% InitialContext ic = new InitialContext();
            Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
            SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
            //get roomtypes
            List<Roomtype> room = (List) searchBean.getAllRoomType();
        %>
        <form:form action="roomTypeBookingsReport.htm" target="_blank" method="post" modelAttribute="roomType">
            <form:select path="roomTypeId">
                <%   for (int i = 0; i < room.size(); i++) {%>
                <form:option value="<%= room.get(i).getRoomTypeId()%>" label="<%=room.get(i).getRoomTypeName()%>"/>
                <% }%>
            </form:select>
            <br>
            <input type="submit" class="btn btn-warning" value="Generate report">
        </form:form>
 </div>
     </div>

        <br>
        <br>
 </div>
    </body>
</html>