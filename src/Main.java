import java.awt.EventQueue;

import javax.swing.JFrame;

import ui.MainFrame;

public class Main {
	public static void main(String[] args) {	
			EventQueue.invokeLater(() -> {
				JFrame mainFrame = new MainFrame();
				mainFrame.setTitle("DrawTest");
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			});

	}
}
