package elective.postparking;

import javax.jws.WebMethod;

/**
 *
 * @author daniele
 */

public interface PostParking {
   
    @WebMethod(operationName = "pay")
    public boolean pay();

    @WebMethod(operationName = "stay")
    public boolean stay();
    
    @WebMethod 
    public String getState();
    
    @WebMethod
    public int getValue();
}
