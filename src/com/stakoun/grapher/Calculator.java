package com.stakoun.grapher;

import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;

public class Calculator
{
	public Calculator()
	{
		getInput();
	}

	public static void main(String[] args)
	{
		new Calculator();
	}
	
	private void getInput()
	{
		Scanner scanner = new Scanner(System.in);
		String in;
		while (true) {
			System.out.print("y = ");
			in = scanner.nextLine();
			if (in.equals("")) break;
			makeGraph(in);
		}
		scanner.close();
		System.exit(0);
	}
	
	private void makeGraph(String equation)
	{
		JFrame frame = new JFrame("y = " + equation);
		Graph graph = new Graph(equation);
		
		frame.add(graph);
		frame.pack();
		frame.setSize(new Dimension(800, 800));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);
	}
	
}
