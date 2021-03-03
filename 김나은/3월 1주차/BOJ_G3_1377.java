// 버블 소트

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Pos implements Comparable<Pos> {
	int val, idx;

	Pos(int val, int idx) {
		this.val = val;
		this.idx = idx;
	}

	@Override
	public int compareTo(Pos o) {
		return this.val - o.val;
	}
}

public class BOJ_G3_1377 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pos[] arr = new Pos[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = new Pos(Integer.parseInt(br.readLine()), i);
		}
		Arrays.sort(arr, 1, N + 1); // fromIndex(inclusive) to the index toIndex(exclusive)

		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, arr[i].idx - i);
		}

		System.out.println(max + 1);

	}
}
