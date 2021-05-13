package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17298_오큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) num[i] = Integer.parseInt(st.nextToken());
		
		for(int i=N-2, flag=num[N-1], max=num[N-1]; i>-1; i--) {
			int cur = num[i];
			
			if(num[i]<flag) num[i] = flag;
			else if(num[i]<num[i+1]) num[i] = num[i+1];
			else if(num[i]<max) num[i] = max;
			else num[i] = -1;
			
			max = Math.max(flag>cur?flag:cur, max);
			flag = cur;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N-1; i++) sb.append(num[i]).append(" ");
		sb.append(-1);
		System.out.println(sb);
	}

}
