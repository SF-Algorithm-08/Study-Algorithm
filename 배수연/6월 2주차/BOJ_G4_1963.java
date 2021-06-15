package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 소수 경로 - 소수, BFS
 * 시작 숫자에서부터 자리수 별로 값을 하나씩 바꿔가면서 그 수가 소수인지 판단
 * 숫자 바꿔보다가 목표값과 같다면 거기까지 도달하는 데 걸린 횟수를 최소값으로 갱신
 * BFS에서 특정 수가 소수인지 판별하는 메소드가 많이 호출됨
 * => 1000~9999 값들을 미리 소수인지 검사해서 저장해두고 배열값 참조만 해주기
 */

public class BOJ_G4_1963 {
	static boolean[] prime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		prime = new boolean[10000];
		for(int i = 1000 ; i<10000 ; i++) {
			prime[i] = isPrime(i);
		}
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test<=T ; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			func(from, to);
		}
	}
	
	public static boolean isPrime(int num) {
		if(num == 1) return false;
		for(int i = 2 ; i<=num/2 ; i++) {
			if(num%i == 0) return false;
		}
		return true;
	}
	
	private static void func(int from, int to) {
		int count = Integer.MAX_VALUE;
		boolean[] visited = new boolean[10000];
		Queue<int[]> queue = new LinkedList<int[]>(); // 현재 num, cnt
		queue.offer(new int[] {from, 0});
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int temp_num = temp[0];
			int cnt = temp[1];
			if(temp_num == to) count = Math.min(count, cnt);
			if(temp_num>=1000 && !visited[temp_num]) {
				visited[temp_num] = true;
				for(int i = 0 ; i<4 ; i++) {
					for(int j = 0 ; j<=9 ; j++) {
						if(i == 0 && j == 0) continue;
						// 새로운 수 만들기
						String origin = String.valueOf(temp_num);
						int mul = 3-i;
						int new_num = (int) (temp_num - Math.pow(10, mul)*(origin.charAt(i)-'0'));
						new_num += Math.pow(10, mul)*j;
						if(new_num>=1000 && prime[new_num]) {
							queue.offer(new int[] {new_num, cnt+1});
						}
					}
				}
			}
		}
		if(count == Integer.MAX_VALUE) System.out.println("Impossible");
		else System.out.println(count);
	}
}
