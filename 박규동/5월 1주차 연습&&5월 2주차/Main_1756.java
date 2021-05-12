import java.io.*;
import java.util.*;

public class Main_1756 {

	static int D,N,answer,depth;
	static int[] oven,pizza;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		oven = new int[D];
		pizza = new int[N];
		st=new StringTokenizer(br.readLine());
		int max = Integer.MAX_VALUE;
		for(int i=0;i<D;i++) {
			int cur = Integer.parseInt(st.nextToken());
			if(cur<max) max=cur;
			oven[i]=max;
		}
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) pizza[i]=Integer.parseInt(st.nextToken());
		answer = solve();
		System.out.println(answer+1);
		
	}

	private static int solve() {
		depth = D;
		for(int i=0;i<N;i++) {
			int cur = pizza[i];
			if(depth<N-i) return -1;
			int start=0;
			int end=depth-1;
			while(start<=end) {
				int mid = (start+end)/2;
				if(oven[mid]>=cur) {
					start = mid+1;
				}
				else {
					end = mid-1;
				}
			}
			depth = end;
		}
		
		return depth;
	}

}
