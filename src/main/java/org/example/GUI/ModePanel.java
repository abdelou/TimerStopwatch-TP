package org.example.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.component.ClockSystem;

public class ModePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClockSystem context;
	private JLabel modeLabel;
	
	public ModePanel(ClockSystem context) {
		
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.context = context;
		
		JLabel mode = new JLabel("    Mode :");
		modeLabel = new JLabel(context.getState().getMode().toString());
		mode.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
		modeLabel.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
		
		add(mode);
		add(modeLabel);
		
		setPreferredSize(new Dimension(700,70)); }
	
	public void update() {
		modeLabel.setText(context.getMode().toString());	} }
