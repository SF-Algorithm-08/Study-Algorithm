package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOG_S5_2116_주사위쌓기 {
	static int[][] dice;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(input));
		
		N = Integer.parseInt(in.readLine());
		dice = new int[N][6];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int up, down, sum, ans = 0;
		for(int i=0; i <6; i++) {
			sum = 0;
//			1. i=0, 1,2 돌면서 down, up 찾음
			down = dice[0][i];
			if(i == 0)
				up = dice[0][5];
			else if(i == 5)
				up = dice[0][0];
			else
				up = i < 3 ? dice[0][i+2] : dice[0][i-2];
			System.out.printf("dice [0] down: %d, up: %d\n",down, up);

//			2. 남은 주사위 중에서 최댓값 찾아서 sum에 더함
			sum += findMax(0, up, down);
			
			for(int j=1; j<N; j++) {
				for(int k=0; k<6; k++) {
//			3. 다음 주사위의 up, down을 찾음
					if(up == dice[j][k]) {
						down = up;
						if(k == 0)
							up = dice[j][5];
						else if(k == 5)
							up = dice[j][0];
						else
							up = k < 3 ? dice[j][k+2] : dice[j][k-2];
						break;
					}
				}
				System.out.printf("dice[%d] down: %d, up: %d\n",j,down, up);
//			4. sum이 최대인 것을 찾음
				sum += findMax(j, up, down);
				
			}
			System.out.println(sum);
			ans = Math.max(ans, sum);
			System.out.println("==========");
		}
		System.out.println(ans);
	}
	
	static int findMax(int idx, int up, int down) {
		int max = 0;
		for(int i = 0; i < 6; i++) {
			if(dice[idx][i] != up && dice[idx][i] != down)
				max = dice[idx][i] > max ? dice[idx][i] : max;
		}
		System.out.printf("dice %d max: %d\n", idx, max);
		return max;
	}
	
	static String input = "5\n" + 
			"2 3 1 6 5 4\n" + 
			"3 1 2 4 6 5\n" + 
			"5 6 4 1 3 2\n" + 
			"1 3 6 2 4 5\n" + 
			"4 1 6 5 2 3";
}
