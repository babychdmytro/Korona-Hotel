/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julia
 */
@Entity
@Table(name = "roomtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roomtype.findAll", query = "SELECT r FROM Roomtype r"),
    @NamedQuery(name = "Roomtype.findByRoomTypeId", query = "SELECT r FROM Roomtype r WHERE r.roomTypeId = :roomTypeId"),
    @NamedQuery(name = "Roomtype.findByRoomTypeName", query = "SELECT r FROM Roomtype r WHERE r.roomTypeName = :roomTypeName"),
    @NamedQuery(name = "Roomtype.findRoomIdByRoomTypeName", query = "SELECT r FROM Roomtype r WHERE r.roomTypeName = :roomTypeName")})

public class Roomtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_type_id")
    private Long roomTypeId;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 50)
    @Column(name = "room_type_name")
    private String roomTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomType")
    private Collection<Room> roomCollection;

    public Roomtype() {
    }

    public Roomtype(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Roomtype(Long roomTypeId, String roomTypeName) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @XmlTransient
    public Collection<Room> getRoomCollection() {
        return roomCollection;
    }

    public void setRoomCollection(Collection<Room> roomCollection) {
        this.roomCollection = roomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomTypeId != null ? roomTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roomtype)) {
            return false;
        }
        Roomtype other = (Roomtype) object;
        if ((this.roomTypeId == null && other.roomTypeId != null) || (this.roomTypeId != null && !this.roomTypeId.equals(other.roomTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Roomtype[ roomTypeId=" + roomTypeId + " ]";
    }
    
}
