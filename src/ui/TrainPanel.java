package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.NeuronNet;
import util.Control;

public class TrainPanel extends JPanel{
	public static Font myFont = new Font("宋体", Font.PLAIN, 20);
	private static final int DEFAULT_WIDTH = 900;
	private static final int DEFAULT_HEIGHT = 150;
	private JFileChooser chooser;
	private JLabel targetLabel;
	private JLabel non_targetLabel;
	private Color background = new Color(111, 249, 193);
	private JTextField rateField;
	private JButton btnRandom;
	private JButton btnTrain;
	public static String targetURL = "file/target.txt";
	public static String non_targetURL = "file/non_target.txt";
	private JTextField layerField;
	
	public  TrainPanel() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		
		chooser = new JFileChooser();
		targetLabel = new JLabel("[target.txt]");
		non_targetLabel = new JLabel("[non_target.txt]");
		btnTrain = new JButton("Train");
		JLabel rateLabel = new JLabel("学习率");
		rateField = new JTextField();
		btnRandom = new JButton("洗牌");
		JLabel layerLabel = new JLabel("隐层节点数");
		layerField = new JTextField();
		
		targetLabel.setFont(myFont);
		non_targetLabel.setFont(myFont);
		targetLabel.setFont(myFont);
		btnRandom.setFont(myFont);
		btnTrain.setFont(myFont);
		rateLabel.setFont(myFont);
		rateField.setFont(myFont);
		layerLabel.setFont(myFont);
		layerField.setFont(myFont);

		rateField.setPreferredSize(new Dimension(100, 30));
		rateField.setText(String.valueOf(NeuronNet.RATE));
		
		layerField.setPreferredSize(new Dimension(100, 30));
		layerField.setText(String.valueOf(Control.centerLayer));
		
		add(targetLabel);
		add(non_targetLabel);
		add(btnRandom);
		add(rateLabel);
		add(rateField);
		add(layerLabel);
		add(layerField);
		add(btnTrain);
		setBackground(background);

		btnTrain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NeuronNet.RATE = Double.parseDouble(rateField.getText());
				Control.train();
				btnTrain.setForeground(Color.red);
			}
		});
		
		btnRandom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.shuffleArray();
				btnRandom.setForeground(Color.RED);
				btnTrain.setForeground(Color.black);
				TestPanel.btnTest.setForeground(Color.black);
			}
		});
		
		rateField.getDocument().addDocumentListener(new MyDocumentListener());
		layerField.getDocument().addDocumentListener(new MyDocumentListener());
		
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
    private class MyDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
			btnTrain.setForeground(Color.black);
			TestPanel.btnTest.setForeground(Color.black);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
			btnTrain.setForeground(Color.black);
			TestPanel.btnTest.setForeground(Color.black);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
			btnTrain.setForeground(Color.black);
			TestPanel.btnTest.setForeground(Color.black);
        }
    }
}
