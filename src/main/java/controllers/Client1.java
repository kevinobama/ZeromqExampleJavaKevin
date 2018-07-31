package controllers;
import org.zeromq.ZMQ;
public class Client1 {

	public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to server
        System.out.println("Connecting to hello world server…");

        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://localhost:5559");

        for (int requestNbr = 0; requestNbr != 20; requestNbr++) {
            
        	echo("client request by Kevin: "+requestNbr);
        	String request = "the moon";
            System.out.println("Sending the moon " + requestNbr);
            requester.send(request.getBytes(), 0);

            byte[] reply = requester.recv(0);
            System.out.println("client Received " + new String(reply) + " " + requestNbr);
        }
        requester.close();
        context.term();
    }

	public static void echo(Object o) {
		// TODO Auto-generated method stub
		System.out.println(o);
	}
}
