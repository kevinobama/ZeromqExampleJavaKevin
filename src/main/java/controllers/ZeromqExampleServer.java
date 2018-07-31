package controllers;

import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class ZeromqExampleServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 echo("started");
		 try (ZContext context = new ZContext()) {
	            // Socket to talk to clients
	            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
	            socket.bind("tcp://*:5559");

	            while (!Thread.currentThread().isInterrupted()) {
	            	echo("=================== server start==========================");
	                // Block until a message is received
	                byte[] reply = socket.recv(0);

	                // Print the message
	                echo("kevin's server ");
	                System.out.println(
	                    "Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]"
	                );
	                
	                // Send a response
	                String response = "ZeroMQ (also known as ØMQ, 0MQ, or zmq) looks like an embeddable networking library but acts like a concurrency framework!";
	                socket.send(response.getBytes(ZMQ.CHARSET), 0);
	                echo("====================== server  end=======================");
	            }
	        }
		
	}
		
	public static void echo(Object o) {
		// TODO Auto-generated method stub
		System.out.println(o);
	}
}
