package util;

import java.util.ArrayList;
import java.util.List;

import entity.NeuronNet;
import ui.MainFrame;
import ui.TrainPanel; 

public class Control {
	
	private static NeuronNet bpnn ;
	private static double[][] allData;
	private static double[][] all_label;
	public static int centerLayer = 8;
	private static double[][] target;
	
	static {
		// 读取
		target = MatrixUtil.getMatrix(TrainPanel.targetURL);
		double[][] target_label = new double[target.length][2];
		for(int i=0; i<target_label.length; i++) {
			target_label[i][0] = 1;
			target_label[i][1] = 0;
		}
		
		double[][] non_target = MatrixUtil.getMatrix(TrainPanel.non_targetURL);
		double[][] non_target_label = new double[target.length][2];
		for(int i=0; i<non_target_label.length; i++) {
			non_target_label[i][0] = 0;
			non_target_label[i][1] = 1;
		}
		
		// 合并为一个数组
		allData = new double[100][];
		all_label = new double[100][2];
		for(int i=0; i<50; i++) {
			allData[i] = target[i];
			all_label[i] = target_label[i];
		}
		for(int i=50; i<100; i++) {
			allData[i] = non_target[i-50];
			all_label[i] = non_target_label[i-50];
		}	
		shuffleArray();
		MainFrame.jTextArea.append("Data loading success!");
	}
	
	public static void shuffleArray() {
		// 同步打乱两个数组
		RandomUtil.shuffle(allData, all_label);
	}
	
	public static void train() {
		// 用前80个数组学习
		double[][] learn = new double[80][];
		double[][] learn_label = new double[80][2];
		for(int i=0; i<80; i++) {
			learn[i] = allData[i];
			learn_label[i] = all_label[i];
		}
		bpnn = new NeuronNet (new int [] {target[0].length, centerLayer, 2});
		bpnn.train(learn, learn_label);
	}
	
	public static String test() {
		if(bpnn == null) {
			return null;
		}
		// 用后20个数组测试
		List<List<Double>> list = new ArrayList<>();
		List<List<Double>> list_label = new ArrayList<>();
		double[][] test = new double[20][];
		double[][] test_label = new double[20][2];
		for(int i= 0; i<20; i++) {
			test[i] = allData[i+80];
			test_label[i] = all_label[i+80];
		}
		
		for(int i=0; i<test_label.length; i++) {
			List<Double> inList = new ArrayList<>();
			for(int j=0; j<test_label[0].length; j++) {
					inList.add(test_label[i][j]);
			}
			list_label.add(inList);
		}
		
		for(int i=0; i<test.length; i++) {
			double[] result = bpnn.predict(test[i]);
			List<Double> inList = new ArrayList<>();
			for(int j=0; j<result.length; j++) {
				if(result[j] >= 0.5) {
					inList.add(1.0);
				} else {
					inList.add(0.0);
				}
			}
			list.add(inList);
		}
		
		// 查看测试结果
		double TP = 0;
		double FP = 0;
		double FN = 0;
		double TN = 0;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).get(0).equals(1.0)) {
				if(list.get(i).get(0).equals(list_label.get(i).get(0))) {
					TP++;
				} else {
					FP++;
				}
			} else {
				if(list.get(i).get(0).equals(list_label.get(i).get(0))) {
					TN++;
				} else {
					FN++;
				}
			}
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<br>");
		stringBuffer.append("<table border='1' align='center' width='50%' "
				+ "style='font-size:18px'>");
		stringBuffer.append("<tr>"
								+ "<th rowspan='2'>决策</th>"
								+ "<th colspan='2'>状态</th>"
								+ "<tr></th><th>阳性</th> <th>阴性</th></tr>"
							+ "</tr>");
		stringBuffer.append("<tr>"
								+ "<th>阳性</th> <td align='center'>" + (int)TP+ "&nbsp;(TP)" + "</td> "
											+ "<td align='center'>" + (int)FP+ "&nbsp;(FP)" + "</td>"
							+ "</tr>");
		stringBuffer.append("<tr>"
							+ "<th>阳性</th> <td align='center'>" + (int)FN+ "&nbsp;(FN)" + "</td> "
										+ "<td align='center'>" + (int)TN + "&nbsp;(TN)" + "</td>"
							+ "</tr>");
		stringBuffer.append("</table><br>");
		stringBuffer.append("<h1 align='center'>Sn=" + String.format("%.2f", TP/(TP+FN)*100) + "%&nbsp;&nbsp;"
												+"Sp=" + String.format("%.2f", TN/(TN + FP)*100) + "%</h1>");
		stringBuffer.append("</table><br>");
		stringBuffer.append("<hr><h1 align='center'>测试准确率为:" + (TP + TN)/20*100 + "%</h1>");
		return stringBuffer.toString();
	}
}
