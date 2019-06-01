package Model;

import java.awt.Graphics2D;

public interface Wunsz 
{
	public void rysuj(Graphics2D g);
	public void zmiana(int x,int y);
	public void set_xy(int x,int y);
	public int get_x();
	public int get_y();
}
