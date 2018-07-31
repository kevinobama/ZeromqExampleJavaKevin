package publishSubscribe;

import java.util.Random;
import org.zeromq.ZMQ;

public class Server {

    public static void main (String[] args) throws Exception {
        //  Prepare our context and publisher
    	System.out.println("the server starting...");
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://*:5559");
        publisher.bind("ipc://weather");

        //  Initialize random number generator
        Random srandom = new Random(System.currentTimeMillis());
        while (!Thread.currentThread ().isInterrupted ()) {
            //  Get values that will fool the boss
            int zipcode, temperature, relhumidity;
            zipcode = 10000 + srandom.nextInt(10000) ;
            temperature = srandom.nextInt(215) - 80 + 1;
            relhumidity = srandom.nextInt(50) + 10 + 1;

            //  Send message to all subscribers
            String update = String.format("%05d %d %d", zipcode, temperature, relhumidity);
            //System.out.println("publisher");
            //System.out.println(update);
            
            publisher.send(update, 0);
        }
        
        publisher.close ();
        context.term ();
    }
}