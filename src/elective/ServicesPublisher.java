/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elective;

/**
 *
 * @author daniele
 */
import elective.switchon.SwitchOnImpl;
import javax.xml.ws.Endpoint;

public class ServicesPublisher {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: java -jar LightServices.jar ip-address port\nexample: java -jar LightServices.jar 127.0.0.1 9697");
        } else {
            String address = args[0];
            String port = args[1];
            String endpointBase = "http://" + address + ":" + port;
            /* From the same class we publish all the services, to stop them press ctrl+c in the console*/
            /* Note that the endpoint must match the one declared in the repository file 
             * BedroomLight = http://127.0.0.1:9697/BedroomLight?wsdl
             *                __________________________________
             *                                 /|\
             *                                  |
             * */
            String endpoint1 = endpointBase + "/SwitchOn";
            Endpoint ep = Endpoint.publish(endpoint1, new SwitchOnImpl("SwitchOnService"));
            System.out.println("Web service published @ " + endpoint1);

            
        }
    }
}
