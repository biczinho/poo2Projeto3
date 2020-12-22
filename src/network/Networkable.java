package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author Gabriel
 * @since November 20th
 * @version 1.3 {@summary } Abstract Class defines network communication, objects serialization. For further versions, all
 *          serialization behaviour could be specified by another Abstract Class.
 */
public abstract class Networkable {

	/**
	 * {@summary } Attribute used to establish UDP based communication.
	 */
	DatagramSocket socket;

	/**
	 * {@summary } Default constructor instantiate socket attribute.
	 */
	public Networkable() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@summary } Default packet listener Method. Receives DatagramPacket from socket and calls abstract onPacketReceived
	 * listener.
	 * 
	 */
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

	/**
	 * {@summary } Default sendPacket method.
	 * 
	 * @param packetReceived : packet being sent.
	 */
	public void sendPacket(DatagramPacket packetReceived) {
		try {
			socket.send(packetReceived);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @author André de Angelis {@summary } Method to serialize given object. Untouched.
	 * @param obj : Object being serialized.
	 * @return byte : Serialized object
	 * @throws IOException : Obligatory by writeObject throw.
	 */
	public byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		(new ObjectOutputStream(byteArrayOutputStream)).writeObject(obj);
		return (byteArrayOutputStream.toByteArray());
	}

	/**
	 * {@summary } Method to deserialize given DatagramPacket, slightly changed from André's suggestion.
	 * 
	 * @param incomingPacket : packet being deserialized into Object
	 * @return Object
	 * @throws IOException            : Obligatory by readObject throw
	 * @throws ClassNotFoundException
	 */
	protected Object deserializePacket(DatagramPacket incomingPacket) throws IOException, ClassNotFoundException {
		return (new ObjectInputStream(new ByteArrayInputStream(incomingPacket.getData()))).readObject();
	}

	protected abstract void onPacketReceived(DatagramPacket packet);
}
