package Presenter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Fabryka;
import Model.Gruszka;
import Model.Head;
import Model.Ifabryka;
import Model.Iowoc;
import Model.Tail;
import Model.Wunsz;

public class Panel extends JPanel
{
	int x=500;
	int y=500;
	private int punkty=0;
	private boolean dodaj=false;
	private boolean zjadl=false;
	private int tail_x;
	private int tail_y;
	private int ilosc=0;
	private JButton b;
	private boolean reset=false;
	private JLabel napis;
	public ArrayList<Wunsz> lista = new ArrayList<Wunsz>();
	Ifabryka fowoce = new Fabryka();
	Iowoc owoc;
	public Panel()
	{
		this.setSize(x,y);
		this.lista.add(new Head(250,250,25));
		this.lista.add(new Tail(250,225,25));
		this.lista.add(new Tail(250,200,25));
		napis = new JLabel("0");
		napis.setFont(new Font(napis.getName(), Font.PLAIN, 20));
		this.add(napis);
		b = new JButton("Restart");
		b.setBounds(200, 200, 80, 60);
		b.setVisible(false);
		b.setFocusable(false);
		this.add(b);
		b.addActionListener(new ActionListener() {
	        
			public void actionPerformed(ActionEvent arg0) {
				lista.clear();
				lista.add(new Head(250,250,25));
				lista.add(new Tail(250,225,25));
				lista.add(new Tail(250,200,25));
				punkty=0;
				owoc=null;
				dodaj=false;
				zjadl=false;
				ilosc=0;		
				napis.setText(new Integer(punkty).toString());
				reset=true;
			}          
	      });
	}
	private void add_lista(int x,int y)
	{
		this.lista.add(new Tail(x,y,25));
	}
	private void zmien(int x,int y)
	{
		int pomx=lista.get(0).get_x();
		int pomy=lista.get(0).get_y();
		lista.get(0).zmiana(x, y);
		for(int i=1;i<lista.size();i++)
		{
			x=pomx;
			y=pomy;
			pomx=lista.get(i).get_x();
			pomy=lista.get(i).get_y();
			lista.get(i).set_xy(x,y);
		}
	}
	private void zrob_owoc()
	{
		if(this.owoc == null)
		{
			boolean koniec =true;
			int rand = (int )(Math.random() * 10);
			int r_x=0;
			int r_y=0;
			while(koniec) {
				r_x = ((int )(Math.random() * 17+1))*25;
				r_y = ((int )(Math.random() * 17+1))*25;
				koniec=false;
				for(Wunsz x : lista)
				{
					if(x.get_x() == r_x && x.get_y() == r_y)koniec=true;
				}
			}
			this.owoc = this.fowoce.createOwoc(r_x, r_y, 25);
			if(rand >4)
			{
				this.owoc = new Gruszka(this.owoc);
			}
		}
	}
	private void jedzenie()
	{
		if(lista.get(0).get_x() == owoc.get_x() && lista.get(0).get_y() == owoc.get_y())
		{
			this.punkty+=owoc.get_p();
			this.tail_x=lista.get(0).get_x();
			this.tail_y=lista.get(0).get_y();
			this.owoc=null;
			this.dodaj=true;
			this.ilosc++;
			this.napis.setText(new Integer(this.punkty).toString());
		}
	}
	private void trawienie()
	{
		if(this.dodaj)
		{
			if(zjadl)
			{
				if(((tail_x == lista.get(lista.size()-1).get_x()) && (tail_y == lista.get(lista.size()-1).get_y()-25)) ||
				((tail_x == lista.get(lista.size()-1).get_x()) && (tail_y == lista.get(lista.size()-1).get_y()+25))||
				((tail_x == lista.get(lista.size()-1).get_x()+25) && (tail_y == lista.get(lista.size()-1).get_y()))||
				((tail_x == lista.get(lista.size()-1).get_x()-25) && (tail_y == lista.get(lista.size()-1).get_y()))   )
				{
					this.dodaj=false;
					add_lista(tail_x,tail_y);
					this.zjadl=false;
					this.ilosc--;
					if(this.ilosc>0)this.zjadl=true;
				}
			}
			if((tail_x == lista.get(lista.size()-1).get_x()) && (tail_y == lista.get(lista.size()-1).get_y()))
			{
				this.zjadl=true;
			}
		}
	}
	public boolean ruch(char kierunek)
	{
		zrob_owoc();
		boolean r=true;
		switch(kierunek)
		{
			case 'g':
						if((lista.get(0).get_x() == lista.get(1).get_x()) && (lista.get(0).get_y()-25 == lista.get(1).get_y()))
							{
								r=false;
								this.zmien(0, 25);
								break;
							}
						if(r)this.zmien(0, -25);break;
			case 'd':
						if((lista.get(0).get_x() == lista.get(1).get_x()) && (lista.get(0).get_y()+25 == lista.get(1).get_y()))
						{
							r=false;
							this.zmien(0, -25);
							break;
						}
						if(r)this.zmien(0, 25);break;
			case 'p':
						if((lista.get(0).get_x()+25 == lista.get(1).get_x()) && (lista.get(0).get_y() == lista.get(1).get_y()))
						{
							r=false;
							this.zmien(-25, 0);
							break;
						}
						if(r)this.zmien(25, 0);break;
			case 'l':
						if((lista.get(0).get_x()-25 == lista.get(1).get_x()) && (lista.get(0).get_y() == lista.get(1).get_y()))
						{
							r=false;
							this.zmien(25, 0);
							break;
						}
						if(r)this.zmien(-25,0);break;
		}
		int x_h = lista.get(0).get_x();
		int y_h = lista.get(0).get_y();
		if(x_h <= 0 || x_h >= 475 || y_h <= 0 || y_h >= 475)return true;
		for(int i=1;i<lista.size();i++)
		{
			if(x_h == lista.get(i).get_x() && y_h == lista.get(i).get_y())
			{
				return true;
			}
		}
		trawienie();
		jedzenie();
		return false;
	}
	public int get_punkty()
	{
		return this.punkty;
	}
	public int get_dlugosc()
	{
		return this.lista.size();
	}
	
	public boolean koniec()
	{
		b.setVisible(true);
		repaint();
		if(this.reset)
		{
			b.setVisible(false);
			this.reset = false;
			return false;
		}
		return true;
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.fillRect(0, 0, x, y);
		for(Wunsz x : lista)
		{
			x.rysuj((Graphics2D) g);
		}
		if(this.owoc != null)this.owoc.rysuj((Graphics2D) g);
	}
}
