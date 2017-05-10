/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;


import entity.Room;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Julia
 */
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@Stateless
public class RoomSessionBean implements RoomSessionBeanRemote {

     @PersistenceContext(unitName="KoronaProject-ejbPU")
     private EntityManager em;

         /**
      * Add a new room
      * @param newRoom - contains the information about new room
      */
    @Override
    public void addRoom(Object newRoom) {
       em.persist((Room) newRoom); 
    }

        /**
     * Update a room depending on id
     * @param newRoom 
     */
    @Override
    public void editRoom(Object newRoom) {
        Room updatedRoom = (Room)newRoom;
        Room oldRoom = (Room)searchRoom(updatedRoom.getRoomId().intValue());
        oldRoom.setName(updatedRoom.getName());
        oldRoom.setDescription(updatedRoom.getDescription());
        oldRoom.setRoomType(updatedRoom.getRoomType());
        oldRoom.setFloor(updatedRoom.getFloor());
        oldRoom.setRoomPrice(updatedRoom.getRoomPrice());
        oldRoom.setCapacity(updatedRoom.getCapacity());
        em.persist(oldRoom);
    }

        /**
     * Finds a room and removes it
     * @param id 
     */
    @Override
    public void deleteRoom(int id) {
        Room room = (Room)searchRoom(id); 
        em.remove(room);

    }

    /**
     * Search for a room by id
     * @param id
     * @return room if found
     */
    @Override
    public Object searchRoom(int id) {
        Long longId = new Long(id);
        Object room = (Object) em.find(Room.class, longId); 
        return room;
     

    }

    
    /**
     * Get a list of all rooms
     * @return list of rooms
     */
    @Override
    public List<Object> getAllRooms() {
       List<Room> room=em.createNamedQuery("Room.findAll", Room.class).getResultList();
       List<Object> rooms = (List)room;
       return rooms;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
