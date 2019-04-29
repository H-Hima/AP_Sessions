package Networking.UDP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSender {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printer = new PrintStream(outputStream);
		
		printer.println("Salaaaam.");
		printer.println("Inam Khat Dovom.");
		
		byte[] data = outputStream.toByteArray();
		DatagramPacket packet = new DatagramPacket(
				data,
				data.length,
				InetAddress.getByName("127.0.0.1"), 
				9090);
		
		socket.send(packet);
	}
}
