import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16113 {

	static String one,two,three,four,five,six,seven,eight,nine,zero;
	static int N, len;
	static String str;
	static char[][] code;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		len = N / 5;
		code = new char[5][len];
		int count = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < len; j++) {
				code[i][j] = str.charAt(count++);
			}
		}
		sb = new StringBuilder();
		solution();
		System.out.println(sb.toString());
	}

	private static void solution() {

		if (N < 15) {
			if (N == 5) {
				if (code[0][0] == '#')
					sb.append("1");
				return;
			} else {
				String tmp0 = "";
				String tmp1 = "";
				String tmp2 = "";
				String tmp3 = "";
				for (int i = 0; i < 2; i++) {
					tmp0 += code[0][i];
					tmp1 += code[1][i];
					tmp2 += code[2][i];
					tmp3 += code[3][i];
				}

				if (tmp0.equals("#.") && tmp1.equals("#.") && tmp2.equals("#.") && tmp3.equals("#."))
					sb.append("1");

				if (tmp0.equals(".#") && tmp1.equals(".#") && tmp2.equals(".#") && tmp3.equals(".#"))
					sb.append("1");
				return;
			}
		}

		String tmp0 = "";
		String tmp1 = "";
		String tmp2 = "";
		String tmp3 = "";
		for (int i = 0; i < 2; i++) {
			tmp0 += code[0][i];
			tmp1 += code[1][i];
			tmp2 += code[2][i];
			tmp3 += code[3][i];
		}

		if (tmp0.equals("#.") && tmp1.equals("#.") && tmp2.equals("#.") && tmp3.equals("#."))
			sb.append("1");

		for (int start = 0; start < len - 2; start++) {
			tmp0 = "";
			tmp1 = "";
			tmp2 = "";
			tmp3 = "";
			for (int i = start; i < start + 3; i++) {
				tmp0 += code[0][i];
				tmp1 += code[1][i];
				tmp2 += code[2][i];
				tmp3 += code[3][i];
			}
			if (tmp0.equals("###") && tmp1.equals("#.#") && tmp2.equals("#.#") && tmp3.equals("#.#"))
				sb.append("0");
			if (tmp0.equals(".#.") && tmp1.equals(".#.") && tmp2.equals(".#.") && tmp3.equals(".#."))
				sb.append("1");
			if (tmp0.equals("###") && tmp1.equals("..#") && tmp2.equals("###") && tmp3.equals("#.."))
				sb.append("2");
			if (tmp0.equals("###") && tmp1.equals("..#") && tmp2.equals("###") && tmp3.equals("..#"))
				sb.append("3");
			if (tmp0.equals("#.#") && tmp1.equals("#.#") && tmp2.equals("###") && tmp3.equals("..#"))
				sb.append("4");
			if (tmp0.equals("###") && tmp1.equals("#..") && tmp2.equals("###") && tmp3.equals("..#"))
				sb.append("5");
			if (tmp0.equals("###") && tmp1.equals("#..") && tmp2.equals("###") && tmp3.equals("#.#"))
				sb.append("6");
			if (tmp0.equals("###") && tmp1.equals("..#") && tmp2.equals("..#") && tmp3.equals("..#"))
				sb.append("7");
			if (tmp0.equals("###") && tmp1.equals("#.#") && tmp2.equals("###") && tmp3.equals("#.#"))
				sb.append("8");
			if (tmp0.equals("###") && tmp1.equals("#.#") && tmp2.equals("###") && tmp3.equals("..#"))
				sb.append("9");
		}

		tmp0 = "";
		tmp1 = "";
		tmp2 = "";
		tmp3 = "";
		for (int i = len - 2; i < len; i++) {
			tmp0 += code[0][i];
			tmp1 += code[1][i];
			tmp2 += code[2][i];
			tmp3 += code[3][i];
		}
		if (tmp0.equals(".#") && tmp1.equals(".#") && tmp2.equals(".#") && tmp3.equals(".#"))
			sb.append("1");
	}
}