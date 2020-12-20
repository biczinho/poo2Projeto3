package common;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class TrafficLightVisualizer {
	private final static ImageIcon lblGreen = new ImageIcon(TrafficLightVisualizer.class.getResource("/assets/icon_green.png"));
	private final static ImageIcon lblYellow = new ImageIcon(TrafficLightVisualizer.class.getResource("/assets/icon_yellow.png"));
	private final static ImageIcon lblRed = new ImageIcon(TrafficLightVisualizer.class.getResource("/assets/icon_red.png"));

	public static Icon visualize(TrafficLight t) {
		if (t.getState() == TrafficLightStates.GREEN)
			return lblGreen;
		else if (t.getState() == TrafficLightStates.YELLOW)
			return lblYellow;
		else if (t.getState() == TrafficLightStates.RED)
			return lblRed;
		else
			return null;
	}
}
