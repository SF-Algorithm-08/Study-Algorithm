import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17825 {

	static int[] dice;
	static int[] seq;
	static StringTokenizer st;
	static Horse[] horse;
	static final int[][] board= {
			{2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
			{10,13,16,19,25,30,35,40},
			{20,22,24,25,30,35,40},
			{30,28,27,26,25,30,35,40}
	};
	
	static class Horse {
		int line;
		int loc;
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		dice = new int[10];
		seq = new int[10];
		
		for(int i=0;i<10;i++) dice[i] = Integer.parseInt(st.nextToken());
		
		perm(0);
		
	}

	private static void perm(int idx) {
		if(idx==10) {
			move();
			return;
		}
		for(int i=0;i<4;i++) {
			seq[idx]=i;
			perm(idx+1);
		}
		
	}

	private static void move() {
		
		for(int i=0;i<10;i++) {
			int curNode = seq[i];
			
		}
		
	}


}
