package Model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tail implements Wunsz
{
	protected int x;
	protected int y;
	protected int d;
	public Tail(int x, int y, int d)
	{
		this.x=x;
		this.y=y;
		this.d=d;
	}
	public void rysuj(Graphics2D g) 
	{
		g.setColor(new Color(0,100,0));
		g.fillRect(this.x, this.y, this.d, this.d);
	}
	public int get_x()
	{
		return this.x;
	}
	public int get_y()
	{
		return this.y;
	}
	public void zmiana(int x,int y)
	{
		this.x=this.x+x;
		this.y=this.y+y;
		
	}
}
