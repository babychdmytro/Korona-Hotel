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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import session.PriceSessionBean;
import session.PriceSessionBeanRemote;
import session.RegistrationSessionBeanRemote;
import session.SearchSessionBeanRemote;
import session.UpdateBookingSessionBeanRemote;

/**
 *
 * @author Julia
 */
@Controller
public class EditBooking {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping(value = "/editBooking.htm", method = RequestMethod.POST)
    public String root(@ModelAttribute("roombooking") Roombooking booking, ModelMap model, HttpSession session, HttpServletRequest request) throws NamingException {
        
        String view = "index";
        
        if (request.getParameter("Edit")!=null) {
            // model.addAttribute("roomId", booking.getRoomId().getRoomId());
            model.addAttribute("booking", booking);
           view = "modifyBooking";
        } //  session.setAttribute("roomBookingForChange", booking);
        else if (request.getParameter("Delete")!=null) {
            UpdateBookingSessionBeanRemote update = (UpdateBookingSessionBeanRemote) session.getAttribute("updateBean");
            UpdateBookingSessionBeanRemote usb;
            if (update == null) {
                Context ctx = new InitialContext();
                usb = (UpdateBookingSessionBeanRemote) ctx.lookup(UpdateBookingSessionBeanRemote.class.getName());
                session.setAttribute("updateBean", usb);
            } else {
                usb = update;
            }
            usb.deleteBooking(booking);
             InitialContext ic = new InitialContext();
        Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
        SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
        User user = (User) searchBean.findUserByEmail(session.getAttribute("userSession").toString());
            List<Roombooking> activeBooking = (List) usb.getBookingTransactionTrue();
            model.addAttribute("activeBookings", activeBooking);
            
            List<Roombooking> previousBooking = (List) usb.getBookingTransactionFalse();
            model.addAttribute("previousBookings", previousBooking);
            model.addAttribute("roombooking", new Roombooking());
            model.addAttribute("booking", new Booking());
            model.addAttribute("room", new Room());
            view = "account";
        }
         else if (request.getParameter("DeletePrevious")!=null) {
            UpdateBookingSessionBeanRemote update = (UpdateBookingSessionBeanRemote) session.getAttribute("updateBean");
            UpdateBookingSessionBeanRemote usb;
            if (update == null) {
                Context ctx = new InitialContext();
                usb = (UpdateBookingSessionBeanRemote) ctx.lookup(UpdateBookingSessionBeanRemote.class.getName());
                session.setAttribute("updateBean", usb);
            } else {
                usb = update;
            }
            usb.deletePreviousBooking(booking);
             InitialContext ic = new InitialContext();
        Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
        SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;
        User user = (User) searchBean.findUserByEmail(session.getAttribute("userSession").toString());
            List<Roombooking> activeBooking = (List) usb.getBookingTransactionTrue();
            model.addAttribute("activeBookings", activeBooking);
            List<Roombooking> previousBooking = (List) usb.getBookingTransactionFalse();
            model.addAttribute("previousBookings", previousBooking);
            model.addAttribute("roombooking", new Roombooking());
            model.addAttribute("booking", new Booking());
            model.addAttribute("room", new Room());
            view = "account";
        }
        
return view;
    }

    @RequestMapping(value = "/checkAvailableModify.htm", method = RequestMethod.POST)
    public String edit(@ModelAttribute("booking") Roombooking roomBooking, ModelMap model, HttpSession session, HttpServletRequest request) throws NamingException {
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
        SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;

        // Roombooking rb = (Roombooking) session.getAttribute("roomBookingForChange");
        if (!searchBean.userSearchRoomEdit(roomBooking.getCheckIn(), roomBooking.getCheckOut(), roomBooking.getNumPeople(), roomBooking.getRoomBookingId().intValue())) {
            model.addAttribute("errorMsg", "The room is not available at that time");
            return "modifyBooking";
        } else {
            model.addAttribute("roomBooking", roomBooking);
            //find a corresponding booking
            UpdateBookingSessionBeanRemote update = (UpdateBookingSessionBeanRemote) session.getAttribute("updateBean");
            UpdateBookingSessionBeanRemote usb;
            if (update == null) {
                Context ctx = new InitialContext();
                usb = (UpdateBookingSessionBeanRemote) ctx.lookup(UpdateBookingSessionBeanRemote.class.getName());
                session.setAttribute("updateBean", usb);
            } else {
                usb = update;
            }
            Booking booking = (Booking) usb.finbBookingByRoomId(roomBooking.getRoomBookingId().intValue());
            model.addAttribute("booking", booking);

            Object obj = ic.lookup(PriceSessionBeanRemote.class.getName());
            PriceSessionBeanRemote priceBean = (PriceSessionBeanRemote) obj;

            double pricePerNight = searchBean.findRoomPriceByRoomBooking(roomBooking.getRoomBookingId().intValue());
            double totalPrice = priceBean.calculateRoomPrice(roomBooking.getCheckIn(), roomBooking.getCheckOut(), pricePerNight);
            model.addAttribute("totalPrice", totalPrice);
            session.removeAttribute("totalPriceEdit");
            session.setAttribute("totalPriceEdit", totalPrice);
            session.setAttribute("roomBookingIdForChange", roomBooking);
            model.addAttribute("action", "transactionProcess.htm");
            return "transaction";
        }

    }
}
