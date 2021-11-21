package standard.helper.enums;

/**
 * Enum for the MouseEvent Types which start an action. For example a click
 * action always starts with press (can't start with release). Furthermore, the
 * mouse has to enter the component first, before it exits.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public enum StartMouseEvent {
	PRESS, CLICK, ENTER;
}
