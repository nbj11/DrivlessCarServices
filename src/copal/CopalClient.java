package copal;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

/**
 *
 * @author daniele
 */
public class CopalClient {


    static final String REST_URI = "http://127.0.01:7878/copal/";
    static final String EVENTS_PATH = "events";
    static final String PUBLISHERS_PATH = "publishers";
    
    static final String eventTypeName = "MyEvent";
    static final String publisherName = "MyPublisher";
 
    public static void main(String[] args) {
 
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
 
        System.out.println(" ---------- Registering event type");
        
        WebResource eventTypeService = service.path(EVENTS_PATH);
        ClientResponse response = eventTypeService.type(MediaType.APPLICATION_XML).put(ClientResponse.class, eventType(eventTypeName));
        System.out.println(response.toString());
        System.out.println(response.getEntity(String.class).toString());
        
        System.out.println(" ---------- Registering event type");
        
        WebResource publishersService = service.path(PUBLISHERS_PATH);
        String[] eventTypes= {eventTypeName};
        response = publishersService.type(MediaType.APPLICATION_XML).put(ClientResponse.class, publisher(publisherName,eventTypes));
        System.out.println(response.toString());
        System.out.println(response.getEntity(String.class).toString());
        
        System.out.println(" ---------- Publishing event");
        
        WebResource myPublisher = service.path(PUBLISHERS_PATH+"/"+publisherName);
        response = myPublisher.type(MediaType.APPLICATION_XML).post(ClientResponse.class, event(eventTypeName,"3"));
        System.out.println(response.toString());
        
        //Remove publisher
        //response = myPublisher.delete(ClientResponse.class);
        //System.out.println(response.toString());
        //System.out.println(response.getEntity(String.class).toString());
        
        System.out.println(" ---------- Removing publisher and its event types");
        
        //Remove publisher and Associated event types
        WebResource current = service.path(PUBLISHERS_PATH+"/"+publisherName + "/publishedTypes");
        response = current.delete(ClientResponse.class);
        System.out.println(response.toString());
        System.out.println(response.getEntity(String.class).toString());
        
        System.out.println(" ---------- Removing event types");
        
        //Remove publisher and Associated event types
        response = eventTypeService.delete(ClientResponse.class);
        System.out.println(response.toString());
        System.out.println(response.getEntity(String.class).toString());
    }
    
    public static String eventType(String eventType){
    	return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"+
    	"<copal:Event name=\""+eventType+"\" xmlns:copal=\"http://www.sm4all-project.eu/COPAL\">"+
    	"<copal:Schema>"+
    	"<copal:Inline>"+
    	"<xs:schema xmlns=\"http://www.sm4all-project.eu/COPAL\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\" targetNamespace=\"http://www.sm4all-project.eu/COPAL\">"+
    	"<xs:element name=\""+eventType+"\">    <xs:complexType>		<xs:attribute name=\"value\" type=\"xs:string\"/>	</xs:complexType>"+
    	"</xs:element>"+
    	"</xs:schema>"+
    	"</copal:Inline>"+
    	"</copal:Schema>"+
    	"</copal:Event>";
    }
    
    public static String publisher(String publisherName, String[] eventType){
    	String result = "";
    	result = result + "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
    	"<copal:Publisher sourceID=\""+ publisherName +"\" xmlns:copal=\"http://www.sm4all-project.eu/COPAL\">" +
    	"<copal:Events>";
    	for (String currentEventName : eventType){
    		result = result + "<copal:Event name=\""+currentEventName+"\"/>";
    	}
    	result = result + "</copal:Events>" +
    	"</copal:Publisher>";
    	return result;
    }
    
    public static String event(String eventType, String value){
    	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    			"<p:"+eventType+" value=\""+value+"\" xmlns:p=\"http://www.sm4all-project.eu/COPAL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" />";
    }
}
