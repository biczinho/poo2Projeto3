package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import assets.TrafficLight;

public class Server extends Networkable {
	public List<Client> clientList = new ArrayList<Client>();
	public DatagramSocket socket;

	public Server() {
		super();
		try {
			socket = new DatagramSocket(9876);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Integer> getClientsPorts() {
		List<Integer> ports = new ArrayList<Integer>();
		for (Client c : clientList) {
			ports.add(c.getPort());
		}
		return ports;
	}

	public void turnPacketListenerOn() {
		Thread listenerThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						byte buff[] = new byte[Parameters.MAX_BUFFER_SIZE];
						DatagramPacket packet = new DatagramPacket(buff, buff.length);

						socket.receive(packet);
						onPacketReceived(packet);
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		listenerThread.start();
	}

	public void sendPacket(Client client) {
		try {
			byte[] data = serialize(client.gettrafficLight());
			DatagramPacket p = new DatagramPacket(data, data.length, client.getIp(), client.getPort());
			socket.send(p);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	protected void onPacketReceived(DatagramPacket packet) {
		try {
			Client c = new Client(InetAddress.getLocalHost(), packet.getPort(), (TrafficLight) deserializePacket(packet), 0);
			c.monitorConnectedTime();
			clientList.add(c);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hi");
	}

}
