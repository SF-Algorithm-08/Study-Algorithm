package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_S2_1535_안녕 {
	static int N;
	static int[] h;
	static int[] j;
	static int max = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		in = new BufferedReader(new StringReader(input));
		
		N = Integer.parseInt(in.readLine());
		h = new int[N];
		j = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringTokenizer st2 = new StringTokenizer(in.readLine(), " ");
		for(int i=0; i<N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			j[i] = Integer.parseInt(st2.nextToken());
		}
		
		find();
	}
	
	static void find() {
		int health, joy;
		for(int i=0, end = 1<<N; i<end; i++) {
			health = 100; 
			joy = 0;
			for(int flag = 0; flag < N; flag++) {
				if((i & 1 << flag) != 0) {
					health -= h[flag];
					joy += j[flag];
				}
			}
			if(health > 0)
				max = Math.max(max, joy);
		}
		System.out.println(max);
	}
	
	static String input = "3\n" + 
			"1 21 79\n" + 
			"20 30 25";
}
