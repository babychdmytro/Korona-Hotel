/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julia
 */
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByRoomId", query = "SELECT r FROM Room r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Room.findByName", query = "SELECT r FROM Room r WHERE r.name = :name"),
    @NamedQuery(name = "Room.findByDescription", query = "SELECT r FROM Room r WHERE r.description = :description"),
    @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor"),
    @NamedQuery(name = "Room.findByRoomPrice", query = "SELECT r FROM Room r WHERE r.roomPrice = :roomPrice"),
    @NamedQuery(name = "Room.findByCapacity", query = "SELECT r FROM Room r WHERE r.capacity = :capacity"),
    @NamedQuery(name = "Room.findByImage", query = "SELECT r FROM Room r WHERE r.image = :image"),
    @NamedQuery(name = "Room.findByDateCreated", query = "SELECT r FROM Room r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "Room.findByDateModified", query = "SELECT r FROM Room r WHERE r.dateModified = :dateModified"),
    @NamedQuery(name = "Room.findAvailableRoomsFull", query = "SELECT DISTINCT r FROM Roombooking rb join rb.roomId r join r.roomType rt WHERE rt.roomTypeId=:roomType and r.capacity>=:capacity and r not in (Select r from Roombooking rb join rb.roomId r where (rb.checkOut>=:checkIn and rb.checkIn<=:checkOut)) "),
    @NamedQuery(name = "Room.findAvailableRoomsFullNotBooked", query = "SELECT DISTINCT r FROM  Room r join r.roomType rt WHERE rt.roomTypeId=:roomType and r.capacity>=:capacity"),
    @NamedQuery(name = "Room.findAvailableRoomsEdit", query = "SELECT DISTINCT r FROM Roombooking rb join rb.roomId r join rb.bookingId b WHERE rb.roomBookingId=:bookingId and r.capacity>=:capacity and r not in (Select r from Room r join Roombooking rb where (rb.checkOut>=:checkIn and rb.checkIn<=:checkOut)) "),
    @NamedQuery(name = "Room.findByRoomBookingId", query = "SELECT DISTINCT r FROM Roombooking rb  join rb.roomId r WHERE rb.roomBookingId=:roomBookingId"),
    @NamedQuery(name = "Room.findRoomsWithinDates", query = "SELECT rb FROM Roombooking rb JOIN rb.roomId r WHERE (rb.checkOut>=:checkIn AND rb.checkIn<=:checkOut)"),
    @NamedQuery(name = "Room.findRoomsByRoomType", query = "SELECT r FROM Room r JOIN r.roomType rt WHERE rt.roomTypeId=:roomTypeId")})


  //  @NamedQuery(name = "Room.findAvailableRoomsFull", query = "SELECT DISTINCT r FROM Roombooking rb join rb.roomId r join r.roomType rt WHERE rt.roomTypeId=:roomType and r.capacity>=:capacity and r not in (Select r from Room r join Roombooking rb where (rb.checkOut>=:checkIn and rb.checkIn<=:checkOut))

public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "room_id")
    private Long roomId;
    @Basic(optional = false)
    // @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    //  @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    //   @NotNull
    @Column(name = "floor")
    private int floor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    //   @NotNull
    @Column(name = "room_price")
    private BigDecimal roomPrice;
    @Basic(optional = false)
    //   @NotNull
    @Column(name = "capacity")
    private int capacity;
    @Basic(optional = false)
    //  @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    //   @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    //   @NotNull
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private Collection<Roombooking> roombookingCollection;
    @JoinColumn(name = "room_type", referencedColumnName = "room_type_id")
    @ManyToOne(optional = false)
    private Roomtype roomType;

    public Room() {
    }

    public Room(Long roomId) {
        this.roomId = roomId;
    }

    public Room(Long roomId, String name, String description, int floor, BigDecimal roomPrice, int capacity, String image, Date dateCreated, Date dateModified) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.floor = floor;
        this.roomPrice = roomPrice;
        this.capacity = capacity;
        this.image = image;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @XmlTransient
    public Collection<Roombooking> getRoombookingCollection() {
        return roombookingCollection;
    }

    public void setRoombookingCollection(Collection<Roombooking> roombookingCollection) {
        this.roombookingCollection = roombookingCollection;
    }

    public Roomtype getRoomType() {
        return roomType;
    }

    public void setRoomType(Roomtype roomType) {
        this.roomType = roomType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Room[ roomId=" + roomId + " ]";
    }

}
