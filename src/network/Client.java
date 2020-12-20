package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

import common.TrafficLight;

public class Client extends Networkable {

	private InetAddress ip;
	private TrafficLight trafficLight;
	private int port;
	private int connectedTime; //time since connected to a server

	public Client(InetAddress ip, int port, TrafficLight tLight, int connectedTime) {
		super();
		this.ip = ip;
		this.port = port;
		this.trafficLight = tLight;
		this.connectedTime = connectedTime;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public TrafficLight gettrafficLight() {
		return trafficLight;
	}

	public void settrafficLight(TrafficLight tLight) {
		this.trafficLight = tLight;
	}

	public int getConnectedTime() {
		return connectedTime;
	}

	public void setConnectedTime(int connectedTime) {
		this.connectedTime = connectedTime;
	}

	public void sendPacket(byte[] data) {
		try {
			DatagramPacket p = new DatagramPacket(data, data.length, this.ip, this.port);
			socket.send(p);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void sendPacket(TrafficLight obj) {
		try {
			byte[] data = serialize(obj);
			DatagramPacket p = new DatagramPacket(data, data.length, this.ip, this.port);
			socket.send(p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void monitorConnectedTime() {
		Timer timer = new Timer(); //appropriate timer usage
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				connectedTime += 1000;
				if (connectedTime % Parameters.CHANGE_INTERVAL == 0) {
					trafficLight.nextState();
//					ServerWindow.server.
				}
			}
		}, 0, 1000);
	}

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
