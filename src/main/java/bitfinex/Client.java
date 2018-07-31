package bitfinex;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import org.zeromq.ZMQ;

import serialization.Employee;

public class Client {

    public static void main (String[] args) {
    	
    	System.out.println("subscriber--------------");
        ZMQ.Context context = ZMQ.context(1);
 
        System.out.println("Collecting updates from   server");
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        subscriber.connect("tcp://localhost:5559");
 
        String filter = "kevin";
        subscriber.subscribe(filter);
        
        int update_nbr;
        long total_temp = 0;
        for (update_nbr = 0; update_nbr < 100; update_nbr++) {
        	System.out.println(update_nbr);
        	
        	byte[] data = subscriber.recv();            

            try {
				deserializing(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            System.out.println(update_nbr);
        }
  
        subscriber.close();
        context.term();  	
    	
    }
    
    public static void deserializing(byte[] data) throws IOException {
	    Employee employee = null;	    
	    
	    try {
	    	ByteArrayInputStream bis = new ByteArrayInputStream(data);
	    	ObjectInput in = new ObjectInputStream(bis);
	 
	    	employee = (Employee) in.readObject();
			 
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("client:"+e1.getMessage());
		} 
	      
      System.out.println("Deserialized Employee...");
      System.out.println("Name: " + employee.name);
      System.out.println("Address: " + employee.address);
      System.out.println("SSN: " + employee.SSN);
      System.out.println("Number: " + employee.number);
	}    
}
