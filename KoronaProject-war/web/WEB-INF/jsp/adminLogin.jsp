<%-- 
    Document   : login
    Created on : 23-Mar-2017, 9:28:26 PM
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

        .banner{
            height: 150px;
            background-color: black;
            color: white;
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
        
        .customInputCentre{
             text-align: center;
            vertical-align: middle;
        }
    </style>
    <body> 

        <div class="container">
            <div class="row" >
                <div class="col-sm-12" >

                    <img src="http://fs9.fex.net/get/579481818798/7153750/thumbImage.jpg" class="mainImage" alt="banner">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="innerTable">
                        <form:form action="loginAdminCheck.htm" method="post" modelAttribute="user">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="customInputCentre">
                                        ${errorMessage}
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6" >
                                    <div class="customInput">
                                        Email:
                                    </div>
                                </div>
                                
                                <div class="col-sm-6">
                                    <form:input path="email" type="text" size="15" maxlength="30" readonly="readonly"></form:input>
                                    </div>
                                </div>
                                     <br>
                                <div class="row">
                                    <div class="col-sm-6" >
                                        <div class="customInput">
                                            Password:
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                    <form:input path="password" type="password" size="15" maxlength="30" readonly="readonly"></form:input>
                                        <br>
                                    </div>
                                    </div>
                                        <br>
                                    <div class="row">
                                        <div class="col-sm-12" class="innerTable">
                                            <div class="customInputCentre">
                                            <input class="btn btn-warning" type="SUBMIT" value="Login">
                                            </div>
                                        </div>
                                </form:form>
                           
                        </div>
                    </div>
                </div>
                </body>
                </html>
