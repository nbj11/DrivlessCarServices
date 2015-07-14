/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elective.switchon;

import javax.jws.WebMethod;

/**
 *
 * @author daniele
 */
public interface SwitchOn {
   
    @WebMethod(operationName = "swtichon")
    public boolean on();

    @WebMethod(operationName = "switchoff")
    public boolean off();
    
    @WebMethod 
    public String getState();
    
}
