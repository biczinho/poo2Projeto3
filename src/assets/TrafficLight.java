package assets;

import java.io.Serializable;

public class TrafficLight implements Serializable {
	/**
	 * 
	 */
//	private transient final ImageIcon icon_green = new ImageIcon(
//			TrafficLight.class.getResource("/assets/icon_green.png"));
//	private transient final ImageIcon icon_yellow = new ImageIcon(
//			TrafficLight.class.getResource("/assets/icon_yellow.png"));
//	private transient final ImageIcon icon_red = new ImageIcon(TrafficLight.class.getResource("/assets/icon_red.png"));

	TrafficLightStates state = TrafficLightStates.ENTER;
//	ImageIcon icon = new ImageIcon();

	public TrafficLight() {
		setState(TrafficLightStates.GREEN);
//		icon = icon_green;
	}

	public TrafficLight setState(TrafficLightStates state) {
		this.state = state;
//		if (state == TrafficLightStates.GREEN)
//			this.icon = icon_green;
//		else if (state == TrafficLightStates.YELLOW)
//			this.icon = icon_yellow;
//		else if (state == TrafficLightStates.RED)
//			this.icon = icon_red;
		return this;
	}

	public TrafficLightStates getState() {
		return state;
	}

//	public ImageIcon getIcon() {
//		return icon;
//	}
//
//	public void setIcon(ImageIcon icon) {
//		this.icon = icon;
//	}

	public String toString() {
		switch (getState()) {
		case ENTER:
			return "Enter";
		case RED:
			return "Red";
		case YELLOW:
			return "Yellow";
		case GREEN:
			return "Green";
		case EXIT:
			return "Exit";
		}
		return null;
	}

	public void nextState() {
		if (this.state == TrafficLightStates.GREEN)
			setState(TrafficLightStates.YELLOW);
		else if (this.state == TrafficLightStates.YELLOW)
			setState(TrafficLightStates.RED);
		else if (this.state == TrafficLightStates.RED)
			setState(TrafficLightStates.GREEN);
	}

}
