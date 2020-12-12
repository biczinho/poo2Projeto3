import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {
	private final JTextField msgField = new JTextField();
	private final JTextArea msgArea = new JTextArea();
	private DatagramSocket socket;
	public Set<Integer> clientsPort = new HashSet<Integer>();

	public Server() {
		super("Message Server");
		super.add(msgField, BorderLayout.NORTH);
		super.add(new JScrollPane(msgArea));
		super.setSize(new Dimension(450, 350));
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
		msgArea.setEditable(false);

		try {
			socket = new DatagramSocket(12345);

		} catch (SocketException ex) {
			System.exit(1);
		}
		msgField.addActionListener((ActionEvent evt) -> {
			if (clientsPort.isEmpty()) {
				msgField.setText("");
				JOptionPane.showMessageDialog(null, "No recent clients!");
			} else
				try {
					String msg = evt.getActionCommand();
					byte buff[] = msg.getBytes();
					DatagramPacket packetSend = new DatagramPacket(buff, buff.length, InetAddress.getLocalHost(),
							clientsPort.iterator().next());
					showMsg("Me:" + new String(packetSend.getData()));
					socket.send(packetSend);
				} catch (IOException ex) {
					showMsg(ex.getMessage());
				}
		});

	}

	public void monitoringClient(DatagramPacket packetFromClient) {
		Thread timer = new Thread(new Runnable() {
			int updates = 0;

			public void run() {
				try {
					String str = new String(packetFromClient.getData());
					str = str.trim() + ":" + updates++;
					DatagramPacket responsePacket = new DatagramPacket(str.getBytes(), str.getBytes().length,
							InetAddress.getLocalHost(), packetFromClient.getPort());
					sendPacket(responsePacket);

					Iterator<Integer> it = clientsPort.iterator();
					while (it.hasNext()) {
						int port = (int) it.next();
						System.out.println(port);
					}

					Thread.sleep(1000);
					run();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		timer.start();
	}

	public void readyToReceivPacket() {
		while (true) {
			try {
				byte buffer[] = new byte[128];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				clientsPort.add(packet.getPort());
				showMsg(packet.getPort() + ":" + new String(packet.getData()));
				monitoringClient(packet);
//				sendPacket(packet);
//				byte buf[] = "Message Received!".getBytes();
//				sendPacket(new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), packet.getPort()));
			} catch (IOException ex) {
				showMsg(ex.getMessage());
			}
		}
	}

	public void sendPacket(DatagramPacket packetReceived) {
		try {
			DatagramPacket packet = new DatagramPacket(packetReceived.getData(), packetReceived.getLength(),
					packetReceived.getAddress(), packetReceived.getPort());
			socket.send(packet);
			showMsg("Me:" + new String(packet.getData()));
		} catch (IOException ex) {

		}
	}

	public void showMsg(final String msg) {
		SwingUtilities.invokeLater(() -> {
			msgArea.append("\n" + msg + "\n");
		});
	}

}