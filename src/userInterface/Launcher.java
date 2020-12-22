package userInterface;

import javax.swing.JOptionPane;

/**
 * @author Gabriel
 * @since November 20th {@summary } Responsible for launching Server and Clients.
 */
public class Launcher {
	public Launcher() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			ServerWindow s = new ServerWindow();
			s.frmMain.setVisible(true);

			int howManyClients = Integer.parseInt(JOptionPane.showInputDialog("How many Clients do you want to launch?"));
			for (int i = 0; i < howManyClients; i++) {
				ClientWindow c = new ClientWindow();
				c.frmMain.setVisible(true);
			}
		} catch (Exception e) {
//			catch-all safety
			e.printStackTrace();
		}
	}

}
