/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Roombooking;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Julia
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@Stateful
public class UserLoginSessionBean implements UserLoginSessionBeanRemote {

    @EJB
    private UpdateBookingSessionBeanRemote updateBookingSessionBean;

    @PersistenceContext(unitName="KoronaProject-ejbPU")
     private EntityManager em;
    
    

    

    @Override
    public boolean checkLogin(String username, String password) {
        User user = new User();
        try{
        user = (User) em.createNamedQuery("User.findByEmail").setParameter("email", username).getSingleResult(); 
        return user.getEmail().equals(username) && user.getPassword().equals(password);
      
        } catch(NoResultException e) {
            return false;
        }
    }
    
    @Override
    public boolean checkAdminLogin(String username, String password) {
        User user = new User();
        try{
        user = (User) em.createNamedQuery("User.findAdminByEmail").setParameter("email", username).setParameter("role", "admin").getSingleResult(); 
        return user.getEmail().equals(username) && user.getPassword().equals(password);
      
        } catch(NoResultException e) {
            return false;
        }
    }
    
    

   
}
