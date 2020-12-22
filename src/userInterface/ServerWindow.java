package userInterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import network.Client;
import network.Parameters;
import network.Server;

/**
 * @author Gabriel
 * @since November 20th {@summary } Server graphic interface definition.
 */
public class ServerWindow extends commonWindow {

	/**
	 * {@summary } JTable to visualize all connected clients.
	 */
	public JTable clientTable = new JTable();
	/**
	 * {@summary } Attribute instance of {@link network.Server }.
	 */
	public Server server = new Server();
	/**
	 * {@summary } Attribute instance of {@link userInterface.ClientTableModel}.
	 */
	private ClientTableModel clientModel = new ClientTableModel();

	/**
	 * {@summary } Method regularly updates JTable, sub-optimal solution because I couldnt implement a custom Listener.
	 */
	private void updateTable() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				clientModel = new ClientTableModel(server.clientList);
				clientTable.setModel(clientModel);
			}
		});
	}

	/**
	 * {@summary } Default constructor calls inherited default constructor, and starts Server attribute's packet listener.
	 * 
	 * @throws UnknownHostException
	 */
	public ServerWindow() throws UnknownHostException {
		super();
		frmMain.setTitle("Server");

		server.turnPacketListenerOn();
		init();
	}

	/**
	 * {@summary } Initialize the contents of the frame.
	 * 
	 * @throws UnknownHostException
	 */
	public void init() throws UnknownHostException {// Components definition, listeners
		Thread threadUI = new Thread(new Runnable() {
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

				clientTable = new JTable();
				JScrollPane scroll = new JScrollPane(clientTable);
				scroll.setSize(pnlRightFix.getSize());
				clientTable.setSize(scroll.getSize());
				pnlRightFix.add(scroll);

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
