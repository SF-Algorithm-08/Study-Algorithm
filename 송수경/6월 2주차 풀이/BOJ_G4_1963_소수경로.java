package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_1963_소수경로 {
	static class Number {
		int num, level;
		
		public Number(int num, int level) {
			this.num = num;
			this.level = level;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine()," ");
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			if(num1==num2) { sb.append("0\n"); continue; }
			
			sb.append(bfs(num1, num2)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static String bfs(int num1, int num2) {
		Queue<Number> queue = new LinkedList<>();
		queue.offer(new Number(num1, 0));
		boolean[] checked = new boolean[10000];
		checked[num1] = true;
		
		while(!queue.isEmpty()) {
			Number number = queue.poll();
			if(number.num==num2) return ""+number.level;
			
			int newNum;
			String strNum = ""+number.num;
			for(int i=1, idx=2; i<=1000; i*=10) {
				newNum = number.num + i;
				String strNew = ""+newNum;
//				if(!checked[newNum] && strNew.charAt(idx) == strNum.charAt(idx) && checkPrime(newNum)) {
//					queue.offer(new Number(newNum, number.level+1));
//					checked[newNum] = true;
//				}
				
				
				newNum = number.num - i;
			}
		}
		
		return "Impossible";
	}

}
