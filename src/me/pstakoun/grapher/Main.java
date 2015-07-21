package me.pstakoun.grapher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The Main class initializes the window and creates GUI components.
 * @author Peter Stakoun
 */
public class Main
{
	// Create frame component variables.
	public double a, b, c;
	private JFrame frame;
	private Graph graph;
	private JTextField aInput, bInput, cInput;
	
	public Main()
	{
		initWindow();
		a = b = c = 0;
	}

	public static void main(String[] args)
	{
		new Main();
	}

	/**
	 * Checks if given string is a numeric value.
	 * @param str
	 * @return Whether or not given string is numeric.
	 */
	private boolean inputValid(String str)
	{
		try {
			double d = Double.parseDouble(str);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Create and add GUI components.
	 */
	private void initWindow()
	{
		// Create frame.
		frame = new JFrame("Grapher");
		
		// Create and add graph.
		graph = new Graph(this);
		frame.add(graph, BorderLayout.CENTER);
		
		// Create and add equation text.
		JPanel equation = new JPanel();
		JLabel yLabel = new JLabel("y = ");
		yLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		equation.add(yLabel);
		
		aInput = new JTextField(3);
		aInput.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkInput();
			}
			public void removeUpdate(DocumentEvent e) {
				checkInput();
			}
			public void insertUpdate(DocumentEvent e) {
				checkInput();
			}

			public void checkInput() {
				if (aInput.getText().isEmpty()) {
					a = 0;
					graph.repaint();
				} else if (aInput.getText().trim().equals("-")) {
					a = -1;
					graph.repaint();
				} else if (inputValid(aInput.getText())) {
					a = Double.parseDouble(aInput.getText());
					graph.repaint();
				}
			}
		});
		equation.add(aInput);
		
		JLabel aLabel = new JLabel("x² + ");
		aLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		equation.add(aLabel);
		
		bInput = new JTextField(3);
		bInput.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkInput();
			}
			public void removeUpdate(DocumentEvent e) {
				checkInput();
			}
			public void insertUpdate(DocumentEvent e) {
				checkInput();
			}

			public void checkInput() {
				if (bInput.getText().isEmpty()) {
					b = 0;
					graph.repaint();
				} else if (bInput.getText().trim().equals("-")) {
					b = -1;
					graph.repaint();
				} else if (inputValid(bInput.getText())) {
					b = Double.parseDouble(bInput.getText());
					graph.repaint();
				}
			}
		});
		equation.add(bInput);
		
		JLabel bLabel = new JLabel("x + ");
		bLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		equation.add(bLabel);
		
		cInput = new JTextField(3);
		cInput.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkInput();
			}
			public void removeUpdate(DocumentEvent e) {
				checkInput();
			}
			public void insertUpdate(DocumentEvent e) {
				checkInput();
			}

			public void checkInput() {
				if (cInput.getText().isEmpty()) {
					c = 0;
					graph.repaint();
				} else if (inputValid(cInput.getText())) {
					c = Double.parseDouble(cInput.getText());
					graph.repaint();
				}
			}
		});
		equation.add(cInput);
		
		frame.add(equation, BorderLayout.PAGE_END);
		
		// Display frame.
		frame.pack();
		frame.setSize(new Dimension(500, frame.getHeight()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public double getA()
	{
		return a;
	}
	
	public double getB()
	{
		return b;
	}
	
	public double getC()
	{
		return c;
	}
	
}
