// 사다리 타기

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2469 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine()); // 참가한 사람의 수
		int n = Integer.parseInt(br.readLine()); // 가로 막대가 놓일 전체 가로 줄의 수

		char[] input = new char[k];
		for (int i = 0; i < k; i++) {
			input[i] = (char) (i + 65);
		}

		char[] output = br.readLine().toCharArray(); // 사다리를 타고 난 후 결정된 참가자들의 최종 순서
		
		char[][] arr = new char[n][k];
		int idx = 0; // 감추어진 가로 줄
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
			if (arr[i][0] == '?') idx = i;
		}

		// 물음표를 기준으로 윗쪽
		for (int r = 0; r < idx; r++) {
			for (int c = 0; c < k - 1; c++) {
				if (arr[r][c] == '-') swap(input, c);
			}
		}

		// 물음표를 기준으로 아래쪽
		for (int r = n - 1; r > idx; r--) {
			for (int c = 0; c < k - 1; c++) {
				if (arr[r][c] == '-') swap(output, c);
			}
		}

		// 위쪽과 아래쪽를 비교
		char[] ans = new char[k-1];
		boolean flag = false;
		for (int i = 0; i < k-1; i++) {
			if (input[i] == output[i]) {
				ans[i] = '*';
			} else if (input[i] == output[i + 1] && input[i + 1] == output[i]) {
				ans[i] = '-';
				swap(input, i);
			} else {
				flag = true;
				break;
			}
		}

		if (flag) Arrays.fill(ans, 'x');
		for (int i = 0; i < k-1; i++) {
			System.out.print(ans[i]);
		}
	}

	private static void swap(char[] a, int i) {
		char temp;
		temp = a[i];
		a[i] = a[i + 1];
		a[i + 1] = temp;
	}
}
