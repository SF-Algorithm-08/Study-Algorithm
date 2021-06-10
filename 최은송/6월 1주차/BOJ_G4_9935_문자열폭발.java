package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_9935_문자열폭발 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char str[] = in.readLine().toCharArray();
		char bomb[] = in.readLine().toCharArray();
		
		char result[] = new char[1000000];
		boolean flag = true;
		int idx = 0;
		
		for(int i = 0, end = str.length; i < end; i++) {
			result[idx++] = str[i];
			
			if(result[idx-1] == bomb[bomb.length-1]) {
				if(idx - bomb.length < 0)
					continue;
				
				flag = true;
				for(int j = 0; j < bomb.length; j++) {
					if(result[idx - j - 1] != bomb[bomb.length - j- 1]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					idx -= bomb.length;
				}
			}
		}
		if(idx == 0) {
			System.out.println("FRULA");
		}else {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < idx; i++)
				sb.append(result[i]);
			System.out.println(sb.toString());
		}
	}
}
