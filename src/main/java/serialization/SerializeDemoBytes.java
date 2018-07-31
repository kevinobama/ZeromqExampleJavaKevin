package serialization;

import java.io.*;

public class SerializeDemoBytes {

   public static void main(String [] args) {	   
	   try {
		   byte[] data  = serializing();
		   deserializing(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	     
   }
   
   public static byte[] serializing() {
      System.out.println("serializing");
      Employee e = new Employee();
      e.name = "bill gates";
      e.address = "pudong, Shanghai,China";
      e.SSN = 11122333;
      e.number = 100;
  
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ObjectOutput out = null;
      byte[] employeeBytes = null;      
      try {
        out = new ObjectOutputStream(outputStream);   
        out.writeObject(e);
        out.flush();
        employeeBytes = outputStream.toByteArray();
 
      } catch (IOException i) {
    	  i.printStackTrace();	      
      } finally {
        try {
        	outputStream.close();
        } catch (IOException ex) {
          // ignore close exception
        }
      }	      
      return employeeBytes;
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
		} 
	      
      System.out.println("Deserialized Employee...");
      System.out.println("Name: " + employee.name);
      System.out.println("Address: " + employee.address);
      System.out.println("SSN: " + employee.SSN);
      System.out.println("Number: " + employee.number);
	}
}