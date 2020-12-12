public class starts {

	public starts() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Server s = new Server();
		Client c = new Client();
		Client c2 = new Client();

		Thread ts = new Thread(new Runnable() {
			public void run() {
				s.readyToReceivPacket();
			}
		});

		Thread tc = new Thread(new Runnable() {
			public void run() {
				c.readyToReceivPacket();
			}
		});

		Thread tc2 = new Thread(new Runnable() {
			public void run() {
				c2.readyToReceivPacket();
			}
		});

		ts.start();
		tc.start();
		tc2.start();
	}

}
