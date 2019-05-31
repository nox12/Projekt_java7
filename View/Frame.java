package View;

import Presenter.Panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Frame extends JFrame
{
	public Panel p;
	public Frame()
	{
		p = new Panel();
		this.add(p);
		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void zadanie()
	{
		
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		f.zadanie();

	}

}
