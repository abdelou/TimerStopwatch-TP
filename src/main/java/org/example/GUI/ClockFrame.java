package org.example.GUI;

import org.example.component.ClockSystem;
import org.example.utility.Time;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class ClockFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ClockSystem context;
	private ButtonClockPanel button;
	private ModePanel mode;
	private TimeDisplayerPanel display;
	private ClockPanel clock;
	private ThreeLineInfoPanel lineInfo;
	
	public ClockFrame() throws FontFormatException, IOException {
		
		super("Clock");
		setSize(800, 550);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		context = new ClockSystem();
		Calendar calendrier = Calendar.getInstance();
		context.setTime(new Time(calendrier.get(Calendar.HOUR_OF_DAY), calendrier.get(Calendar.MINUTE), calendrier.get(Calendar.SECOND)));
		button = new ButtonClockPanel(context);
		mode = new ModePanel(context);
		display = new TimeDisplayerPanel(context);
		clock = new ClockPanel(context);
		lineInfo = new ThreeLineInfoPanel(context);
		
		context.setClock(this);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		mainPanel.add(button);
		mainPanel.add(mode);
		mainPanel.add(display);
		mainPanel.add(clock);
		mainPanel.add(lineInfo);	
		
		add(mainPanel); }
		
		
	public void update() {
		button.update();
		mode.update();
		display.update();
		clock.update();
		lineInfo.update(); }
	
	public void setHourColor(Color color) {
		display.setHourColor(color);	}
	
	public void setMinuteColor(Color color) {
		display.setMinuteColor(color); }
	
	public void setSecondColor(Color color) {
		display.setSecondColor(color); }
	
	public Color getHourColor() {
		return display.getHourColor();	}
	
	public Color getMinuteColor() {
		return display.getMinuteColor(); }
	
	public Color getSecondColor() {
		return display.getSecondColor(); }
	
	public void setIconColor(Color color) {
		display.setIconColor(color); }
	
	public void setLineInfoOneColor (Color color) {
		lineInfo.setLineInfo1Color(color); }
	
	public void setLineInfoTwoColor (Color color) {
		lineInfo.setLineInfo2Color(color); }
	
	public Color getLineInfoOneColor() {
		return lineInfo.getLineInfo1Color(); }
	
	public Color getLineInfoTwoColor() {
		return lineInfo.getLineInfo2Color(); } }
