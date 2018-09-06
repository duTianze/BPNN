package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;

import util.Control;

public class TestPanel extends JPanel {
	public static Font myFont = new Font("ÀŒÃÂ", Font.PLAIN, 20);
	private static final int DEFAULT_WIDTH = 900;
	private static final int DEFAULT_HEIGHT = 450;
	public static JTextPane result;
	private Color background = new Color(111, 249, 193);
	public static JButton btnTest;
	
	public TestPanel() {
		JPanel top = new JPanel();
		btnTest = new JButton("≤‚ ‘");
		JPanel down = new JPanel();
		result = new JTextPane();
		
		top.setBackground(background);
		top.setPreferredSize(new Dimension(0, 50));
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		top.add(btnTest);
		
		btnTest.setFont(myFont);
		
		result.setEditable(false);
		result.setBackground(background);
		result.setContentType("text/html");
		result.setBorder(new MatteBorder(10, 0, 0, 0, new Color(0xEEEEEE)));
		down.setLayout(new BorderLayout());
		down.add(result, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(top, BorderLayout.NORTH);
		add(down, BorderLayout.CENTER);
		
		btnTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result.setText(Control.test());
				btnTest.setForeground(Color.red);
			}
		});
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
