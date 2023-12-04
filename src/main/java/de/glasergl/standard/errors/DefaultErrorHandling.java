package de.glasergl.standard.errors;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.JOptionPane;

/**
 * This class contains a function to activate the default exception handler
 * which shows the exception in a JOptionPane and stores it in a .txt-File.
 *
 * @author Gabriel Glaser
 */
public final class DefaultErrorHandling {
    /**
     * Every uncaught Throwable will be visualized in a JOptionPane and logged in a
     * File.
     */
    public static void activateDefaultExceptionHandling() {
	Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
	    showAndLogUnexpectedExceptionAndExitProgram(exception);
	});
    }

    /**
     * Depicts the given Exception in a JOptionPane and terminates the program after
     * that.
     *
     * @param exception
     */
    private static void showAndLogUnexpectedExceptionAndExitProgram(final Throwable exception) {
	final String title = "Unexpected Error";
	final String message = "The file \"error.txt\" shows the stacktrace of methods which led to the error.";
	JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	logException(exception);
	System.exit(1);
    }

    /**
     * Prints the stack-trace of the given Exception in a file.
     *
     * @param exception
     */
    private static void logException(final Throwable exception) {
	try {
	    final File errorOutput = new File("error_" + getCurrentDateAndTime() + ".txt");
	    exception.printStackTrace(new PrintStream(errorOutput));
	} catch (final IOException e) {
	    final String title = "Error with the Error.";
	    final String message = "The error couldn't be stored in a file.";
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
    }

    /**
     * @return String representation of the current time.
     */
    private static String getCurrentDateAndTime() {
	final Calendar calendar = Calendar.getInstance();
	return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "." + calendar.get(Calendar.MINUTE) + "." + calendar.get(Calendar.SECOND)
		+ "." + calendar.get(Calendar.MILLISECOND);
    }
}
