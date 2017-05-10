/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Booking;
import entity.Room;
import entity.Roombooking;
import entity.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Julia
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@Stateful
public class BookingSessionBean implements BookingSessionBeanRemote {

    @EJB
    private UpdateBookingSessionBeanRemote updateBookingSessionBean;

    @EJB
    private PriceSessionBeanRemote priceSessionBean;

    @PersistenceContext(unitName = "KoronaProject-ejbPU")
    private EntityManager em;

    List<Roombooking> bookedRoomsList;
    Roombooking rbook;
    Booking booking;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Object getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

//initializing of the list
    @PostConstruct
    private void init() {
       
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
    
//adding room to the cart 
    @Override
    public List<Object> addRoomBooking(int roomId, Date checkIn, Date checkOut, int numberOfPeople, String email) {
        //find the room
        Room room = em.createNamedQuery("Room.findByRoomId", Room.class)
                .setParameter("roomId", roomId).getSingleResult();
    User user = em.createNamedQuery("User.findByEmail", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
    getFalseTransaction(user);
        //create a new booking if booking object is null
        if (booking.getBookingId() == null) {

            //find a user depending on the user id
        

            booking.setUserId(user);
            booking.setTransaction(false);
            booking.setDateCreated(new Date());
            booking.setDateModified(new Date());
            em.persist(booking);
            em.flush();

        }

        rbook = new Roombooking();
        rbook.setBookingId(booking);
        rbook.setRoomId(room);
        rbook.setCheckIn(checkIn);
        rbook.setCheckOut(checkOut);
        rbook.setNumPeople(numberOfPeople);
        rbook.setPriceTotal(BigDecimal.valueOf(priceSessionBean.calculateRoomPrice(checkIn, checkOut, room.getRoomPrice().doubleValue())));
        rbook.setDateCreated(new Date());
        rbook.setDateModified(new Date());
        em.persist(rbook);
        em.flush();
        bookedRoomsList.add(rbook);
        return (List) bookedRoomsList;
    }

    @Override
    public void checkOut(Object bookingNew) {

        //converting the values
        Roombooking rb;
        Booking bk = (Booking) bookingNew;

        //set booking
        Booking newBk = em.find(Booking.class, bk.getBookingId());
        newBk.setPriceTotal(BigDecimal.valueOf(getTotalPrice(bk.getUserId())));
        newBk.setCreditCardType(bk.getCreditCardType());
        newBk.setCreditCardNumber(bk.getCreditCardNumber());
        newBk.setCvv(bk.getCvv());
        newBk.setExpMonth(bk.getExpMonth());
        newBk.setExpYear(bk.getExpYear());
        newBk.setTransaction(true);
        newBk.setDateModified(new Date());
        em.persist(newBk);
        em.flush();

        //save to roomBooking
        for (int i = 0; i < bookedRoomsList.size(); i++) {

            rb = bookedRoomsList.get(i);
            Roombooking newRb = em.find(Roombooking.class, rb.getRoomBookingId());
            newRb.setDateModified(new Date());
            newRb.setSpecialRequests("No requests");
            em.persist(newRb);
            em.flush();
        }

        bookedRoomsList.clear();

    }

    @Override
    public double getTotalPrice(Object userName) {
        double price = 0;
      //  bookedRoomsList = (List) bookingList;
        //bookedRoomsList = (List) getFalseTransaction(userName);
        for (int i = 0; i < bookedRoomsList.size(); i++) {
            price += bookedRoomsList.get(i).getPriceTotal().doubleValue();
        }
        return price;
    }

    @Override
    public List<Object> getFalseTransaction(Object user) {
        
        bookedRoomsList = (List) em.createNamedQuery("Roombooking.findTranscationUser")
                .setParameter("transaction", false)
                .setParameter("userId", user)
                .getResultList();
    
        //bookedRoomsList = (List) updateBookingSessionBean.getListFalseTransaction();
        //create new booking
        if (!bookedRoomsList.isEmpty()) {
            booking = bookedRoomsList.get(0).getBookingId();
        } else {
            booking = new Booking();
        }
        return (List) bookedRoomsList;
    }

}
