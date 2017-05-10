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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import session.SearchSessionBeanRemote;
import session.UpdateBookingSessionBeanRemote;
import session.UserLoginSessionBean;
import session.UserLoginSessionBeanRemote;

/**
 *
 * @author Julia
 */
@Controller
@SessionAttributes("userBookings")
public class Login {

    @RequestMapping(value = "/loginIndex.htm", method = RequestMethod.POST)
    public String index(ModelMap model, HttpSession session, HttpServletRequest request) throws NamingException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserLoginSessionBeanRemote valueOfAttribute = (UserLoginSessionBeanRemote) session.getAttribute("userLoginBean");
        UserLoginSessionBeanRemote sb;
        if (valueOfAttribute == null) {
            Context ctx = new InitialContext();
            sb = (UserLoginSessionBeanRemote) ctx.lookup(UserLoginSessionBeanRemote.class.getName());
            session.setAttribute("userLoginBean", sb);
        } else {
            sb = valueOfAttribute;
        }

        if (sb.checkLogin(email, password)) {
            session.setAttribute("userSession", email);
            UpdateBookingSessionBeanRemote update = (UpdateBookingSessionBeanRemote) session.getAttribute("updateBean");
            UpdateBookingSessionBeanRemote usb;
            if (update == null) {
                Context ctx = new InitialContext();
                usb = (UpdateBookingSessionBeanRemote) ctx.lookup(UpdateBookingSessionBeanRemote.class.getName());
                session.setAttribute("updateBean", usb);
            } else {
                usb = update;
            }
            List<Roombooking> rb = (List) usb.getBookingList(email);
            session.setAttribute("userBookings", rb);
        } else {
            model.addAttribute("errorLogin", "Please, check your credentials!");
        }

        model.addAttribute("roomBooking", new Roombooking());
        model.addAttribute("room", new Roomtype());
        model.addAttribute("user", new User());

        return "index";
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public String searchResults(ModelMap model, HttpSession session, HttpServletRequest request) throws NamingException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserLoginSessionBeanRemote valueOfAttribute = (UserLoginSessionBeanRemote) session.getAttribute("userLoginBean");
        UserLoginSessionBeanRemote sb;
        if (valueOfAttribute == null) {
            Context ctx = new InitialContext();
            sb = (UserLoginSessionBeanRemote) ctx.lookup(UserLoginSessionBeanRemote.class.getName());
            session.setAttribute("userLoginBean", sb);
        } else {
            sb = valueOfAttribute;
        }

        if (sb.checkLogin(email, password)) {
            session.setAttribute("userSession", email);
            UpdateBookingSessionBeanRemote update = (UpdateBookingSessionBeanRemote) session.getAttribute("updateBean");
            UpdateBookingSessionBeanRemote usb;
            if (update == null) {
                Context ctx = new InitialContext();
                usb = (UpdateBookingSessionBeanRemote) ctx.lookup(UpdateBookingSessionBeanRemote.class.getName());
                session.setAttribute("updateBean", usb);
            } else {
                usb = update;
            }
            List<Roombooking> rb = (List) usb.getBookingList(email);
            session.setAttribute("userBookings", rb);
        } else {
            model.addAttribute("errorLogin", "Please, check your credentials!");
        }

        model.addAttribute("availableRoom", (List<Room>) session.getAttribute("availableRoomListSession"));
        model.addAttribute("room", new Room());

        model.addAttribute("bookingInfo", (Roombooking) session.getAttribute("bookingSession"));
        model.addAttribute("user", new User());

        return "searchResults";
    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logOut(ModelMap model, HttpSession session, HttpServletRequest request) throws NamingException {
        if (session.getAttribute("userSession") != null) {
            session.setAttribute("userSession", null);
        }
        model.addAttribute("roomBooking", new Roombooking());

        model.addAttribute("room", new Roomtype());
        model.addAttribute("user", new User());

        session.setAttribute("pageRedirect", request.getHeader("Referer"));

        return "index";

    }
}
