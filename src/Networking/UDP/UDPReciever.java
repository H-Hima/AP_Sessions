package Networking.UDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class UDPReciever {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(9090);
		
		byte[] data = new byte[10000];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		
		socket.receive(packet);
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(packet.getData());
		Scanner scanner = new Scanner(inputStream);
		
		System.out.println(scanner.nextLine());
		System.out.println(scanner.nextLine());
	}
}
