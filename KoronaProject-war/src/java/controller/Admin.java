/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Room;
import entity.Roomtype;
import entity.User;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import session.RoomSessionBeanRemote;
import session.UserLoginSessionBeanRemote;

/**
 *
 * @author Dmytro
 */
@Controller
@SessionAttributes("sessionAdmin")
public class Admin {

    @RequestMapping(value = {"/loginAdmin.htm"}, method = RequestMethod.GET)
    public String login( ModelMap model, HttpSession session) throws NamingException {
        model.addAttribute("user", new User());
        return "adminLogin";

    }
    
    @RequestMapping(value = {"/loginAdminCheck.htm"}, method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute("user") User user, ModelMap model, HttpSession session) throws NamingException {
        
        String redirect = "";
        
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(UserLoginSessionBeanRemote.class.getName());
        UserLoginSessionBeanRemote loginBean = (UserLoginSessionBeanRemote) o;
        if (loginBean.checkAdminLogin(user.getEmail(), user.getPassword())){
        Object obj = ic.lookup(RoomSessionBeanRemote.class.getName());
        RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) obj;
        model.addAttribute("roomsList", roomBean.getAllRooms());
        model.addAttribute("roomType", new Roomtype());
        model.addAttribute("room", new Room());
        session.setAttribute("sessionAdmin", user.getEmail());
        redirect = "adminMain";
        }
        else {
            model.addAttribute("errorMessage", "Access Denied");
            model.addAttribute("user", new User());
            redirect = "adminLogin";
        }
       
        return redirect;

    }
    
    
    @RequestMapping(value = {"/admin.htm"}, method = RequestMethod.GET)
    public String root( ModelMap model, HttpSession session) throws NamingException {

        InitialContext ic = new InitialContext();
        Object o = ic.lookup(RoomSessionBeanRemote.class.getName());
        RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) o;

        model.addAttribute("roomsList", roomBean.getAllRooms());
        model.addAttribute("roomType", new Roomtype());
        model.addAttribute("room", new Room());
        return "adminMain";

    }
    
    @RequestMapping(value = {"/addNewRoomForm.htm"}, method = RequestMethod.GET)
    public String root2(ModelMap model, HttpSession session) throws NamingException {

        model.addAttribute("roomType", new Roomtype());
        model.addAttribute("room", new Room());
        model.addAttribute("confirmButton", "Add new room");
        model.addAttribute("action", "addNewRoom.htm");
        return "adminForm";

    }
    
   
    @RequestMapping(value = {"/addNewRoom.htm"}, method = RequestMethod.POST)
    public String addNewRoom(@Valid @ModelAttribute("room") Room room, @Valid @ModelAttribute("roomType") Roomtype roomType, BindingResult bindingResult, ModelMap modelMap, HttpSession session, HttpServletRequest request) throws NamingException {

        Date date = new Date();
        
        room.setRoomType(roomType);
        room.setDateCreated(date);
        room.setDateModified(date);
        
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("roomType", new Roomtype());
            modelMap.addAttribute("room", new Room());
            return "adminForm";
        }
        
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(RoomSessionBeanRemote.class.getName());
        RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) o;
        
        roomBean.addRoom(room);
        modelMap.addAttribute("roomsList", roomBean.getAllRooms());
        modelMap.addAttribute("room", new Room());
        return "adminMain";

    }
    
    
    @RequestMapping(value = {"/editRoom.htm"}, method = RequestMethod.POST)
    public String editRoom(@ModelAttribute("room") Room room, @ModelAttribute("roomType") Roomtype roomType, BindingResult bindingResult, ModelMap modelMap, HttpSession session, HttpServletRequest request) throws NamingException {
    
        if(request.getParameter("delete") != null){
            InitialContext ic = new InitialContext();
            Object o = ic.lookup(RoomSessionBeanRemote.class.getName());
            RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) o;

            roomBean.deleteRoom(Integer.parseInt(request.getParameter("delete")));


            modelMap.addAttribute("roomsList", roomBean.getAllRooms());
            return "adminMain";
            
        }else{
            
            //room.setName(room.getName());
            //modelMap.addAttribute("editName", room.getName());
            
            modelMap.addAttribute("roomTypeId", roomType.getRoomTypeId());
            modelMap.addAttribute("room", room);
            modelMap.addAttribute("confirmButton", "Update room");
            modelMap.addAttribute("action", "updateRoom.htm");
            
            return "adminForm";
        }
        
    }
    
    @RequestMapping(value = {"/updateRoom.htm"}, method = RequestMethod.POST)
    public String updateRoom(@ModelAttribute("room") Room room, @ModelAttribute("roomType") Roomtype roomType, BindingResult bindingResult, ModelMap modelMap, HttpSession session, HttpServletRequest request) throws NamingException {
    
        Date date = new Date();
        
        room.setRoomType(roomType);
        room.setDateModified(date);
        
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(RoomSessionBeanRemote.class.getName());
        RoomSessionBeanRemote roomBean = (RoomSessionBeanRemote) o;
        
        roomBean.editRoom(room);
        
        modelMap.addAttribute("roomsList", roomBean.getAllRooms());
        
        return "adminMain";
    }
    
    @RequestMapping(value = {"/logOut.htm"}, method = RequestMethod.GET)
    public String logout(ModelMap modelMap, HttpSession session, HttpServletRequest request) throws NamingException {
    
        session.setAttribute("sessionAdmin", null);
        modelMap.addAttribute("user", new User());
            
        return "adminLogin";
    }
    
    
  
    
}
