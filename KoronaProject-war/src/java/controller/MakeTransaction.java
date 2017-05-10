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
import java.util.concurrent.TimeUnit;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import session.BookingSessionBeanRemote;
import session.SearchSessionBeanRemote;
import session.UpdateBookingSessionBeanRemote;

/**
 *
 * @author Julia
 */

@Controller
public class MakeTransaction {
    
     @RequestMapping(value="/transactionProcess.htm", method=RequestMethod.POST)
    public String processTransaction(@Valid @ModelAttribute("booking") Booking booking, BindingResult bindingResult, ModelMap modelMap, HttpSession session) throws NamingException{
     
     // long difference = booking.getCheckOut().getTime()-booking.getCheckIn().getTime();
       
      if(bindingResult.hasErrors()) {
            return "transaction";
        }
     
      
        else{ 
         //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       //    dateFormat.format(booking.getCheckin());
       double price = (double) session.getAttribute("totalPriceEdit");
            Roombooking oldrb = (Roombooking) session.getAttribute("roomBookingIdForChange");
       
       
               InitialContext ic = new InitialContext();
               Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
               SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
               
               double oldPrice = searchBean.findRoomBookingPriceByBooking(oldrb.getRoomBookingId().intValue());
               
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
        
        //values for update
        
        
        
        usb.updateCheckOut((Object)session.getAttribute("roomBookingIdForChange"), booking, (double) session.getAttribute("totalPriceEdit"));
       
         
        User user = (User) searchBean.findUserByEmail(session.getAttribute("userSession").toString());
        usb.getBookingList(user.getEmail());
        List<Roombooking> activeBooking = (List)usb.getBookingTransactionTrue();
        modelMap.addAttribute("activeBookings", activeBooking);
        List<Roombooking> previousBooking = (List)usb.getBookingTransactionFalse();
        modelMap.addAttribute("previousBookings", previousBooking);
        modelMap.addAttribute("roombooking", new Roombooking());
        modelMap.addAttribute("booking", new Booking());
        modelMap.addAttribute("room", new Room());
        
        
        //create a session variable
        return "account";
      }
        
    }
    
     @RequestMapping(value="/cartCheckOut.htm", method=RequestMethod.POST)
    public String processTransactionCheckOut(@Valid @ModelAttribute("booking") Booking booking, BindingResult bindingResult, ModelMap modelMap, HttpSession session) throws NamingException{
     
     // long difference = booking.getCheckOut().getTime()-booking.getCheckIn().getTime();
       
      if(bindingResult.hasErrors()) {
            return "transaction";
        }
     
      
        else{ 
            
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
        
        //values for update
        
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
        
       
        
        bsb.checkOut(booking);
         InitialContext ic = new InitialContext();
        Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
        SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
        User user = (User) searchBean.findUserByEmail(session.getAttribute("userSession").toString());
        usb.getBookingList(user.getEmail());
        List<Roombooking> activeBooking = (List)usb.getBookingTransactionTrue();
        modelMap.addAttribute("activeBookings", activeBooking);
        List<Roombooking> previousBooking = (List)usb.getBookingTransactionFalse();
        modelMap.addAttribute("previousBookings", previousBooking);
        modelMap.addAttribute("roombooking", new Roombooking());
        modelMap.addAttribute("booking", new Booking());
        modelMap.addAttribute("room", new Room());
        
        
        //create a session variable
        return "account";
      }
        
    }
    
}
