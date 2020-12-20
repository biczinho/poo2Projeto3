package userInterface;

public class Tester {
	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			ClientWindow c = new ClientWindow();
			ClientWindow c2 = new ClientWindow();
			ServerWindow s = new ServerWindow();
			c.frmMain.setVisible(true);
			c2.frmMain.setVisible(true);
			s.frmMain.setVisible(true);
		} catch (Exception e) {
//			catch-all safety
			e.printStackTrace();
		}
	}

}
