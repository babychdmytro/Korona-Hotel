<%-- 
    Document   : index
    Created on : 18-Mar-2017, 1:57:22 PM
    Author     : Julia
--%>

<%@page import="entity.User"%>
<%@page import="entity.Roombooking"%>
<%@page import="session.BookingSessionBeanRemote"%>
<%@page import="session.SearchSessionBeanRemote"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="entity.Room"%>
<%@page import="java.util.List"%>
<%@page import="session.SearchSessionBean"%>
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
         <form method=GET action='index.jsp'>
        <% InitialContext ic = new InitialContext();
        Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
        SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
        List<Room> rooms = new ArrayList<Room>();
        rooms = (List) searchBean.userSearchRoom(Date.valueOf("2019-05-02"), Date.valueOf("2019-05-07"), 3, "Deluxe");
        Room room;
 
        for(int i=0;i<rooms.size();i++) 
        { 
         room = rooms.get(i); 
        out.println(room.getName() + "<br>");
        out.println("<input type='text' name='roomId' value='" + room.getRoomId() + "'>");
        out.println("<input type='text' name='checkIn' value='" + Date.valueOf("2019-05-02") + "'>");
        out.println("<input type='text' name='checkOut' value='" +Date.valueOf("2019-05-07") + "'>");
        out.println("<input type='text' name='capacity' value='" +3 + "'>");
        out.println("<input type='submit' value='Add' name='AddRoom'>");
        }%>
        
           
          </form>
         <%   Object booking;
         BookingSessionBeanRemote createBooking;
         if (request.getParameter("AddRoom")!=null){
             
           if  (request.getSession().getAttribute("Booking")==null){
              booking = ic.lookup(BookingSessionBeanRemote.class.getName()); 
        request.getSession().setAttribute("Booking", booking);
         }
           else {
               booking = request.getSession().getAttribute("Booking");
           }
           createBooking = (BookingSessionBeanRemote) booking;
           List<Roombooking> rb = new ArrayList<Roombooking>();
           rb = (List) createBooking.addRoomBooking(Integer.parseInt(request.getParameter("roomId")), 
                   Date.valueOf(request.getParameter("checkIn")), Date.valueOf(request.getParameter("checkOut")), Integer.parseInt(request.getParameter("capacity")));
         
           for(int i=0;i<rb.size();i++) 
        { 
        out.println(rb.get(i).getCheckIn().toString()+ "<br>");
        }%>
        <form method=GET action='index.jsp'>
             <input type='submit' value='Check Out' name='CheckOut'>;
        </form>
        <%
         }
if  (request.getParameter("CheckOut")!=null){
    booking = request.getSession().getAttribute("Booking");
    createBooking = (BookingSessionBeanRemote) booking;
     createBooking.checkOut(1, "Visa", "12344535", 123, 12, 2019);
    out.println("Success");


}
         %>
         
    </body>
</html>
