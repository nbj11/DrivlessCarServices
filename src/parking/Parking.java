package parking;

import javax.jws.WebMethod;

/**
 *
 * @author daniele
 */
public interface Parking {

    @WebMethod(operationName = "park")
    public boolean park();
   
    @WebMethod 
    public String getState();
    
    @WebMethod
    public int getValue();
}
