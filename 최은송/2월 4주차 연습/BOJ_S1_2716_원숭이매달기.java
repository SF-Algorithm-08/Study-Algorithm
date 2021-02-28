package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_S1_2716_원숭이매달기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String line = in.readLine();
			int size = line.length();
			char[] c = new char[size];
			for(int i=0; i<size; i++) {
				c[i] = line.charAt(i);
			}
			int maxL = 0, level = 0;
			for(int i=0; i<size; i++) {
				if(c[i] == '[') {
					level++;
					maxL = Math.max(maxL, level);
				}
				else {
					level--;
				}
			}
			int result = (int)Math.pow(2, maxL);
			System.out.println(result);
		}
	}
}
