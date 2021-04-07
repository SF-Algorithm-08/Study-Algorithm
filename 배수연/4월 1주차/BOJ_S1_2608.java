package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 로마 숫자
 */

public class BOJ_S1_2608 {
	static char[] Rome = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	static int[] value = {1, 5, 10, 50, 100, 500, 1000};
	static String[] Rome2 = {"IV", "IX", "XL", "XC", "CD", "CM"};
	static int[] value2 = {4, 9, 40, 90, 400, 900};
	static String[] RomeTotal = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; // num->str에서 사용해야 하므로 내림차순
	static int[] valueTotal = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int sum = StrToNum(str1) + StrToNum(str2);
		System.out.println(sum);
		
		String ans = NumToStr(sum);
		System.out.println(ans);
	}

	private static int StrToNum(String str) {
		int len = str.length();
		int num = 0;
		
		int idx = 0;
		boolean isEnd = false; // 끝에서 두 번째 문자(len-2번째)가 한 글자 짜리인지 두 글자 짜리인지 판단할 변수. 한 글자 짜리였다면 맨 마지막 글자(len-1번째)의 수를 더해줘야 한다.
		while(idx<len-1) {
			boolean isTwo = false; // 우선순위인 두 글자 문자를 먼저 판단해보고 idx를 조절해준다.
			String temp = str.substring(idx, idx+2); // 문자열을 두 글자씩 끊어서 비교
			for(int i = 0 ; i<Rome2.length ; i++) { // 두 글자짜리 문자열을 저장한 배열에서 하나씩 비교해보기
				if(temp.equals(Rome2[i])) {
					num += value2[i];
					isTwo = true;
					if(idx == len-2) isEnd = true;
					break;
				}
			}
			if(isTwo) idx += 2;
			else {
				for(int i = 0 ; i<Rome.length ; i++) {
					if(str.charAt(idx) == Rome[i]) num += value[i];
				}
				idx++;
			}
		}
		
		if(!isEnd) {
			for(int i = 0 ; i<Rome.length ; i++) {
				if(str.charAt(len-1) == Rome[i]) num += value[i];
			}
		}
		return num;
	}
	private static String NumToStr(int sum) {
		String ans = "";
		
		// 두 번째 시도한 방법 -> 정답!
		// 전체 Rome과 value 값들을 내림차순으로 정렬한 RomeTotal, valueTotal배열을 새로 생성해줌
		// valueTotal[i]값과 순서대로 비교하면서 sum값에서 일치하는 값을 빼고 정답 문자열에는 RomeTotal[i]를 더해준다.
		
		while(sum>0) {
			for(int i = 0 ; i<RomeTotal.length ; i++) {
				if(sum>=valueTotal[i]) {
					sum -= valueTotal[i];
					ans += RomeTotal[i];
					break;
				}
			}
		}
		
		// 처음 시도한 방법 -> 예제는 맞았지만 제출하자마자 '틀렸습니다'
		// 1. 숫자를 천의자리, 백의자리, 십의자리, ... 따로따로 분리해서 배열에 저장 (ex. 2493 -> [2000, 400, 90, 3])
		// 2. Rome2에 해당하는 숫자들은 반복해서 사용이 불가하고 값이 고정이기 때문에, 각 자리 수 별로 value2 배열의 값과 먼저 비교를 해본다.
		// 2-1. 예시의 경우, 400, 90이 판단 가능
		// 3. value2에 일치하는 값이 아닌 경우에는 value값과 비교하는데, 내림차순으로 정렬한 value[j]값과 나누기해서 그 몫만큼 문자열을 반복한다.
		// 3-1. 예시의 경우, 2000을 1000으로 나눈 몫만큼 반복 => M 두번 붙이기
		
//		int len = String.valueOf(sum).length();
//		int temp = sum;
//		int[] num = new int[len];
//		
//		// 1. 숫자를 자리수 별로 따로따로 분리해서 배열에 저장
//		for(int i = 0, k = len-1 ; i<len ; i++, k--) {
//			num[i] = (int) (temp/Math.pow(10, k));
//			num[i] *= Math.pow(10, k);
//			temp %= Math.pow(10, k);
//		}
//		
//		top:
//		for(int i = 0 ; i<len ; i++) {
//			// 2. value2와 비교
//			for(int j = 0 ; j<value2.length ; j++) {
//				if(num[i] == value2[j]) {
//					ans += Rome2[j];
//					continue top;
//				}
//			}
//			// 3. value를 내림차순하고 비교
//			for(int j = value.length-1 ; j>=0 ; j--) {
//				if(num[i]>=value[j]) {
//					int cnt = num[i]/value[j];
//					for(int c = 0 ; c<cnt ; c++) {
//						ans += Rome[j];
//					}
//					continue top;
//				}
//			}
//		}
		return ans;
	}
}
