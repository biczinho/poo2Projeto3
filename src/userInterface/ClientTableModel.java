package userInterface;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import common.TrafficLight;
import network.Client;

/**
 * @author Gabriel
 * @since November 20th
 * @version 1.3 {@summary } Class extending AbstractTableModel defines how JTable data are modelled.
 */
public class ClientTableModel extends AbstractTableModel {
	/**
	 * {@summary } Attribute defines column's names for clients Table.
	 */
	String[] columnNames = { "IP", "Port", "Traffic Light", "Connection Time (ms)" };
	/**
	 * {@summary } Attribute List defines data type being modelled
	 */
	List<Client> clients;

	/**
	 * {@summary } Default constructor instantiates clients attribute
	 */
	public ClientTableModel() {
		this.clients = new ArrayList<Client>();
	}

	/**
	 * {@summary } Constructor to initialize clients attribute and adding parameters to it.
	 * 
	 * @param clients : List of clients being added to attribute.
	 */
	public ClientTableModel(List<Client> clients) {
		this.clients = new ArrayList<Client>();
		addAllClients(clients);
	}

	/**
	 * {@summary } Method to add parameter List into attribute List
	 * 
	 * @param clients : List of clients being added to attribute.
	 */
	public void addAllClients(List<Client> clients) {
		for (Client c : clients)
			this.clients.add(c);
	}

	/**
	 * {@summary } Default getter
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * {@summary } Default getter
	 */
	public String getColumnName(int i) {
		return columnNames[i];
	}

	/**
	 * {@summary } Default getter
	 */
	@Override
	public int getRowCount() {
		return clients.size();
	}

	/**
	 * {@summary } Default getter to access Columns object's classes.
	 */
	public Class getColumnClass(int i) {
		switch (i) {
		case 0: {
			return InetAddress.class;
		}
		case 1: {
			return int.class;
		}
		case 2: {
			return TrafficLight.class;
//			return Icon.class;
		}
		case 3: {
			return int.class;
		}
		default:
			return null;
		}
	}

	/**
	 * {@summary } Method used by DefaultTableModel to make cells editable or not.
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * {@summary } Default getter to access each cell's value.
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Client m = clients.get(row);

		if (column == 0) {
			return m.getIp();
		} else if (column == 1) {
			return m.getPort();
		} else if (column == 2) {
			return m.gettrafficLight();
		} else if (column == 3) {
			return m.getConnectedTime();
		}
		return "";
	}

	/**
	 * {@summary } Default setter to access each cell's value.
	 */
	@Override
	public void setValueAt(Object value, int row, int column) {
		Client c = clients.get(row);

		if (column == 0) {
			c.setIp((InetAddress) value);
		} else if (column == 1) {
			c.setPort((int) value);
		} else if (column == 2) {
			c.settrafficLight((TrafficLight) value);
		} else if (column == 3) {
			c.setConnectedTime((int) value);
		}
	}

	/**
	 * {@summary } Method to get a row Client object.
	 * 
	 * @param row : Which row to get object from.
	 * @return Client specified by row
	 */
	public Client getClient(int row) {
		return clients.get(row);
	}

	/**
	 * {@summary } Method to add parameter into clients attribute
	 * 
	 * @param client : client being added into clients List
	 */
	public void addClient(Client client) {
		clients.add(client);
		int lastIndex = getRowCount() - 1;
		fireTableRowsInserted(lastIndex, lastIndex);
	}

	/**
	 * {@summary } Default updateClient inherited method. Not being used as of version 1.3.
	 * 
	 * @param row
	 * @param client
	 */
	public void updateClient(int row, Client client) {
		clients.set(row, client);
		fireTableRowsUpdated(row, row);
	}

	/**
	 * {@summary } Default removeClient inherited method. Not being used as of version 1.3.
	 * 
	 * @param row
	 */
	public void removeClient(int row) {
		clients.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
