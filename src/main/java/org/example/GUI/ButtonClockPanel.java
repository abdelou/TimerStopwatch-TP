package org.example.GUI;

import org.example.component.ClockSystem;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonClockPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private ClockSystem context;
	
	private void setButtonStyle(JButton b) {
		b.setFocusPainted(false);
		b.setPreferredSize(new Dimension(250,75));
		b.setFont(new Font(Font.DIALOG,Font.PLAIN, 20));		
	}
	
	public ButtonClockPanel(ClockSystem context) {
		
		super();		
		this.context = context;
		setPreferredSize(new Dimension(770, 100));
		
		button1 = new JButton(context.getState().getButton1Name());
		// use Java8-style action listener:
	   	button1.addActionListener(e -> context.button1Pressed());
	   	setButtonStyle(button1);
		add(button1);
		
		button2 = new JButton(context.getState().getButton2Name());
		// use Java8-style action listener:
		button2.addActionListener(e -> context.button2Pressed());
		setButtonStyle(button2);
		add(button2);
		
		button3 = new JButton(context.getState().getButton3Name());
		// use Java8-style action listener:
		button3.addActionListener(e -> context.button3Pressed());
		setButtonStyle(button3);		
		add(button3);
		}
	
	public void update() {
		button1.setText(context.getState().getButton1Name());
		button2.setText(context.getState().getButton2Name());
		button3.setText(context.getState().getButton3Name()); } }
