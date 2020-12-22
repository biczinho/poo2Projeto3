package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import common.TrafficLight;

/**
 * @author Gabriel
 * @since November 20th
 * @version 1.3 {@summary } Server class definition. Responsible for information about Server hosting and communication with
 *          clients. Extends {@link Networkable} to overload packet related methods.
 */
public class Server extends Networkable {
	/**
	 * {@summary } Attribute to keep track of connected clients.
	 */
	public List<Client> clientList = new ArrayList<Client>();
	/**
	 * {@summary } Attribute used to establish UDP based communication.
	 */
	public DatagramSocket socket;

	/**
	 * {@summary } Default Constructor instantiate socket and opens socket with a port.
	 */
	public Server() {
		super();
		try {
			socket = new DatagramSocket(9876);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@summary } Overriding packet Listener, not sure why, doesnt work otherwise.
	 * 
	 */
	@Override
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
						ex.printStackTrace();
					}
				}
			}
		});
		listenerThread.start();
	}

	/**
	 * {@summary } Overloaded sendPacket Method to implement packet sending by Client parameter. Reads Client's TrafficLight,
	 * serializes and send.
	 * 
	 * @param client : Client which TrafficLight is sent.
	 */
	public void sendPacket(Client client) {
		try {
			byte[] data = serialize(client.gettrafficLight());
			DatagramPacket p = new DatagramPacket(data, data.length, client.getIp(), client.getPort());
			socket.send(p);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * {@summary } Overriding default listener to implement Servers specific behaviour. Adds new Client to clientList attribute
	 * and starts its monitoring Method.
	 * 
	 * @param packet : read its port and deserializes its data.
	 */
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
	}

}
