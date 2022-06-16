/**
 * class Seat
 * @author Xiangyi Lin, Chia-Yun Chen
 * ticket booking software
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Seat
{
	private final Color SOLD_COLOR = Color.RED;
	private final Color SALE_COLOR = Color.GREEN;
	
	private int price;
	private boolean sold;
	private int xLeft;
	private int yTop;
	private int seatWidth;
	private Color seatColor;
	
	public Seat()
	{
		//constructor
	}
	
	//constructor
	public Seat(int par_price, boolean par_sold, int x, int y, int width)
	{
		price = par_price;
		sold = par_sold;
		xLeft = x;
		yTop = y;
		seatWidth = width;
		
		if(sold)
			seatColor = SOLD_COLOR;
		else
			seatColor = SALE_COLOR;
	}
	
	//constructor
	public Seat(int par_price, Color par_seatColor, int x, int y, int width)
	{
		price = par_price;
		xLeft = x;
		yTop = y;
		seatWidth = width;
		seatColor = par_seatColor;
	}

	/**
	 * draw the rectangle seat with color
	 * @param g
	 */
	public void drawSeat(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(xLeft, yTop, seatWidth, seatWidth);
		
		g2.setColor(seatColor);
		g2.fillRect(xLeft, yTop, seatWidth, seatWidth);
		g2.setColor(Color.BLACK);
		Font f = new Font(null, Font.BOLD,14);
		g2.setFont(f);
		if(price != 0)
			g2.drawString("$"+price, xLeft+10, yTop+30);
	}
	
	/**
	 * get the sold in the class
	 * @return boolean of sold
	 */
	public boolean getSold()
	{
		return sold;
	}
	
	/**
	 * get the price in the class
	 * @return integer of price
	 */
	public int getPrice()
	{
		return price;
	}
	
	/**
	 * get the x for the seat
	 * @return integer of x value
	 */
	public int getX()
	{
		return xLeft;
	}
	
	/**
	 * get the y for the seat
	 * @return integer of y value
	 */
	public int getY()
	{
		return yTop;
	}
	
	/**
	 * get the width for the seat
	 * @return integer of width value
	 */
	public int getWidth()
	{
		return seatWidth;
	}
}
