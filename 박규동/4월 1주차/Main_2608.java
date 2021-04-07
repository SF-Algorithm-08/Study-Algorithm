import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_2608 {

	static HashMap<String, Integer> rome;
	static int answer;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expr1 = br.readLine();
		String expr2 = br.readLine();
		sb = new StringBuilder();
		makeSet();
		answer = parsingToInteger(expr1) + parsingToInteger(expr2);
		System.out.println(answer);
		parsintToRome(answer);
		System.out.println(sb.toString());
		
	}

	private static void makeSet() {
		rome = new HashMap<>();
		rome.put("I", 1);
		rome.put("V", 5);
		rome.put("X", 10);
		rome.put("L", 50);
		rome.put("C", 100);
		rome.put("D", 500);
		rome.put("M", 1000);
		rome.put("IV", 4);
		rome.put("IX", 9);
		rome.put("XL", 40);
		rome.put("XC", 90);
		rome.put("CD", 400);
		rome.put("CM", 900);

	}

	private static int parsingToInteger(String expr) {

		int romeint = 0;

		for (int i = 0; i < expr.length();) {
			for (int j = 2; j > 0; j--) {
				if (i == expr.length() - 1 && j == 2)
					continue;
				String token = expr.substring(i, i + j);
				if (rome.containsKey(token)) {
					romeint += rome.get(token);
					i += token.length();
					break;
				}
			}
		}
		return romeint;
	}

	private static void parsintToRome(int result) {

		if (result >= 1000) {
			int cnt = result / 1000;
			for (int i = 0; i < cnt; i++) {
				sb.append("M");
			}
			result %= (cnt * 1000);
		}
		if (result >= 900) {
			sb.append("CM");
			result -= 900;
		}
		if (result >= 500) {
			sb.append("D");
			result -= 500;
		}
		if (result >= 400) {
			sb.append("CD");
			result -= 400;
		}
		if (result >= 100) {
			int cnt = result / 100;
			for (int i = 0; i < cnt; i++) {
				sb.append("C");
			}
			result %= (cnt * 100);
		}
		if (result >= 90) {
			sb.append("XC");
			result -= 90;
		}
		if (result >= 50) {
			sb.append("L");
			result -= 50;
		}
		if (result >= 40) {
			sb.append("XL");
			result -= 40;
		}
		if (result >= 10) {
			int cnt = result / 10;
			for (int i = 0; i < cnt; i++) {
				sb.append("X");
			}
			result %= (cnt * 10);
		}
		if (result >= 9) {
			sb.append("IX");
			result -= 9;
		}
		if (result >= 5) {
			sb.append("V");
			result -= 5;
		}
		if (result >= 4) {
			sb.append("IV");
			result -= 4;
		}
		if (result >= 1) {
			int cnt = result;
			for (int i = 0; i < cnt; i++) {
				sb.append("I");
			}
		}
	}

}
