package Presenter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Head;
import Model.Tail;
import Model.Wunsz;

public class Panel extends JPanel
{
	int x=500;
	int y=500;
	public ArrayList<Wunsz> lista = new ArrayList<Wunsz>();
	public Panel()
	{
		this.setSize(x,y);
		this.lista.add(new Head(250,250,50));
		this.lista.add(new Tail(250,200,50));
	}
	public void add_lista(int x,int y)
	{
		this.lista.add(new Tail(x,y,50));
	}
	public void zmien(int x,int y)
	{
		for(Wunsz k: this.lista)
		{
			k.zmiana(x,y);
		}
	}
	public boolean ruch(char kierunek)
	{
		System.out.println("tak");
		switch(kierunek)
		{
			case 'g':this.zmien(0, 50);break;
			case 'd':this.zmien(0, -50);break;
			case 'p':this.zmien(50, 0);break;
			case 'l':this.zmien(-50, 0);break;
		}
		int x_h = lista.get(0).get_x();
		int y_h = lista.get(0).get_y();
		if(x_h == 0 || x_h == 450 || y_h == 0 || y_h == 450)return true;
		for(int i=1;i<lista.size();i++)
		{
			if(x_h == lista.get(i).get_x() || y_h == lista.get(i).get_y())
			{
				return true;
			}
		}
		
		return false;
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		System.out.println("nie");
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, x, y);
		for(Wunsz x : lista)
		{
			x.rysuj((Graphics2D) g);
		}
	}
}
