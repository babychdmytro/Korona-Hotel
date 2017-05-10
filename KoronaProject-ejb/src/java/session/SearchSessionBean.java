/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Room;
import entity.Roombooking;
import entity.Roomtype;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
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
@Stateless
public class SearchSessionBean implements SearchSessionBeanRemote {

    //interceptors
    
    @PersistenceContext(unitName="KoronaProject-ejbPU")
     private EntityManager em;
    
    
     /**
      * Main search for the room (user search)
      * @param checkIn 
      * @param checkOut
      * @param numberOfPeople
      * @param roomTypeName - converts to the room type object
      * @return list of available rooms
      */
    @Override
    public List<Object> userSearchRoom(Date checkIn, Date checkOut, int numberOfPeople, int roomTypeName) {
       //list fo rooms that will be populated after search
        List<Room> availableRooms = new ArrayList<Room>();
        
        //return the roomtype object depending on the user's choice
      //  Roomtype roomType = (Roomtype) findRoomTypeId(roomTypeName);
      
      List<Room> r =  em.createNamedQuery("Room.findRoomsByRoomType", Room.class)
              .setParameter("roomTypeId", roomTypeName)
              .getResultList();
      List<Roombooking> room = new ArrayList<Roombooking>();
      try{
        room =  (List) em.createNamedQuery("Roombooking.findByRoomId", Roombooking.class)
                .setParameter("roomId", r.get(0))
                .getResultList();
      }
      catch (NoResultException ex){
          //list will remain the same
      }
        if (room.isEmpty())    {
             availableRooms = em.createNamedQuery("Room.findAvailableRoomsFullNotBooked", Room.class)
                .setParameter("roomType", roomTypeName)
                .setParameter("capacity", numberOfPeople)
                .getResultList();
        }    
        else{ 
        availableRooms = em.createNamedQuery("Room.findAvailableRoomsFull", Room.class)
                .setParameter("roomType", roomTypeName)
                .setParameter("capacity", numberOfPeople)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut)
                .getResultList();
        }
        //casting to List<Object>
        return (List)availableRooms;
        
    }

    
      /**
     * Returns an id of the room depending on the string name. Can be used for searching in the room table,
     * as it contains the number not the name
     * @param roomTypeName name of the room type
     * @return room type id
     */
    @Override
    public Object findRoomTypeId(String roomTypeName) {
        Object rt;
        rt = em.createNamedQuery("Roomtype.findRoomIdByRoomTypeName").setParameter("roomTypeName", roomTypeName).getSingleResult();
        return rt;
       
    }

    //to get the list of available options
    @Override
    public List<Object> getAllRoomType() {
        return (List)em.createNamedQuery("Roomtype.findAll", Roomtype.class).getResultList();
    }

    @Override
    public boolean userSearchRoomEdit(Date checkIn, Date checkOut, int numberOfPeople, int bookingId) {
        
       boolean result=false;
       try{
       Room rb = em.createNamedQuery("Room.findAvailableRoomsEdit", Room.class)
                .setParameter("bookingId", bookingId)
                .setParameter("capacity", numberOfPeople)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut)
                .getSingleResult();
        if (rb!=null){
            result=true;
        }
        return result;
       }
       catch(NoResultException ex){
           return result;
       }
       
       
       
       
    }

    @Override
    public double findRoomPriceByRoomBooking(int roomBookingId) {
        Room room = em.createNamedQuery("Room.findByRoomBookingId", Room.class)
                .setParameter("roomBookingId", roomBookingId)
                .getSingleResult();
     
        return room.getRoomPrice().doubleValue();
    }

    @Override
    public double findRoomBookingPriceByBooking(int bookingId) {
        Roombooking rb = em.createNamedQuery("Roombooking.findByRoomBookingId", Roombooking.class)
                .setParameter("roomBookingId", bookingId)
                .getSingleResult();
        return rb.getPriceTotal().doubleValue();
    }

    @Override
    public Object findUserByEmail(String email) {
        return em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
    
    
    
    
    
    
    
}
