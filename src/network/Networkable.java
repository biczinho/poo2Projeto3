package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public abstract class Networkable {

	DatagramSocket socket;

	public Networkable() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		listenerThread.start();
	}

	public void sendPacket(DatagramPacket packetReceived) {
		try {
			socket.send(packetReceived);
//			System.out.println("Data Sent:" + deserializePacket(packetReceived));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		(new ObjectOutputStream(byteArrayOutputStream)).writeObject(obj);
		return (byteArrayOutputStream.toByteArray());
	}

	protected Object deserializePacket(DatagramPacket incomingPacket) throws IOException, ClassNotFoundException {
		return (new ObjectInputStream(new ByteArrayInputStream(incomingPacket.getData()))).readObject();
	}

	protected abstract void onPacketReceived(DatagramPacket packet);
}
