package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_1535 {
	static int N;
	static int[] power;
	static int[] joy;
	static int max_joy;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		power = new int[N];
		joy = new int[N];
		ans = 0;
		for(int n = 0 ; n<N ; n++) {
			power[n] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int n = 0 ; n<N ; n++) {
			joy[n] = Integer.parseInt(st.nextToken());
		}
		check();
		System.out.println(ans);
	}
	public static void check() {
		for(int i = 0 ; i<(1<<N) ; i++) {
			int p = 100, happy = 0;
			for(int j = 0 ; j<N ; j++) {
				if((i&(1<<j)) != 0) {
					p -= power[j];
					happy += joy[j];
				}
				if(p<=0) break;
				ans = Math.max(ans, happy);
			}
		}
	}

}

/*
3
1 21 79
20 30 25
*/