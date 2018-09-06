package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MainFrame extends JFrame{
	
	public static JTextArea jTextArea = new JTextArea(); 
	
	public MainFrame() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setSize(1000, 750);
		jTextArea.setFont(new Font("ËÎÌו", Font.BOLD, 20));
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setPreferredSize(new Dimension(900, 50));
		jScrollPane.setViewportView(jTextArea);
		add(new TrainPanel());
		add(new TestPanel());
		add(jScrollPane);
		setLocationRelativeTo(getOwner());
	}

}
