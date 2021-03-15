// 전생했더니 슬라임 연구자였던 건에 대하여 (Easy)

package algo;

import java.util.Scanner;

public class BOJ_S1_14715 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int cnt = 0;

		for (int i = 2; i <= (int) Math.sqrt(K); i++) {
			while (K % i == 0) {
				cnt++;
				K /= i;
			}
		}

		if (K != 1) cnt++;
		System.out.println((int) Math.ceil((Math.log10(cnt) / Math.log10(2))));

	}
}