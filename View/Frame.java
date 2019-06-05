package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import Presenter.Panel;

public class Frame extends JFrame
{
	public Panel p;
	public char kierunek='d';
	private int szybkosc=0;
	boolean koniec=false;
	public Frame(String nazwa)
	{
		p = new Panel();
		this.setTitle(nazwa);
		this.add(p);
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(505, 530);
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
		koniec=false;
		while(true)
		{
			if(!koniec)
			{
				koniec=p.ruch(kierunek);
				p.repaint();
				if(p.get_dlugosc()%4 == 0)
				{
					if(this.szybkosc<900)
						this.szybkosc+=10;
				}
				try {
					Thread.sleep(1000-this.szybkosc);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				koniec=p.koniec();
				kierunek='d';
				szybkosc=120;
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
	public boolean get_koniec()
	{
		return this.koniec;
	}
	public static void main(String[] args) {
		Frame f = new Frame("Wunsz");
		f.set_speed(120);
		f.zadanie();
		

	}

}
