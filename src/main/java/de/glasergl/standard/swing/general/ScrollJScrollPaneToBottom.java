package de.glasergl.standard.swing.general;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * Class which smoothly scrolls a given jScrollPane to its maximum value, if it
 * is visible (i.e., getParent() != null).
 * 
 * @author Gabriel Glaser
 */
public final class ScrollJScrollPaneToBottom {
    private static final int DEFAULT_ANIMATION_DURATION_MS = 100;
    private static final int DEFAULT_NUMBER_OF_STEPS = 25;

    private final JScrollPane scrollPane;
    private final int numberOfSteps;
    private final int durationBetweenStep;

    public ScrollJScrollPaneToBottom(final JScrollPane scrollPane, final int fullAnimationDurationMS, final int numberOfSteps) {
	super();
	this.scrollPane = scrollPane;
	this.numberOfSteps = numberOfSteps;
	this.durationBetweenStep = fullAnimationDurationMS / numberOfSteps;
	scrollToBottom();
    }

    public ScrollJScrollPaneToBottom(final JScrollPane scrollPane) {
	this(scrollPane, DEFAULT_ANIMATION_DURATION_MS, DEFAULT_NUMBER_OF_STEPS);
    }

    private void scrollToBottom() {
	final Component parentOfScrollWrapper = scrollPane.getParent();
	if (parentOfScrollWrapper != null) {
	    parentOfScrollWrapper.validate();
	    final JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
	    final int scrollBarValueIncreasePerStep = (verticalScrollBar.getMaximum() - verticalScrollBar.getValue()) / numberOfSteps;
	    if (durationBetweenStep == 0 || scrollBarValueIncreasePerStep == 0) {
		final int maximum = verticalScrollBar.getMaximum();
		verticalScrollBar.setValue(maximum);
		parentOfScrollWrapper.revalidate();
		parentOfScrollWrapper.repaint();
	    } else {
		final Timer timer = new Timer(durationBetweenStep, null);
		timer.setInitialDelay(0);
		timer.setRepeats(true);
		final AnimationStep animationStep = new AnimationStep(timer, verticalScrollBar, parentOfScrollWrapper, scrollBarValueIncreasePerStep);
		timer.addActionListener(animationStep);
		timer.start();
	    }
	}
    }

    private class AnimationStep implements ActionListener {
	private final Timer timer;
	private final JScrollBar scrollBar;
	private final Component parentOfScrollWrapper;
	private final int scrollBarValueIncreasePerStep;
	private int currentStep = 0;

	public AnimationStep(final Timer timer, final JScrollBar scrollBar, final Component parentOfScrollWrapper, final int scrollBarValueIncreasePerStep) {
	    super();
	    this.timer = timer;
	    this.scrollBar = scrollBar;
	    this.parentOfScrollWrapper = parentOfScrollWrapper;
	    this.scrollBarValueIncreasePerStep = scrollBarValueIncreasePerStep;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
	    if (currentStep < numberOfSteps) {
		currentStep++;
		scrollBar.setValue(scrollBar.getValue() + scrollBarValueIncreasePerStep);
		parentOfScrollWrapper.revalidate();
		parentOfScrollWrapper.repaint();
	    } else {
		timer.stop();
	    }

	}
    }
}
