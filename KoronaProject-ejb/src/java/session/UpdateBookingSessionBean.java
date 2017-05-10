/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Booking;
import entity.Roombooking;
import entity.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Stateful
public class UpdateBookingSessionBean implements UpdateBookingSessionBeanRemote {

    @PersistenceContext(unitName = "KoronaProject-ejbPU")
    private EntityManager em;

    private List<Roombooking> bookings;
    private User user;
    private List<Booking> lsTransactionFalse;
    public  List<Roombooking> lsFalse;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

   

    @PostConstruct
    private void init() {
        bookings = new ArrayList<Roombooking>();
        lsFalse = new ArrayList<Roombooking>();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Object> getBookingList(String userName) {
        User user = new User();
        user = (User) em.createNamedQuery("User.findByEmail").setParameter("email", userName).getSingleResult();
        bookings = ((List) em.createNamedQuery("Roombooking.findByUserId").setParameter("userId", user).getResultList());
        return (List) bookings;
    }

    @Override
    public void setBookingList(List list) {
        bookings = list;
    }

    
    
    //is available
    //update
    @Override
    public void deleteBooking(Object booking) {

        Roombooking b = (Roombooking) booking;
        Roombooking rb = (Roombooking) em.find(Roombooking.class, b.getRoomBookingId());
        em.remove(rb);

        //delete from the list
        bookings.remove(rb);

         Booking oldB = (Booking) em.createNamedQuery("Booking.findByBookingId", Booking.class)
             .setParameter("bookingId",  rb.getBookingId().getBookingId())
             .getSingleResult();
    
    //calculating new price
 //   double newPriceBooking = oldB.getPriceTotal().doubleValue() - rb.getPriceTotal().doubleValue();
 //   oldB.setPriceTotal(BigDecimal.valueOf(newPriceBooking));
 //   oldB.setDateModified(new Date());
 //   em.persist(oldB);
  //  em.flush();
  
  
        // List<Booking> b = em.createNamedQuery("Booking.findByBookingId").setParameter("bookingId", rb.getBookingId())
        //        .getResultList();
        //  if (b.size()<=0){
        //em.remove(b.get(0));
        //}
    }
    
    
     @Override
    public void deletePreviousBooking(Object booking) {

        Roombooking b = (Roombooking) booking;
        Roombooking rb = (Roombooking) em.find(Roombooking.class, b.getRoomBookingId());
        em.remove(rb);

        //delete from the list
        bookings.remove(rb);

         Booking oldB = (Booking) em.createNamedQuery("Booking.findByBookingId", Booking.class)
             .setParameter("bookingId",  rb.getBookingId().getBookingId())
             .getSingleResult();
    
    //calculating new price
    double newPriceBooking = oldB.getPriceTotal().doubleValue() - rb.getPriceTotal().doubleValue();
    oldB.setPriceTotal(BigDecimal.valueOf(newPriceBooking));
    oldB.setDateModified(new Date());
    em.persist(oldB);
    em.flush();
  
  
        // List<Booking> b = em.createNamedQuery("Booking.findByBookingId").setParameter("bookingId", rb.getBookingId())
        //        .getResultList();
        //  if (b.size()<=0){
        //em.remove(b.get(0));
        //}
    }

    @Override
    public List<Object> getBookingTransactionTrue() {
     //  User user = new User();
     //  user = (User) userName;
     //   user = (User) em.createNamedQuery("User.findByEmail").setParameter("email", user.getEmail()).getSingleResult(); 
        List<Booking> bookingsA = ((List) em.createNamedQuery("Roombooking.findTranscation").setParameter("transaction", true)
                .getResultList());
        List<Roombooking> lsTrue = new ArrayList<Roombooking>();
        
    /*    for (int i=0; i<bookingsA.size(); i++){
            for (int j=0; j<bookings.size(); j++){
            if (bookings.get(j).getBookingId().equals(bookingsA.get(i))){
                lsTrue.add(bookingsA.get(i));
            }
        }
        } */
    
    for (int i=0; i<bookings.size(); i++){
       if (bookings.get(i).getBookingId().getTransaction()){
           lsTrue.add(bookings.get(i));
        }
    }
        return (List) lsTrue;
    }

    @Override
    public List<Object> getBookingTransactionFalse() {
       // User user = new User();
       //user = (User) userName;
      //  user = (User) em.createNamedQuery("User.findByEmail").setParameter("email", user.getEmail()).getSingleResult(); 
        List<Booking> bookingsP = ((List) em.createNamedQuery("Roombooking.findTranscation").setParameter("transaction", false)
                .getResultList());
        lsTransactionFalse = bookingsP;
        List<Roombooking> lsFalse = new ArrayList<Roombooking>();
        
      /*  for (int i=0; i<bookingsP.size(); i++){
            for (int j=0; j<bookings.size(); j++){
            if (bookingsP.get(i).equals(bookings.get(j).getBookingId())){
                lsFalse.add(bookingsP.get(i));
            }
        }
        }*/
       for (int i=0; i<bookings.size(); i++){
        if (!bookings.get(i).getBookingId().getTransaction()){
           lsFalse.add(bookings.get(i));
        }
    }
        return (List) lsFalse;
    }

    @Override
    public Object finbBookingByRoomId(int roomBookingId) {
        Booking bookingId=null;
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getRoomBookingId().intValue() == roomBookingId) {
                bookingId = bookings.get(i).getBookingId();
            }
        }
        return (Object)bookingId;
    }
    
    
    
    // String credit_card_type, String credit_card_number, int cvv, int exp_month, int exp_year

    @Override
    public void updateCheckOut(Object roomBooking, Object bookingId, double newPrice) {
    
     Roombooking bookingForEdit = (Roombooking) roomBooking;  
        
     Roombooking oldRb = (Roombooking) em.createNamedQuery("Roombooking.findByRoomBookingId", Roombooking.class)
             .setParameter("roomBookingId",  bookingForEdit.getRoomBookingId())
             .getSingleResult();
     
    Booking b = (Booking) bookingId;    
    
    Booking oldB = (Booking) em.createNamedQuery("Booking.findByBookingId", Booking.class)
             .setParameter("bookingId",  b.getBookingId())
             .getSingleResult();
    
    //calculating new price
    double newPriceBooking = oldB.getPriceTotal().doubleValue() - oldRb.getPriceTotal().doubleValue() + newPrice;
    
    
    //updare roomBooking
   
    
    oldRb.setCheckIn(bookingForEdit.getCheckIn());
    oldRb.setCheckOut(bookingForEdit.getCheckOut());
    oldRb.setNumPeople(bookingForEdit.getNumPeople());
    oldRb.setPriceTotal(BigDecimal.valueOf(newPrice));
    oldRb.setDateModified(new Date());
    em.persist(oldRb);
    em.flush();   
    
    
    //update booking
    
    oldB.setCreditCardNumber(b.getCreditCardNumber());
    oldB.setCreditCardType(b.getCreditCardType());
    oldB.setCvv(b.getCvv());
    oldB.setExpMonth(b.getExpMonth());
    oldB.setExpYear(b.getExpYear());
    oldB.setPriceTotal(BigDecimal.valueOf(newPriceBooking));
    oldB.setDateModified(new Date());
    oldB.setTransaction(true);
    em.persist(oldB);
    em.flush();
    
    }

    @Override
    public List<Object> getBookingListTransactionFalse() {
        return (List)lsTransactionFalse;
    }

    @Override
    public List<Object> getListFalseTransaction() {
       return (List) lsFalse;
    }

    
 
    
    
    
}
