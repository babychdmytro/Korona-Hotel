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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julia
 */
@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findByBookingId", query = "SELECT b FROM Booking b WHERE b.bookingId = :bookingId"),
    @NamedQuery(name = "Booking.findByPriceTotal", query = "SELECT b FROM Booking b WHERE b.priceTotal = :priceTotal"),
    @NamedQuery(name = "Booking.findByCreditCardType", query = "SELECT b FROM Booking b WHERE b.creditCardType = :creditCardType"),
    @NamedQuery(name = "Booking.findByCreditCardNumber", query = "SELECT b FROM Booking b WHERE b.creditCardNumber = :creditCardNumber"),
    @NamedQuery(name = "Booking.findByCvv", query = "SELECT b FROM Booking b WHERE b.cvv = :cvv"),
    @NamedQuery(name = "Booking.findByExpMonth", query = "SELECT b FROM Booking b WHERE b.expMonth = :expMonth"),
    @NamedQuery(name = "Booking.findByExpYear", query = "SELECT b FROM Booking b WHERE b.expYear = :expYear"),
    @NamedQuery(name = "Booking.findByTransaction", query = "SELECT b FROM Booking b WHERE b.transaction = :transaction"),
    @NamedQuery(name = "Booking.findByDateCreated", query = "SELECT b FROM Booking b WHERE b.dateCreated = :dateCreated"),
    @NamedQuery(name = "Booking.findByDateModified", query = "SELECT b FROM Booking b WHERE b.dateModified = :dateModified")})


public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "booking_id")
    private Long bookingId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "price_total")
    private BigDecimal priceTotal;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "credit_card_type")
    private String creditCardType;
    @Basic(optional = false)
 //   @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "cvv")
    private int cvv;
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "exp_month")
    private int expMonth;
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "exp_year")
    private int expYear;
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "transaction")
    private boolean transaction;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
 //   @NotNull
    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
    private Collection<Roombooking> roombookingCollection;

    public Booking() {
    }

    public Booking(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Booking(Long bookingId, BigDecimal priceTotal, String creditCardType, String creditCardNumber, int cvv, int expMonth, int expYear, boolean transaction, Date dateCreated, Date dateModified) {
        this.bookingId = bookingId;
        this.priceTotal = priceTotal;
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.transaction = transaction;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public boolean getTransaction() {
        return transaction;
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Roombooking> getRoombookingCollection() {
        return roombookingCollection;
    }

    public void setRoombookingCollection(Collection<Roombooking> roombookingCollection) {
        this.roombookingCollection = roombookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Booking[ bookingId=" + bookingId + " ]";
    }
    
}
