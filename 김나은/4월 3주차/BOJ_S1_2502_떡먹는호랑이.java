package algo;

import java.util.Scanner;

public class BOJ_S1_2502_떡먹는호랑이 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		int K = sc.nextInt();

		int[][] coefficient = new int[D+1][2];
		coefficient[1][0] = 1;		coefficient[1][1] = 0;
		coefficient[2][0] = 0;		coefficient[2][1] = 1;
		
		for (int i = 3; i <= D; i++) {
			coefficient[i][0] = coefficient[i-2][0] + coefficient[i-1][0];
			coefficient[i][1] = coefficient[i-2][1] + coefficient[i-1][1];
		}

		int first = coefficient[D][0];
		int second = coefficient[D][1];

		int A = 1, B = 1;
		while (true) {
			if ((K - first * A) % second == 0) {
				B = (K - first * A) / second;
				break;
			}
			A++;
		}

		System.out.printf("%d\n%d", A, B);
	}
}
