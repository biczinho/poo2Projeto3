package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import common.TrafficLight;

/**
 * @author Gabriel
 * @since November 20th
 * @version 1.3 {@summary } Client class definition. Responsible for information about Client application and communication
 *          with server. Extends {@link Networkable} to overload packet related methods.
 *
 */
public class Client extends Networkable {

	/**
	 * {@summary } Attribute defines IP address used to establish communication.
	 */
	private InetAddress ip;
	/**
	 * {@summary } Attribute defines TrafficLight object used by this Client.
	 */
	private TrafficLight trafficLight;
	/**
	 * {@summary } Attribute defines Port used in network communication.
	 */
	private int port;
	/**
	 * {@summary } Attribute used to monitor time since this client connected to a server.
	 */
	private int connectedTime; //time since connected to a server

	/**
	 * {@summary } Constructor used to initialize private attributes.
	 * 
	 * @param ip
	 * @param port
	 * @param tLight
	 * @param connectedTime
	 */
	public Client(InetAddress ip, int port, TrafficLight tLight, int connectedTime) {
		super();
		this.ip = ip;
		this.port = port;
		this.trafficLight = tLight;
		this.connectedTime = connectedTime;
	}

	/**
	 * {@summary } Default getter
	 * 
	 * @return
	 */
	public InetAddress getIp() {
		return ip;
	}

	/**
	 * {@summary } Default setter
	 * 
	 * @param ip
	 */
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	/**
	 * {@summary } Default getter
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * {@summary } Default setter
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * {@summary } Default getter
	 * 
	 * @return
	 */
	public TrafficLight gettrafficLight() {
		return trafficLight;
	}

	/**
	 * {@summary } Default setter
	 * 
	 * @param tLight
	 */
	public void settrafficLight(TrafficLight tLight) {
		this.trafficLight = tLight;
	}

	/**
	 * {@summary } Default getter
	 * 
	 * @return
	 */
	public int getConnectedTime() {
		return connectedTime;
	}

	/**
	 * {@summary } Default setter
	 * 
	 * @param connectedTime
	 */
	public void setConnectedTime(int connectedTime) {
		this.connectedTime = connectedTime;
	}

	/**
	 * @deprecated Since 1.3 all serialization is internal, therefore no use for this variant. {@summary } Overloading inherited
	 *             sendPacket method to send packet by previously serialized data.
	 * @param data : bytes being sent.
	 */
	@Deprecated
	public void sendPacket(byte[] data) {
		try {
			DatagramPacket p = new DatagramPacket(data, data.length, this.ip, this.port);
			socket.send(p);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * {@summary } Overloading method to read object, serialize, and send to socket.
	 * 
	 * @param obj : Object being serialized and sent.
	 */
	public void sendPacket(TrafficLight obj) {
		try {
			byte[] data = serialize(obj);
			DatagramPacket p = new DatagramPacket(data, data.length, this.ip, this.port);
			socket.send(p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * {@summary } Method used to encapsulate timer usage. Keeps track of time since connection to server and updates
	 * TrafficLightStates.
	 * 
	 */
	public void monitorConnectedTime() {
		Timer timer = new Timer(); //appropriate timer usage
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				connectedTime += 1000;
				if (connectedTime % Parameters.CHANGE_INTERVAL == 0) {
					trafficLight.nextState();
				}
			}
		}, 0, 1000);
	}

	/**
	 * {@summary } Overriding default listener to change TrafficLight attribute according to received packet's data.
	 * 
	 * @param packet : DatagramPacket being deserialized and cast into a TrafficLight object.
	 */
	@Override
	protected void onPacketReceived(DatagramPacket packet) {
		try {
			settrafficLight((TrafficLight) deserializePacket(packet));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
