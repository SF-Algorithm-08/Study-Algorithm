package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_S1_2608_로마숫자 {

	public static void main(String[] args) throws IOException{	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		int result = 0;
		for(int i=0; i<2; i++) {
			String anum = in.readLine();
			int num = 0;
			for(int j=0; j<anum.length(); j++) {
				num += map.get(anum.charAt(j));
				if(j > 0 && map.get(anum.charAt(j-1)) < map.get(anum.charAt(j))){
					num -= 2 * map.get(anum.charAt(j-1));
				}
			}
			result += num;
		}
		int answer = result;
		StringBuilder sb = new StringBuilder();
		// 천의 자리 수 
		int digit = result / 1000;
		result %= 1000;
		for(int i=0; i<digit; i++) {
			sb.append("M");
		}
		
		// 백의 자리 수 
		digit = result / 100;
		result %= 100;
		if(digit == 4)	sb.append("CD");
		else if(digit == 9)	sb.append("CM");
		else {
			if(digit >= 5) {
				sb.append("D");
				digit -= 5;
			}
			for(int i=0; i<digit; i++)
				sb.append("C");
		}
		
		// 십의 자리 수
		digit = result / 10;
		result %= 10;
		if(digit == 4)	sb.append("XL");
		else if(digit == 9)	sb.append("XC");
		else {
			if(digit >= 5) {
				sb.append("L");
				digit -= 5;
			}
			for(int i=0; i<digit; i++)
				sb.append("X");
		}
		
		// 일의 자리 수 
		if(result == 4)	sb.append("IV");
		else if(result == 9)	sb.append("IX");
		else {
			if(result >= 5) {
				sb.append("V");
				result -= 5;
			}
			for(int i=0; i<result; i++)
				sb.append("I");
		}
		System.out.printf("%d\n%s\n",answer,sb.toString());
	}
}
