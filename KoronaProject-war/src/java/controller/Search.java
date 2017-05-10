/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Room;
import entity.Roombooking;
import entity.Roomtype;
import entity.User;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import session.SearchSessionBeanRemote;
import session.UserLoginSessionBeanRemote;

/**
 *
 * @author Julia
 */
@Controller
@RequestMapping("/submitCheck.htm")
public class Search {

//      @InitBinder
//    public void setAllowedFields(WebDataBinder dataBinder) {
//        System.out.println(dataBinder.getValidator());
//        dataBinder.setDisallowedFields("roomId");
//    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSearchRequest(@Valid @ModelAttribute("roomBooking") Roombooking booking, @Valid @ModelAttribute("room") Roomtype room, BindingResult bindingResult, ModelMap modelMap, HttpSession session) throws NamingException {

        // long difference = booking.getCheckOut().getTime()-booking.getCheckIn().getTime();
        Date date = new Date();
        if (bindingResult.hasErrors()) {
            return "index";
        } else if (booking.getCheckIn() == null || booking.getCheckOut() == null) {
            String errorMsg = "Please, specify check in and check out date";
            modelMap.addAttribute("errorMsg", errorMsg);
            modelMap.addAttribute("user", new User());
            return "index";
        } else if (booking.getCheckIn().before(date)) {
            String errorMsg = "Date must be in the future";
            modelMap.addAttribute("errorMsg", errorMsg);
            modelMap.addAttribute("user", new User());
            return "index";
        } else if (TimeUnit.DAYS.convert(booking.getCheckOut().getTime() - booking.getCheckIn().getTime(), TimeUnit.MILLISECONDS) <= 0) {
            String errorMsg = "Check in date must be before check out date";
            modelMap.addAttribute("errorMsg", errorMsg);
            modelMap.addAttribute("user", new User());
            return "index";
        } else {
            //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //    dateFormat.format(booking.getCheckin());

            InitialContext ic = new InitialContext();
            Object o = ic.lookup(SearchSessionBeanRemote.class.getName());
            SearchSessionBeanRemote searchBean = (SearchSessionBeanRemote) o;

            List<Room> availableRoomList = (List) searchBean.userSearchRoom(booking.getCheckIn(), booking.getCheckOut(),
                    booking.getNumPeople(), room.getRoomTypeId().intValue());

            if (availableRoomList.isEmpty()) {
                modelMap.addAttribute("errorMessage", "Sorry, no rooms are available at this time. Please choose new dates or call us for help");
            }

            modelMap.addAttribute("availableRoom", availableRoomList);
            session.setAttribute("availableRoomListSession", availableRoomList);
            modelMap.addAttribute("room", new Room());
            session.setAttribute("bookingSession", booking);
            modelMap.addAttribute("bookingInfo", booking);
            modelMap.addAttribute("user", new User());
            return "searchResults";

            //create a session variable
        }

    }

}
