/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Room;
import entity.Roombooking;
import entity.Roomtype;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import session.ReportSessionBeanRemote;

/**
 *
 * @author Dmytro
 */
@Controller
public class Report {
    
    @RequestMapping(value = {"/reporstForm.htm"}, method = RequestMethod.GET)
    public String openReportsForm(ModelMap model, HttpSession session) throws NamingException {
    
        model.addAttribute("roomBooking", new Roombooking());
        model.addAttribute("room", new Room());
        model.addAttribute("roomType", new Roomtype());
        return "adminReports";
        
    }
    
    
    @RequestMapping(value = {"/processDateReport.htm"}, method = RequestMethod.POST)
    public ModelAndView generateReportByDates(@ModelAttribute("roomBooking") Roombooking roomBooking, ModelMap model, HttpSession session) throws NamingException {
    
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(ReportSessionBeanRemote.class.getName());
        ReportSessionBeanRemote reportsBean = (ReportSessionBeanRemote) o;
        
        List<Room> roomsList = (List) reportsBean.getRoomsWithinDatesRange(roomBooking.getCheckIn(), roomBooking.getCheckOut());
        
//        model.addAttribute("roomsByDateList", roomsList);
//        model.addAttribute("roomBooking", new Roombooking());
//        model.addAttribute("room", new Room());
//        
//        return "adminReports";

        return new ModelAndView("AdminReport","dateReport",roomsList);
        
    }
    
    
    @RequestMapping(value = {"/processFloorReport.htm"}, method = RequestMethod.POST)
    public ModelAndView generateReportByFloor(@ModelAttribute("room") Room room, ModelMap model, HttpSession session) throws NamingException {
        
        
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(ReportSessionBeanRemote.class.getName());
        ReportSessionBeanRemote reportsBean = (ReportSessionBeanRemote) o;
        
        List<Room> roomsList = (List) reportsBean.getRoomsByFloor(room.getFloor());
        
//        model.addAttribute("roomsByDateList", roomsList);
//        model.addAttribute("roomBooking", new Roombooking());
//        
//        model.addAttribute("room", new Room());
//        
//        return "adminReports";
    
        return new ModelAndView("AdminReport","floorReport",roomsList);
    }
    
    
    @RequestMapping(value = {"/processUnpaidBookingsReport.htm"}, method = RequestMethod.POST)
    public ModelAndView generateReportOfUnpaidRooms(@ModelAttribute("room") Room room, ModelMap model, HttpSession session) throws NamingException {
     
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(ReportSessionBeanRemote.class.getName());
        ReportSessionBeanRemote reportsBean = (ReportSessionBeanRemote) o;
        
        List<Roombooking> unpaidRoomBookingsList = (List) reportsBean.getUnpaidRooms();
        
//        model.addAttribute("unpaidRoomsList", unpaidRoomsList);
//        List<Room> roomsList = new ArrayList<>();
//        model.addAttribute("roomsByDateList", roomsList);
//        model.addAttribute("roomBooking", new Roombooking());
//        
//        model.addAttribute("room", new Room());
//        
//        return "adminReports";

        return new ModelAndView("AdminReport","unpaidRoomsReport",unpaidRoomBookingsList);
        
    }
    

    
      @RequestMapping(value = {"/roomTypeBookingsReport.htm"}, method = RequestMethod.POST)
        public ModelAndView generateReportOfRoomType(@ModelAttribute("roomType") Roomtype roomType, ModelMap model, HttpSession session) throws NamingException {
     
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(ReportSessionBeanRemote.class.getName());
        ReportSessionBeanRemote reportsBean = (ReportSessionBeanRemote) o;
        
        List<Room> roomList = (List) reportsBean.getRoomsByRoomtype(roomType.getRoomTypeId().intValue());
        
//        model.addAttribute("unpaidRoomsList", unpaidRoomsList);
//        List<Room> roomsList = new ArrayList<>();
//        model.addAttribute("roomsByDateList", roomsList);
//        model.addAttribute("roomBooking", new Roombooking());
//        
//        model.addAttribute("room", new Room());
//        
//        return "adminReports";

        return new ModelAndView("AdminReport","roomsByRoomType",roomList);
        
    }
    
}
