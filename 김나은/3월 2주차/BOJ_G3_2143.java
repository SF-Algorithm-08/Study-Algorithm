// 두 배열의 합

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G3_2143 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> sumA = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int sum = A[i];
			sumA.add(sum);
			for (int j = i + 1; j < n; j++) {
				sum += A[j];
				sumA.add(sum);
			}
		}
		ArrayList<Integer> sumB = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int sum = B[i];
			sumB.add(sum);
			for (int j = i + 1; j < m; j++) {
				sum += B[j];
				sumB.add(sum);
			}
		}
		Collections.sort(sumA);
		Collections.sort(sumB);

		int idxA = 0, idxB = sumB.size() - 1;
		long cnt = 0;
		while (idxA < sumA.size() && idxB >= 0) {
			int tempA = sumA.get(idxA);
			int tempB = sumB.get(idxB);

			if (tempA + tempB == T) {
				int cntA = 0, cntB = 0;
				while (idxA < sumA.size() && tempA == sumA.get(idxA)) {
					idxA++;
					cntA++;
				}
				while (idxB >= 0 && tempB == sumB.get(idxB)) {
					idxB--;
					cntB++;
				}
				cnt += (long) cntA * (long) cntB;
			} else if (tempA + tempB < T) {
				idxA++;
			} else {
				idxB--;
			}
		}

		System.out.println(cnt);
	}
}
