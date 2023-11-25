package de.glasergl.standard.swing.myComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingTextAnimation extends JPanel implements ActionListener {
    private static final int DEFAULT_TIME_BETWEEN_LOADING_ANIMATION_UPDATE_IN_MS = 400;

    private final MyLabel loadingTextLabel;
    private final Timer timer = new Timer(DEFAULT_TIME_BETWEEN_LOADING_ANIMATION_UPDATE_IN_MS, this);

    private String loadingText;
    private int animationIndex = 0;

    public LoadingTextAnimation(final String loadingText) {
	super();
	this.loadingText = loadingText;
	this.loadingTextLabel = new MyLabel(loadingText);
	timer.setInitialDelay(0);
	setLayout(new BorderLayout());
	add(loadingTextLabel, BorderLayout.CENTER);
    }

    public LoadingTextAnimation() {
	this("");
    }

    public void startLoadingAnimation() {
	timer.start();
    }

    public void stopLoadingAnimation() {
	timer.stop();
	loadingTextLabel.setText("");
    }

    public void setLoadingText(final String loadingText) {
	this.loadingText = loadingText;
	animationIndex = 0;
    }

    @Override
    public void actionPerformed(final ActionEvent nextIteration) {
	final StringBuilder animatedText = new StringBuilder(loadingText);
	for (int i = 0; i < animationIndex; i++) {
	    animatedText.append(".");
	}
	loadingTextLabel.setText(animatedText.toString());
	animationIndex++;
	animationIndex %= 4;
    }
}
