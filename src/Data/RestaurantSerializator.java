package Data;

import java.io.*;

import Business.Restaurant;

public class RestaurantSerializator {
	public void serialize(Restaurant r) {
		try {
         FileOutputStream fileOut =new FileOutputStream("fisierR.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(r);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in fisierR.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
	}
	public Restaurant deserialize(){
		Restaurant r= null;
	      try {
	         FileInputStream fileIn = new FileInputStream("fisierR.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         r =  (Restaurant) in.readObject();
	         in.close();
	         fileIn.close();
	         }
	      catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      	} 
	      catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	      System.out.println("Deserialized Restaurant...");
	      return r;
	      }
}
