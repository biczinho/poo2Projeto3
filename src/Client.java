import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
	private final JTextField msgField = new JTextField();
	private final JTextArea msgArea = new JTextArea();
	private DatagramSocket socket;

	public Client() {
		super("UDP Client");
		super.add(msgField, BorderLayout.NORTH);
		super.add(new JScrollPane(msgArea), BorderLayout.CENTER);
		super.setSize(new Dimension(450, 350));
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		msgArea.setEditable(false);

		try {
			socket = new DatagramSocket();
		} catch (SocketException ex) {
			System.exit(1);
		}

		msgField.addActionListener((ActionEvent evt) -> {
			try {
				String msg = evt.getActionCommand();
//				showMsg("\nSending message packet: " + msg);
				byte buff[] = msg.getBytes();
				DatagramPacket packetSend = new DatagramPacket(buff, buff.length, InetAddress.getLocalHost(), 12345);
				showMsg("Me:" + new String(packetSend.getData()));
				socket.send(packetSend);
			} catch (IOException ex) {
				showMsg(ex.getMessage());
			}
		});

	}

	public void readyToReceivPacket() {
		while (true) {
			try {
				byte buff[] = new byte[128];
				DatagramPacket packet = new DatagramPacket(buff, buff.length);
				socket.receive(packet);
				showMsg(packet.getPort() + ":" + new String(packet.getData()));
//				showMsg("received: " + new String(packet.getData()), 1);
			} catch (IOException ex) {
				showMsg(ex.getMessage());
			}
		}
	}

	public void showMsg(final String msg) {
		SwingUtilities.invokeLater(() -> {
			msgArea.append("\n" + msg + "\n");
		});
	}

}