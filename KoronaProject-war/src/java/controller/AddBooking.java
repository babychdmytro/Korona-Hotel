/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Booking;
import entity.Room;
import entity.Roombooking;
import entity.Roomtype;
import entity.User;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import session.BookingSessionBeanRemote;
import session.RoomSessionBeanRemote;
import session.SearchSessionBeanRemote;
import session.UpdateBookingSessionBeanRemote;
import session.UserLoginSessionBeanRemote;

/**
 *
 * @author Julia
 */

@Controller
public class AddBooking {
    
    
    @RequestMapping(value = {"/addBooking.htm"}, method = RequestMethod.POST)
    public String root( @ModelAttribute("room") Room room,  ModelMap model, HttpSession session) throws NamingException {

        

       //get booking information
       Roombooking bookingInfo = (Roombooking) session.getAttribute("bookingSession");
       
       
       //add to booking list
       BookingSessionBeanRemote valueOfAttribute = (BookingSessionBeanRemote) session.getAttribute("bookingLoginBean");
        BookingSessionBeanRemote bsb;
        if (valueOfAttribute == null)
        { 
            Context ctx = new InitialContext();
            bsb = (BookingSessionBeanRemote) ctx.lookup(BookingSessionBeanRemote.class.getName());
            session.setAttribute("bookingLoginBean", bsb);
        }
        else {
            bsb = valueOfAttribute;
        }
        
        String email = (String) session.getAttribute("userSession");
        
        bsb.addRoomBooking(room.getRoomId().intValue(), bookingInfo.getCheckIn(), bookingInfo.getCheckOut(), bookingInfo.getNumPeople(), email);
        UpdateBookingSessionBeanRemote update = (UpdateBookingSessionBeanRemote) session.getAttribute("updateBean");
        UpdateBookingSessionBeanRemote usb;
        if (update == null)
        { 
            Context ctx = new InitialContext();
            usb = (UpdateBookingSessionBeanRemote) ctx.lookup(UpdateBookingSessionBeanRemote.class.getName());
            session.setAttribute("updateBean", usb);
        }
        else {
            usb = update;
        }
        
        
          InitialContext ic = new InitialContext();
        Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
        SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
        User user = (User) searchBean.findUserByEmail(session.getAttribute("userSession").toString());
        usb.getBookingList(session.getAttribute("userSession").toString());
        List<Roombooking> activeBooking = (List)usb.getBookingTransactionTrue();
        model.addAttribute("activeBookings", activeBooking);
        List<Roombooking> previousBooking = (List)usb.getBookingTransactionFalse();
        model.addAttribute("previousBookings", previousBooking);
        model.addAttribute("roombooking", new Roombooking());
        model.addAttribute("booking", new Booking());
        model.addAttribute("room", new Room());
        
        return "account";

    }
}
