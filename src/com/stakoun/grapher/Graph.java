package com.stakoun.grapher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 * The Graph class creates the graph made by user inputed values.
 * @author Peter Stakoun
 */
public class Graph extends JPanel
{
	// Create graph variables.
	private Dimension size;
	private Main main;
	
	public Graph(Main main)
	{
		// Initialize graph.
		size = new Dimension(500, 500);
		this.setPreferredSize(size);
		this.main = main;
		
		// Show graph.
		this.setVisible(true);
	}
	
	/**
	 * Updates graph with latest variables.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.setColor(Color.BLACK);
		
		// Draw empty graph.
		Line2D xAxis = new Line2D.Double(-getWidth()/2, 0, getWidth()/2, 0);
		Line2D yAxis = new Line2D.Double(0, -getHeight()/2, 0, getHeight()/2);
		g2d.draw(xAxis);
		g2d.draw(yAxis);
		
		// Calculate line coordinates from user input.
		double a = main.getA();
		double b = main.getB();
		double c = main.getC();
		
		// Draw graph from user input.
		g2d.setColor(Color.RED);
		double prevX = -250;
		double prevY = -(a*prevX*prevX + b*prevX + c);
		// Loop through x values.
		for (double x = -250; x <= 250; x++)
		{
			double y = -(a*x*x + b*x + c);
			Line2D l = new Line2D.Double(x, y, prevX, prevY);
			g2d.draw(l);
			// Update previous x and y.
			prevX = x;
			prevY = y;
		}
	}

}
