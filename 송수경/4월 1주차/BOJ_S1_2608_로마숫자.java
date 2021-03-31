package boj.Silver;
//S1 로마 숫자 (구현,수학,문자열)
//solved
//HashMap을 사용해서 풀이했다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_S1_2608_로마숫자 {

	static HashMap<Character, Integer> dict = new HashMap<>();
	public static void main(String[] args) throws IOException {
//		1. 기호, 값을 해쉬맵으로 저장
		dict.put('I', 1);
		dict.put('V', 5);
		dict.put('X', 10);
		dict.put('L', 50);
		dict.put('C', 100);
		dict.put('D', 500);
		dict.put('M', 1000);
//		System.out.println(dict.toString());
		
//		2. 두 문자열 받아오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine().trim();
		String s2 = br.readLine().trim();
		
//		3. 문자열 -> 숫자 변환하여 합치기
		int resultInt = StringToInt(s1) + StringToInt(s2);
		
//		4. 합한 숫자 -> 문자열 변환
		String resultString = IntToString(resultInt);
		
//		5. 정답 출력
		System.out.printf("%d\n%s", resultInt, resultString);
	}

	// String -> int
	static int StringToInt(String s) {
		int num = 0;
		for(int i=0, end=s.length(); i<end; i++) {
			if(i<end-1 && dict.get(s.charAt(i))<dict.get(s.charAt(i+1))) {	//작은 숫자가 왼쪽에 오는 경우
				num += dict.get(s.charAt(i+1)) - dict.get(s.charAt(i++));
			} else num += dict.get(s.charAt(i));
		}
		return num;
	}
	
	// int -> String
	static String IntToString(int i) {
		StringBuilder sb = new StringBuilder();
		int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		int cnt = 0;	//3개 카운트
		int idx = 0;
		
		while(i!=0) {
			if(i>=numbers[idx] &&  cnt<(idx%2==0?3:1)) {
				cnt++;
				i -= numbers[idx];
				if(idx%2==0) sb.append(findKey(numbers[idx]));
				else {	//차이값에 해당하는 경우
					switch(numbers[idx]) {
					case 900:
						sb.append("CM");
						break;
					case 400:
						sb.append("CD");
						break;
					case 90:
						sb.append("XC");
						break;
					case 40:
						sb.append("XL");
						break;
					case 9:
						sb.append("IX");
						break;
					case 4:
						sb.append("IV");
						break;
					}
				}
			} else {
				cnt=0;
				idx++;
			}
		}
		
		return sb.toString();
	}
	
	// 정의한 해쉬맵에서 value 값으로 key 찾기
	static char findKey(int value) {
		for(char c : dict.keySet()) {
			if(dict.get(c) == value) return c;
		}
		return '-';
	}
}
