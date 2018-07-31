package bitfinex;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Random;
import org.zeromq.ZMQ;

import serialization.Employee;

public class Server {

    public static void main (String[] args) throws Exception {
 
    	System.out.println("the server starting...");
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://*:5559");
        publisher.bind("ipc://weather");

        Employee employee = new Employee();
        employee.name = "kevin";
        employee.address = "pudong, Shanghai,China";
        employee.SSN = 11122333;
        employee.number = 100;
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] employeeBytes = null;         
        
        Random srandom = new Random(System.currentTimeMillis());
        int k = 1;
        while (!Thread.currentThread ().isInterrupted ()) {
             
            try {
                out = new ObjectOutputStream(outputStream);   
                out.writeObject(employee);
                out.flush();
                employeeBytes = outputStream.toByteArray();
                publisher.send(employeeBytes);
 
              } catch (IOException i) {
            	  i.printStackTrace();	  
            	  
            	  System.out.println("server:"+i.getMessage());
              }                       
        }
        
        publisher.close();
        context.term ();
    }
}