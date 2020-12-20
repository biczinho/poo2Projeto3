package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.TrafficLight;
import common.TrafficLightVisualizer;
import network.Client;

public class ClientWindow extends commonWindow {
	TrafficLight tLight = new TrafficLight();
	Client client;
	JLabel lbltLight;

	private void turnUpdaterOn() {
		Timer updater = new Timer(); //THIS IS WRONG!! I CANT IMPLEMENT A TABLE LISTENER (HELP) SO IM WORKING AROUND
		updater.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				lbltLight.setText("");
				lbltLight.setIcon(TrafficLightVisualizer.visualize(client.gettrafficLight()));
			}
		}, 0, 500);
	}

	public ClientWindow() throws UnknownHostException {
		super();
		frmMain.setPreferredSize(new Dimension(600, 600));
		frmMain.setTitle("Client");
		client = new Client(InetAddress.getLocalHost(), 9876, tLight, 0);
		client.turnPacketListenerOn();
		init();
	}

	public void init() {
		pnlRight.setBackground(new Color(0x161616));
		GridBagConstraints gbc_pnlRight = new GridBagConstraints();
		gbc_pnlRight.fill = GridBagConstraints.BOTH;
		gbc_pnlRight.gridx = 1;
		gbc_pnlRight.gridy = 0;
		frmMain.getContentPane().add(pnlRight, gbc_pnlRight);
		pnlRight.setLayout(new GridLayout(3, 1, 0, 25));

		JPanel pnlRightFix = new JPanel();
		pnlRightFix.setBackground(new Color(0x161616));
		pnlRight.add(pnlRightFix);

		JPanel pnlInput = new JPanel();
		pnlInput.setBorder(null);
		pnlInput.setBackground(new Color(0x161616));
		pnlRight.add(pnlInput);
		GridBagLayout gbl_pnlInput = new GridBagLayout();
		gbl_pnlInput.columnWidths = new int[] { 90, 90, 290 };
		gbl_pnlInput.rowHeights = new int[] { 30, 30, 0 };
		gbl_pnlInput.columnWeights = new double[] { 0.0, 0.0, 1.0 };
		gbl_pnlInput.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnlInput.setLayout(gbl_pnlInput);

		JButton btnInsert = new JButton("Insert"); //t
		btnInsert.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBackground(new Color(0x2CB67D));
		btnInsert.setFont(new Font("Lato", Font.BOLD, 12));
		btnInsert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnCalc = new GridBagConstraints();
		gbc_btnCalc.fill = GridBagConstraints.BOTH;
		gbc_btnCalc.insets = new Insets(0, 0, 0, 5);
		gbc_btnCalc.gridx = 2;
		gbc_btnCalc.gridy = 0;
		pnlInput.add(btnInsert, gbc_btnCalc);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.sendPacket(tLight);
				turnUpdaterOn();
			}
		});

//		lbltLight = new JLabel(tLight.toString()); //t
		lbltLight = new JLabel("Disconnected"); //t
		lbltLight.setForeground(Color.WHITE);
		lbltLight.setFont(new Font("Lato", Font.BOLD, 24));
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.fill = GridBagConstraints.BOTH;
		gbc_lblGroup.insets = new Insets(0, 10, 0, 0);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 0;
//		pnlInput.add(lbltLight, gbc_lblGroup);
		pnlRightFix.add(lbltLight, gbc_lblGroup);
	}

}
