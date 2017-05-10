/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Room;
import entity.Roombooking;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dmytro
 */
@Stateless
public class ReportSessionBean implements ReportSessionBeanRemote {

    @PersistenceContext(unitName="KoronaProject-ejbPU")
    private EntityManager em;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List<Object> getRoomsWithinDatesRange(Date checkIn, Date checkOut){
        
       List<Roombooking> room= em.createNamedQuery("Room.findRoomsWithinDates", Roombooking.class).setParameter("checkIn", checkIn).setParameter("checkOut", checkOut).getResultList();
       List<Object> rooms = (List)room;
       return rooms;
        
    }
    
    @Override
    public List<Object> getRoomsByFloor(int floor){
        
       List<Room> room= em.createNamedQuery("Room.findByFloor", Room.class).setParameter("floor", floor).getResultList();
       List<Object> rooms = (List)room;
       return rooms;
        
    }
    
    @Override
    public List<Object> getUnpaidRooms(){
        
       List<Room> roomBooking= em.createNamedQuery("Roombooking.findUnpaidRooms", Room.class).setParameter("transaction", false).getResultList();
       List<Object> roomBookings = (List)roomBooking;
       return roomBookings;
        
    }
    
    @Override
    public List<Object> getRoomsByRoomtype(int roomTypeId){
        
       List<Room> room= em.createNamedQuery("Room.findRoomsByRoomType", Room.class).setParameter("roomTypeId", roomTypeId).getResultList();
       List<Object> rooms = (List)room;
       return rooms;
        
    }
    
}
