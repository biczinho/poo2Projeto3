package common;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Gabriel
 * @since November 20th
 * @version 1.3 {@summary } Utility Class used to define icons and visualize a TrafficLightState as Icon.
 */
public class TrafficLightVisualizer {
	/**
	 * {@summary }Attribute defines Green icon, by finding corresponding .png file
	 */
	private final static ImageIcon iconGreen = new ImageIcon(TrafficLightVisualizer.class.getResource("/common/icon_green.png"));
	/**
	 * {@summary }Attribute defines Yellow icon, by finding corresponding .png file
	 */
	private final static ImageIcon iconYellow = new ImageIcon(TrafficLightVisualizer.class.getResource("/common/icon_yellow.png"));
	/**
	 * {@summary} Attribute defines Red icon, by finding corresponding .png file
	 */
	private final static ImageIcon iconRed = new ImageIcon(TrafficLightVisualizer.class.getResource("/common/icon_red.png"));

	/**
	 * {@summary } Method defines visualization of a TrafficLightState by reading a TrafficLight object
	 * 
	 * @param t : TrafficLight object being visualized
	 * @return Icon attribute
	 */
	public static Icon visualize(TrafficLight t) {
		if (t.getState() == TrafficLightStates.GREEN)
			return iconGreen;
		else if (t.getState() == TrafficLightStates.YELLOW)
			return iconYellow;
		else if (t.getState() == TrafficLightStates.RED)
			return iconRed;
		else
			return null;
	}
}
