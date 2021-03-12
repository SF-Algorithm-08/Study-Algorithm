import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16113_1 {

	static String one = "####.##.##.#";
	static String two = ".#..#..#..#.";
	static String three = "###..#####..";
	static String four = "#.##.####..#";
	static String five = "####..###..#";
	static String six = "####..####.#";
	static String seven = "###..#..#..#";
	static String eight = "####.#####.#";
	static String nine = "####.####..#";
	static String zero = "####.##.##.#";
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
				String tmp = "";
				for (int i = 0; i < 2; i++) {
					for(int j=0;j<4;j++)
						tmp+=code[j][i];
				}

				if (tmp.equals("#.#.#.#."))
					sb.append("1");

				if (tmp.equals(".#.#.#.#"))
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
			String tmp = "";
			for (int i = start; i < start + 3; i++) {
				for(int j=0;j<4;j++) 
					tmp+=code[j][i];
			}
			if (tmp.equals(zero))
				sb.append("0");
			if (tmp.equals(one))
				sb.append("1");
			if (tmp.equals(two))
				sb.append("2");
			if (tmp.equals(three))
				sb.append("3");
			if (tmp.equals(four))
				sb.append("4");
			if (tmp.equals(five))
				sb.append("5");
			if (tmp.equals(six))
				sb.append("6");
			if (tmp.equals(seven))
				sb.append("7");
			if (tmp.equals(eight))
				sb.append("8");
			if (tmp.equals(nine))
				sb.append("9");
		}


		String tmp="";
		for (int i = len-3; i < len; i++) {
			for(int j=0;j<4;j++) 
				tmp+=code[j][i];
		}
		if (tmp.equals(".#.#.#.#"))
			sb.append("1");
	}
}