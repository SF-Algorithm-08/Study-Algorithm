import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2075 {

	static int N,answer;
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int input = Integer.parseInt(st.nextToken());
				if(pq.size()<N) {
					pq.add(input);
				} else {
					pq.add(input);
					pq.poll();
				}
			}
		}
		answer = pq.poll();
		System.out.println(answer);
		
	}

}
