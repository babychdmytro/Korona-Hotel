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
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Julia
 */

@Controller  
@SessionAttributes({"bookingSession", "userSession", "availableRoomListSession", "pageRedirect"})
@RequestMapping("/first.htm") 
public class Initial {
  @RequestMapping(method = RequestMethod.GET)
   public String root(ModelMap model, HttpSession session, HttpServletRequest request){
    model.addAttribute("roomBooking", new Roombooking()); 
  
    model.addAttribute("room", new Roomtype()); 
    model.addAttribute("user", new User());
  //  session.setAttribute("userSession", "Text");
    session.setAttribute("pageRedirect", request.getHeader("Referer"));
     // model.addAttribute("lists", Lists.getList());
   
   
  //  if(database.getUserFirstName() == null)
 //   user.setFirstName("this has failed horribly");
  //  else
   //  user.setFirstName(database.getUserFirstName());
     
  
    return "index";
     
     
    }
}
