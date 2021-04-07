package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S2_2469_사다리타기 {
	static int k;
	static int n;
	static char[] up;
	static char[] down;
	static char[][] ladder;
	static String answer = null;
	static int lineIdx;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(in.readLine());
		n = Integer.parseInt(in.readLine());
		
		down = new char[k];
		up = new char[k];
		ladder = new char[n][k-1];
		
		String line = in.readLine();
		for(int i=0; i<k; i++) {
			down[i] = line.charAt(i);
			up[i] =  (char)(i + 65);
		}
		
		for(int i=0; i<n; i++) {
			line = in.readLine();
			for(int j=0; j<k-1; j++) {
				ladder[i][j] = line.charAt(j);
				if(ladder[i][j] == '?') {
					lineIdx = i;
					break;
				}
			}
		}
		
		find();
	}
	static void find() {
		// 위에서부터 비교 
		for(int l = 0; l < lineIdx; l++) {
			for(int c = 0; c < k-1; c++) {
				if(ladder[l][c] == '-') {
					char temp = up[c];
					up[c] = up[c+1];
					up[c+1] = temp;
				}
			}
		}
		// 아래에서부터 비교
		for(int l=n-1; l>lineIdx; l--) {
			for(int c = 0; c < k-1; c++) {
				if(ladder[l][c] == '-') {
					char temp = down[c];
					down[c] = down[c+1];
					down[c+1] = temp;
				}
			}
		}
		
		//up, down 체크
		for(int c=0; c<k-1; c++) {
			if(up[c] == down[c]) {
				ladder[lineIdx][c] = '*';
		
			}else if(up[c] == down[c+1] && up[c+1] == down[c]) {
				ladder[lineIdx][c] = '-';
				char temp = up[c];
				up[c] = up[c+1];
				up[c+1] = temp;
			}else {
				Arrays.fill(ladder[lineIdx], 'x');
				break;
			}
		}
		for(int i=0; i<k-1; i++)
			System.out.printf("%c",ladder[lineIdx][i]);
	}
}
