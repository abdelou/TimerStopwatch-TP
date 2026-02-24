package org.example.GUI;

import org.example.component.ClockSystem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ClockPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ClockSystem context;

	public ClockPanel(ClockSystem context) {
		super();
		this.context=context;
		setPreferredSize(new Dimension(220,220)); }

	@Override
	public void paintComponent(Graphics g) {
		try { 			
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(getWidth()/2,getHeight()/2);
			
			super.paintComponent(g2);
			
			ImageIcon image = new ImageIcon(getClass().getResource("/pictures/cadran.png"));
			g2.drawImage(image.getImage(), -getWidth()/2, -getHeight()/2, null);
			
			double secondeAngle = (Math.PI/2) - (Math.PI*context.getObservedTime().getSecond()/30);
			double minuteAngle = (Math.PI/2) - (Math.PI*context.getObservedTime().getMinute()/30 + Math.PI*context.getObservedTime().getSecond()/1800);
			double heureAngle = (Math.PI/2) - (Math.PI*context.getObservedTime().getHour()/6) - Math.PI*context.getObservedTime().getMinute()/360;
			
			g2.setColor(new Color(28,62,77));
			g2.setStroke(new BasicStroke(6,BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2.drawLine(0,0, (int) (50*Math.cos(heureAngle)), (int) (-50*Math.sin(heureAngle)));
			
			g2.setColor(new Color(34,93,118));
			g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2.drawLine(0,0, (int) (75*Math.cos(minuteAngle)), (int) (-75*Math.sin(minuteAngle)));
			
			g2.setColor(new Color(230,15,15));
			g2.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2.drawLine(0,0, (int) (75*Math.cos(secondeAngle)), (int) (-75*Math.sin(secondeAngle))); }
		
		catch (Exception e) { e.printStackTrace(); } }
		
	public void update() {
		this.repaint(); } }
