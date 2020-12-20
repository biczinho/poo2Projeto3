package userInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import assets.ClientTableModel;
import network.Client;
import network.Parameters;
import network.Server;

public class ServerWindow extends commonWindow {
	protected static final String Component = null;

	public JTable clientTable = new JTable();
	public Server server = new Server();
	private ClientTableModel clientModel = new ClientTableModel();

	private void updateTable() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				clientModel = new ClientTableModel(server.clientList);
				clientTable.setModel(clientModel);
			}
		});
	}

	public ServerWindow() throws UnknownHostException {
		super();
		frmMain.setPreferredSize(new Dimension(600, 600));
		frmMain.setTitle("Server");

		server.turnPacketListenerOn();
		init();
	}

	public void init() throws UnknownHostException {
		Thread threadUI = new Thread(new Runnable() { // Components definition, listeners
			public void run() {
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

				clientTable = new JTable();
				JScrollPane scroll = new JScrollPane(clientTable);
				scroll.setSize(pnlRightFix.getSize());
				clientTable.setSize(scroll.getSize());
				pnlRightFix.add(scroll);

//				JTextField txfPort = new JTextField();
//				txfPort.setFont(new Font("Lato", Font.PLAIN, 14));
//				txfPort.setBorder(new LineBorder(new Color(192, 192, 192)));
//				GridBagConstraints gbc_txtValue = new GridBagConstraints();
//				gbc_txtValue.fill = GridBagConstraints.BOTH;
//				gbc_txtValue.insets = new Insets(0, 0, 5, 0);
//				gbc_txtValue.gridx = 1;
//				gbc_txtValue.gridy = 0;
//				pnlInput.add(txfPort, gbc_txtValue);
//				txfPort.setColumns(10);
//				txfPort.setText("" + 0);

				JButton btnInsert = new JButton("Insert"); //t
				btnInsert.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
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
//						for (Client c : server.clientList) {
//							server.sendPacket(c);
//						}

					}
				});

			}
		});

		Timer tableRedrawer = new Timer(); //THIS IS WRONG!! I CANT IMPLEMENT A TABLE LISTENER (HELP) SO IM WORKING AROUND
		tableRedrawer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				updateTable();
				for (Client c : server.clientList) {
					server.sendPacket(c);
				}
			}
		}, 0, Parameters.REFRESH_RATE);
		threadUI.start();
	}
}
