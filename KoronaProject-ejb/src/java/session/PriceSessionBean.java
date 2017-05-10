/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;

/**
 *
 * @author Julia
 */
@Stateless
public class PriceSessionBean implements PriceSessionBeanRemote {

    
    @Override
    public double calculateRoomPrice(Date checkIn, Date checkOut, double pricePerNight) {
        
         long diff = checkOut.getTime() - checkIn.getTime();
     double totalPrice = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)*pricePerNight;
        return totalPrice;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
