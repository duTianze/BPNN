package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatrixUtil {

	public static void main(String[] args) {
		getMatrix("");
	}
	
	/**
	 * ��ȡtarget || non_target �ļ� 
	 * @param �ļ���ַ
	 * @return ��ά���� 
	 */
	public static double[][] getMatrix(String url) {
		BufferedReader br = null;
		/**
		 * fafa
		 */
		double[][] x = null;
		Pattern p = Pattern.compile(".\\d\\p{Punct}\\d+e.\\d+");
		try {
			br = new BufferedReader(new FileReader(url));
			String s ;
			int rows = 0;
			int columns = 0;
			//=============���ļ������ݼ������������=======================
			while( (s = br.readLine()) != null) {
				if(columns == 0) {
					Matcher m = p.matcher(s);
					while(m.find()) {
						columns++;
					}
				}
				rows++;
			}
			x = new double[rows][columns];
			br.close();
			br = new BufferedReader(new FileReader(url));	
			//================���ļ��е����ݶ��뵽������========================
			int i = 0;
			while( (s = br.readLine()) != null) {
				Matcher m = p.matcher(s);
				int j = 0;
				while(m.find()) {
					x[i][j] = Double.parseDouble(m.group());
					j++;
				}
				i++;
			}
			x = transpose(x);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return x;
	}
	
	/**
	 * ת�ö�ά����
	 * @param arr
	 * @return
	 */
	public static  double[][]  transpose(double[][] arr) {
		int rows = arr.length;
		int columns = arr[0].length;
		double[][] newArr = new double[columns][rows];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				newArr[j][i] = arr[i][j];
			}
		}
		return newArr;
	}
	
	/**
	 * ��ӡ��ά����
	 * @param x
	 */
	public static void print(double[][] x) {
		for(int i=0; i<x.length; i++) {
			for(int j=0; j<x[i].length; j++) {
				System.out.printf("%.10f\t", x[i][j]);
			}
			System.out.println();
		}
	}

}
