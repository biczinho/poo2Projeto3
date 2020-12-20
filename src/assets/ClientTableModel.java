package assets;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import network.Client;

public class ClientTableModel extends AbstractTableModel {
	String[] columnNames = { "IP", "Port", "Traffic Light", "Connection Time (ms)" };
	List<Client> clients;

	public ClientTableModel() {
		this.clients = new ArrayList<Client>();
	}

	public ClientTableModel(List<Client> clients) {
		this.clients = new ArrayList<Client>();
		addAllClients(clients);
	}

	public void addAllClients(List<Client> clients) {
		for (Client c : clients)
			this.clients.add(c);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int i) {
		return columnNames[i];
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

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
		}
		case 3: {
			return int.class;
		}
		default:
			return null;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

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

	public Client getClient(int row) {
		return clients.get(row);
	}

	public void addClient(Client client) {
		clients.add(client);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void updateClient(int row, Client client) {
		clients.set(row, client);
		fireTableRowsUpdated(row, row);
	}

	public void removeClient(int row) {
		clients.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
