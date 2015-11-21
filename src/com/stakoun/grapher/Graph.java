package com.stakoun.grapher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JPanel;

/**
 * The Graph class creates the graph made by user inputed values.
 * @author Peter Stakoun
 */
public class Graph extends JPanel
{
	private String postfix;
	
	public Graph(String equation)
	{
		this.setPreferredSize(new Dimension(800, 800));
		postfix = toPostfix(equation);
		
		this.setVisible(true);
	}
	
	private String toPostfix(String expr)
	{
        final String ops = "-+/*^";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();
 
        for (String token : expr.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);
 
                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            }
            else if (c == '(') {
                s.push(-2);
            } 
            else if (c == ')') {
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }
	
	private double getY(double x)
	{
		String s = postfix.replaceAll("x", String.valueOf(x));
        return eval(s);
    }
	
	private double eval (String s)
	{
		LinkedList<Double> stack = new LinkedList<Double>();
		for (String token : s.split("\\s")) {
			Double tokenNum = null;
			try {
				tokenNum = Double.parseDouble(token);
			} catch(NumberFormatException e) { }
			if (tokenNum != null) {
				stack.push(tokenNum);
			} else if (token.equals("*")) {
				double second = stack.pop();
				double first = stack.pop();
				stack.push(first * second);
			} else if (token.equals("/")) {
				double second = stack.pop();
				double first = stack.pop();
				stack.push(first / second);
			} else if (token.equals("-")) {
				double second = stack.pop();
				double first = stack.pop();
				stack.push(first - second);
			} else if (token.equals("+")) {
				double second = stack.pop();
				double first = stack.pop();
				stack.push(first + second);
			} else if (token.equals("^")) {
				double second = stack.pop();
				double first = stack.pop();
				stack.push(Math.pow(first, second));
			}
		}
		return stack.pop();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.setColor(Color.BLACK);
		
		Line2D xAxis = new Line2D.Double(-getWidth()/2, 0, getWidth()/2, 0);
		Line2D yAxis = new Line2D.Double(0, -getHeight()/2, 0, getHeight()/2);
		g2d.draw(xAxis);
		g2d.draw(yAxis);
		
		g2d.setColor(Color.RED);
		double prevX = -getWidth()/2;
		double prevY = -getY(-getHeight()/2);
		for (double x = -20; x <= 20; x+=0.05)
		{
			double x1 = x*20;
			double y = -getY(x)*20;
			if ((y > getHeight()/2 || y < -getHeight()/2)
					&& (prevY > getHeight()/2 || prevY < -getHeight()/2)) {
				prevX = x1;
				prevY = y;
				continue;
			}
			Line2D l = new Line2D.Double(x1, y, prevX, prevY);
			prevX = x1;
			prevY = y;
			g2d.draw(l);
		}
	}

}
