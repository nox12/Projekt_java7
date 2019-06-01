package View;

import Presenter.Panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame
{
	public Panel p;
	public char kierunek='d';
	private int szybkosc=0;
	public Frame(String nazwa)
	{
		p = new Panel();
		this.setTitle(nazwa);
		this.add(p);
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(501, 501);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(new Adapter());
	}
	public class Adapter extends KeyAdapter 
	{

        @Override
        public void keyPressed(KeyEvent event) 
        {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT)
            {
            	kierunek='l';
            }
            if (keyCode == KeyEvent.VK_RIGHT)
            {
            	kierunek='p';
            }
            if (keyCode == KeyEvent.VK_UP)
            {
            	kierunek='g';
            }
            if (keyCode == KeyEvent.VK_DOWN)
            {
            	kierunek='d';
            }
        }
        @Override
        public void keyReleased(KeyEvent event) {
        }
	}
	
	public void zadanie()
	{
		boolean koniec=false;
		while(!koniec)
		{
			koniec=p.ruch(kierunek);
			p.repaint();
			if(p.get_punkty()%3 == 0)
			{
				if(this.szybkosc<900)
				this.szybkosc+=10;
			}
			try {
				Thread.sleep(1000-this.szybkosc);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void set_speed(int x)
	{
		if(x<900)
		this.szybkosc=x;
	}
	public int get_speed()
	{
		return this.szybkosc;
	}
	public static void main(String[] args) {
		Frame f = new Frame("Wunsz");
		f.zadanie();

	}

}
