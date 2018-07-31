package serialization;

import java.io.*;

public class SerializeDemo {

   public static void main(String [] args) {
	   serializing();
	   
	   deserializing();
	   
       byte b = 10; //declare a byte variable and assign the value i.e 10

       Byte n1 = new Byte(b);
       Byte n2 = new Byte("4");

       System.out.println(n1); // print the value of byte variable n1        
       System.out.println(n2);//print the value of byte variable n2
   }
   
   public static void serializing() {
	      Employee e = new Employee();
	      e.name = "bill gates";
	      e.address = "Phokka Kuan, Ambehta Peer,Canada";
	      e.SSN = 11122333;
	      e.number = 100;
      
      try {
         FileOutputStream fileOut = new FileOutputStream("/tmp/employee.text");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(e);
         out.close();
         fileOut.close();
         
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }   
   
   public static void deserializing() {
	      Employee e = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("/tmp/employee.text");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Employee) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	      
	      System.out.println("Deserialized Employee...");
	      System.out.println("Name: " + e.name);
	      System.out.println("Address: " + e.address);
	      System.out.println("SSN: " + e.SSN);
	      System.out.println("Number: " + e.number);
	   }
}