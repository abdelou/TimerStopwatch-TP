package org.example.GUI;

import org.example.component.ClockSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ThreeLineInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ClockSystem context;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	public ThreeLineInfoPanel(ClockSystem context) {
		
		super();
		setPreferredSize(new Dimension(600, 150));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.context = context;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		label1 = new JLabel(context.getLineInfo1());
		label1.setPreferredSize(new Dimension(300,50));
		label1.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
		label1.setForeground(Color.BLACK);
		add(label1);
		
		label2 = new JLabel(context.getLineInfo2());
		label2.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
		label2.setPreferredSize(new Dimension(300,50));
		label2.setForeground(Color.BLACK);
		add(label2);
		
		label3 = new JLabel(context.getLineInfo3());
		label3.setFont(new Font(Font.DIALOG,Font.PLAIN, 25));
		label3.setPreferredSize(new Dimension(300,50));
		label3.setForeground(Color.BLACK);
		add(label3);
		}
	
	public void update() {
		label1.setText(context.getLineInfo1());
		label2.setText(context.getLineInfo2());
		label3.setText(context.getLineInfo3());	}
	
	public void setLineInfo1Color(Color color) {
		label1.setForeground(color); }
	
	public void setLineInfo2Color(Color color) {
		label2.setForeground(color); }
	
	public Color getLineInfo1Color() {
		return label1.getForeground(); }
	
	public Color getLineInfo2Color() {
		return label2.getForeground(); } } 
