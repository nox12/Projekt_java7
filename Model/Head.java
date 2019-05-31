package Model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Head extends Tail implements Wunsz
{
	public Head(int x, int y, int d)
	{
		super(x,y,d);
	}
	@Override
	public void rysuj(Graphics2D g) {
		g.setColor(new Color(0,100,80));
		g.fillRect(this.x, this.y, this.d, this.d);
	}
	
}
