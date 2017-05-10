/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
    @NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
    @NamedQuery(name = "User.findByPostalCode", query = "SELECT u FROM User u WHERE u.postalCode = :postalCode"),
    @NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "User.findByDateCreated", query = "SELECT u FROM User u WHERE u.dateCreated = :dateCreated"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findAdminByEmail", query = "SELECT u FROM User u WHERE u.email = :email and u.role = :role")})

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
   // @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
   // @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
 //   @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
   // @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 0, max = 12)
    @Column(name = "postal_code")
    private String postalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
  //  @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "role")
    private String role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Booking> bookingCollection;

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String email, String password, String firstName, String lastName, String address, String city, String country, String postalCode, String phoneNumber, Date dateCreated, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.dateCreated = dateCreated;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ userId=" + userId + " ]";
    }
    
}
