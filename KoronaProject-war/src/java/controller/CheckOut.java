/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Booking;
import entity.Room;
import entity.Roombooking;
import entity.User;
import java.util.List;
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
public class CheckOut {

    @RequestMapping(value = "/checkout.htm", method = RequestMethod.GET)
    public String processTransaction(ModelMap modelMap, HttpSession session) throws NamingException {
   
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
    double totalPrice = bsb.getTotalPrice(user);
    modelMap.addAttribute("totalPrice", totalPrice);
    modelMap.addAttribute("booking", (Booking) bsb.getBooking());
    modelMap.addAttribute("action", "cartCheckOut.htm");
    
        //create a session variable
        return "transaction";

    }
}
