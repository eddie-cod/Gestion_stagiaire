package boutonColor;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class ButtonColor extends JButton {
	String name;
	Color color;
	public ButtonColor(String str,Color color){
		super(str);
		this.name=str;
		this.color=color;
	}
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		Color Dodger_Blue=new Color(30,144,255);
		GradientPaint gp=new GradientPaint(0,0,Dodger_Blue,0,20,Color.GRAY,true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(),this.getHeight());
		g2d.setColor(color);
		g2d.drawString(this.name, this.getWidth() / 2 -
				(this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);
		
	}
}
