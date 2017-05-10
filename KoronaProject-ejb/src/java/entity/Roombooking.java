/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julia
 */
@Entity
@Table(name = "roombooking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roombooking.findAll", query = "SELECT r FROM Roombooking r"),
    @NamedQuery(name = "Roombooking.findByRoomBookingId", query = "SELECT r FROM Roombooking r WHERE r.roomBookingId = :roomBookingId"),
    @NamedQuery(name = "Roombooking.findByCheckIn", query = "SELECT r FROM Roombooking r WHERE r.checkIn = :checkIn"),
    @NamedQuery(name = "Roombooking.findByCheckOut", query = "SELECT r FROM Roombooking r WHERE r.checkOut = :checkOut"),
    @NamedQuery(name = "Roombooking.findByNumPeople", query = "SELECT r FROM Roombooking r WHERE r.numPeople = :numPeople"),
    @NamedQuery(name = "Roombooking.findByPriceTotal", query = "SELECT r FROM Roombooking r WHERE r.priceTotal = :priceTotal"),
    @NamedQuery(name = "Roombooking.findBySpecialRequests", query = "SELECT r FROM Roombooking r WHERE r.specialRequests = :specialRequests"),
    @NamedQuery(name = "Roombooking.findByDateCreated", query = "SELECT r FROM Roombooking r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "Roombooking.findByDateModified", query = "SELECT r FROM Roombooking r WHERE r.dateModified = :dateModified"),
     @NamedQuery(name = "Roombooking.findByRoomId", query = "SELECT r FROM Roombooking r WHERE r.roomId = :roomId"),
     @NamedQuery(name = "Roombooking.findByRoomTypeId", query = "SELECT r FROM Roombooking r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Roombooking.findByUserId", query = "SELECT DISTINCT r FROM Roombooking r JOIN r.bookingId b WHERE b.userId=:userId"),
    @NamedQuery(name = "Roombooking.findTranscation", query = "SELECT DISTINCT r FROM Roombooking r JOIN r.bookingId b WHERE b.transaction=:transaction"),
    @NamedQuery(name = "Roombooking.findTranscationUser", query = "SELECT DISTINCT r FROM Roombooking r JOIN r.bookingId b WHERE b.transaction=:transaction and b.userId=:userId"),
    @NamedQuery(name = "Roombooking.findPriceByBookingId", query = "SELECT DISTINCT r FROM Roombooking r JOIN r.bookingId b WHERE b.bookingId=:bookingId"),
    @NamedQuery(name = "Roombooking.findUnpaidBookings", query = "SELECT DISTINCT r FROM Roombooking r JOIN r.bookingId b WHERE b.transaction=:transaction"),
    @NamedQuery(name = "Roombooking.findUnpaidRooms", query = "SELECT rb FROM Roombooking rb JOIN rb.roomId r JOIN rb.bookingId b WHERE b.transaction = :transaction")})


public class Roombooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "room_booking_id")
    private Long roomBookingId;
    @Basic(optional = false)
    //@NotNull (message = "Enter date")
    @Column(name = "check_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkIn;
    @Basic(optional = false)
   // @NotNull
    @Column(name = "check_out")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOut;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "num_people")
    private int numPeople;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "price_total")
    private BigDecimal priceTotal;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "special_requests")
    private String specialRequests;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    @ManyToOne(optional = false)
    private Booking bookingId;
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    @ManyToOne(optional = false)
    private Room roomId;

    @Transient
    private int roomTypeName;

    public int getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(int roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
    
    public Roombooking() {
    }

    public Roombooking(Long roomBookingId) {
        this.roomBookingId = roomBookingId;
    }

    public Roombooking(Long roomBookingId, Date checkIn, Date checkOut, int numPeople, BigDecimal priceTotal, String specialRequests, Date dateCreated, Date dateModified) {
        this.roomBookingId = roomBookingId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numPeople = numPeople;
        this.priceTotal = priceTotal;
        this.specialRequests = specialRequests;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Long getRoomBookingId() {
        return roomBookingId;
    }

    public void setRoomBookingId(Long roomBookingId) {
        this.roomBookingId = roomBookingId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
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

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomBookingId != null ? roomBookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roombooking)) {
            return false;
        }
        Roombooking other = (Roombooking) object;
        if ((this.roomBookingId == null && other.roomBookingId != null) || (this.roomBookingId != null && !this.roomBookingId.equals(other.roomBookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Roombooking[ roomBookingId=" + roomBookingId + " ]";
    }
    
}