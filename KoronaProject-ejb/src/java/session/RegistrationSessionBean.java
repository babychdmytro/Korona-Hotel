/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Julia
 */

@Stateless
public class RegistrationSessionBean implements RegistrationSessionBeanRemote {

    Date date;
    @PersistenceContext(unitName="KoronaProject-ejbPU")
    private EntityManager em;
    
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /*
    @Override
    public boolean registerUser(String email, String password, String firstName, String lastName, String address, String city, String country, String postalCode, String phoneNumber) {
         
        boolean flag = false;
        date = new Date();

        User user = new User();
        
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setCity(city);
        user.setCountry(country);
        user.setPostalCode(postalCode);
        user.setPhoneNumber(phoneNumber);
        user.setDateCreated(date);
        user.setRole("user");
        
        em.persist(user);
        
        if (em.contains(user)){
            flag=true;
        }
        
        return flag;
    }
    */
    
     @Override
    public boolean registerUser(Object newUser) {
         
        boolean flag = false;
        date = new Date();
        User nUser = (User) newUser;
        User user = new User();
        
        user.setEmail(nUser.getEmail());
        user.setPassword(nUser.getPassword());
        user.setFirstName(nUser.getFirstName());
        user.setLastName(nUser.getLastName());
        user.setAddress(nUser.getAddress());
        user.setCity(nUser.getCity());
        user.setCountry(nUser.getCountry());
        user.setPostalCode(nUser.getPostalCode());
        user.setPhoneNumber(nUser.getPostalCode());
        user.setDateCreated(date);
        user.setRole("user");
        
        em.persist(user);
        
        if (em.contains(user)){
            flag=true;
        }
        
        return flag;
    }
    
    
    @Override
    public List<Object> getAllUsersByEmail(String email) {
       List<User> user= em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getResultList();
       List<Object> users = (List)user;
       
       return users;
    }
}
