package org.example.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;


import org.example.component.ClockSystem;
import org.example.utility.Time;

public class TimeDisplayerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ClockSystem context;
	private JLabel labelHour;
	private JLabel labelMinute;
	private JLabel labelSecond;
	private JLabel labelIcon;

	public String toString(int v) {
		String s = String.valueOf(v);
		if (v<10) s = "0"+s;
		return s; }

	public TimeDisplayerPanel(ClockSystem context) throws FontFormatException, IOException {
		
		super();
		setPreferredSize(new Dimension(500,100));

		Font font = Font.createFont(Font.TRUETYPE_FONT, TimeDisplayerPanel.class.getResource("/font/DS-DIGII.TTF").openStream());
		Font font_wanted = font.deriveFont(100f);
		Time timeToUpdate = context.getObservedTime();
		
		JLabel separatorOne = new JLabel(":");
		separatorOne.setFont(new Font(Font.DIALOG,Font.PLAIN, 30));
		separatorOne.setSize(new Dimension(30,60));
		
		JLabel separatorTwo = new JLabel(":");
		separatorTwo.setFont(new Font(Font.DIALOG,Font.PLAIN, 30));
		separatorTwo.setSize(new Dimension(30,60));
		
		this.context = context;
		
		labelHour = new JLabel(toString(timeToUpdate.getHour()));
		labelHour.setFont(new Font(Font.DIALOG,Font.PLAIN, 60));
		labelHour.setFont(font_wanted);
		labelHour.setPreferredSize(new Dimension(100,75));
		
		labelMinute = new JLabel(toString(timeToUpdate.getMinute()));
		labelMinute.setFont(new Font(Font.DIALOG,Font.PLAIN, 60));
		labelMinute.setFont(font_wanted);
		labelMinute.setPreferredSize(new Dimension(100,75));
		
		labelSecond = new JLabel(toString(timeToUpdate.getSecond()));
		labelSecond.setFont(new Font(Font.DIALOG,Font.PLAIN, 60));
		labelSecond.setFont(font_wanted);
		labelSecond.setPreferredSize(new Dimension(100,75));
		
		labelIcon = new JLabel(context.getIcon());
		labelIcon.setFont(new Font(Font.DIALOG,Font.PLAIN, 40));
		labelIcon.setPreferredSize(new Dimension(100,75));
		
		add(labelHour);
		add(separatorOne);
		add(labelMinute);
		add(separatorTwo);
		add(labelSecond);
		add(labelIcon); }
		
	public void update() {
		
		Time timeToUpdate = context.getObservedTime();
		
		labelHour.setText(toString(timeToUpdate.getHour()));
		labelMinute.setText(toString(timeToUpdate.getMinute()));
		labelSecond.setText(toString(timeToUpdate.getSecond()));
		labelIcon.setText(context.getIcon()); }
	
	public void setHourColor(Color color) {
		labelHour.setForeground(color);	}
	
	public void setMinuteColor(Color color) {
		labelMinute.setForeground(color); }
	
	public void setSecondColor(Color color) {
		labelSecond.setForeground(color); }
	
	public Color getHourColor() {
		return labelHour.getForeground(); }
	
	public Color getMinuteColor() {
		return labelMinute.getForeground(); }
	
	public Color getSecondColor() {
		return labelSecond.getForeground(); }
	
	public void setIconColor(Color color) {
		labelIcon.setForeground(color); } }
