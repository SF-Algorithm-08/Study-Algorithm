// 로마 숫자

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_2608 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();

		int num1 = StrToNum(str1);
		int num2 = StrToNum(str2);
		System.out.println(num1 + num2);
		StringBuilder ans = NumToStr(num1 + num2);
		System.out.println(ans);

	}

	private static StringBuilder NumToStr(int num) {
		StringBuilder temp = new StringBuilder();
		
		int cnt = num / 1000;
		for (int i = 0; i < cnt; i++) {
			temp.append("M");
		}
		num %= 1000;
		
		cnt = num / 100;
		if(cnt==9) temp.append("CM");
		else if(cnt==4) temp.append("CD");
		else {
			if(cnt>=5) {
				temp.append("D");
				cnt -= 5;
			}
			for (int i = 0; i < cnt; i++) {
				temp.append("C");
			}
		}
		num %= 100;
		
		cnt = num / 10;
		if(cnt==9) temp.append("XC");
		else if(cnt==4) temp.append("XL");
		else {
			if(cnt>=5) {
				temp.append("L");
				cnt -= 5;
			}
			for (int i = 0; i < cnt; i++) {
				temp.append("X");
			}
		}
		num %= 10;
		
		if(num==9) temp.append("IX");
		else if(num==4) temp.append("IV");
		else {
			if(num>=5) {
				temp.append("V");
				num -= 5;
			}
			for (int i = 0; i < num; i++) {
				temp.append("I");
			}
		}
		
		return temp;
	}

	private static int StrToNum(char[] str) {
		int num = 0;
		int length = str.length;
		int prelength = length - 1;

		for (int i = 0; i < length; i++) {
			if (str[i] == 'I') {
				if (i != prelength && str[i + 1] == 'V') {
					num += 4;
					i++;
				} else if (i != prelength && str[i + 1] == 'X') {
					num += 9;
					i++;
				} else
					num += 1;
			}
			else if (str[i] == 'V') num += 5;
			else if (str[i] == 'X') {
				if (i != prelength && str[i + 1] == 'L') {
					num += 40;
					i++;
				} else if (i != prelength && str[i + 1] == 'C') {
					num += 90;
					i++;
				} else
					num += 10;
			}
			else if (str[i] == 'L') num += 50;
			else if (str[i] == 'C') {
				if (i != prelength && str[i + 1] == 'D') {
					num += 400;
					i++;
				} else if (i != prelength && str[i + 1] == 'M') {
					num += 900;
					i++;
				} else
					num += 100;
			}
			else if (str[i] == 'D') num += 500;
			else num += 1000;
		}

		return num;
	}
	
}
