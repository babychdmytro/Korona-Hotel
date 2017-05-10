/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Roombooking;
import entity.Roomtype;
import entity.User;
import java.util.List;
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
import session.RegistrationSessionBeanRemote;


/**
 *
 * @author Dmytro
 */
@Controller
@RequestMapping("/registration.htm")
public class RegisterUser {
    
    @RequestMapping(method = RequestMethod.GET)
    public String root(ModelMap model, HttpSession session){
   
       model.addAttribute("user", new User()); 
       return "registration";
   
   }
       
    @RequestMapping(method=RequestMethod.POST)
    public String processUserRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session, HttpServletRequest request) throws NamingException{
        
        if(bindingResult.hasErrors()) {
            return "index";
        }
        InitialContext ic = new InitialContext();
        Object o = ic.lookup(RegistrationSessionBeanRemote.class.getName());
        RegistrationSessionBeanRemote registrationBean = (RegistrationSessionBeanRemote) o;

        String confirmedPassword = request.getParameter("confirmUserPassword");
        
        List<User> emails = (List) registrationBean.getAllUsersByEmail(user.getEmail());
        if(!emails.isEmpty()){
            String errorMsg = "User with this email already exists";
            modelMap.addAttribute("registrationError", errorMsg);
            return "registration";
        }else if("".equals(user.getEmail().trim()) || "".equals(user.getPassword().trim()) || "".equals(confirmedPassword.trim()) || "".equals(user.getFirstName().trim()) || "".equals(user.getLastName().trim()) 
                || "".equals(user.getAddress().trim()) || "".equals(user.getCity().trim()) || "".equals(user.getCountry().trim()) || "".equals(user.getPostalCode().trim()) 
                || "".equals(user.getPhoneNumber().trim())){
            String errorMsg = "All fields should be completed";
            modelMap.addAttribute("registrationError", errorMsg);
            return "registration";
        }else if(user.getPassword().length() < 6){
            String errorMsg = "Password should have at least 6 characters";
            modelMap.addAttribute("registrationError", errorMsg);
            return "registration";
        }else if(!user.getPassword().equals(confirmedPassword)){
            String errorMsg = "Passwords don't";
            modelMap.addAttribute("registrationError", errorMsg);
            return "registration";
        }else if(!user.getPhoneNumber().matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
            String errorMsg = "Phone number should be in XXX-XXX-XXXX format";
            modelMap.addAttribute("registrationError", errorMsg);
            return "registration";
        }else{
            registrationBean.registerUser(user);
            modelMap.addAttribute("roomBooking", new Roombooking()); 
            modelMap.addAttribute("room", new Roomtype());
            return "index";
        }
            
        
        
    }
}