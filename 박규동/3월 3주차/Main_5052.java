import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main_5052 {

	static int T, N;
	static HashSet<String> set;
	static String[] phonenums;
	static String str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			phonenums = new String[N];
			set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				phonenums[i] = br.readLine();
				set.add(phonenums[i]);
			}
			if (solution())
				System.out.println("NO");
			else
				System.out.println("YES");
		}
	}

	private static boolean solution() {

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < phonenums[i].length(); j++) {
				if (set.contains(phonenums[i].substring(0, j))) {
					return true;
				}
			}
		}

		return false;
	}
}
