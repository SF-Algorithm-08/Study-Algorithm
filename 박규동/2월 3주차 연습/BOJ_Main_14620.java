import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14620 {

	static int N,answer;
	static int[][] board;
	static boolean[][] check;
	static StringTokenizer st;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		board = new int[N][N];
		check = new boolean[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = Integer.MAX_VALUE;

		dfs(0);
		System.out.println(answer);
	}


	private static void dfs(int cnt) {
		
		if(cnt==3) {
			cal();
			return;
		}
		
		//씨앗은 일단 1~N-1 안에서 심어야 한다.
		for(int i=1;i<N-1;i++) {
			for(int j=1;j<N-1;j++) {
				if(check[i][j]) continue;
				check[i][j]=true;
				dfs(cnt+1);
				check[i][j]=false;
			}
		}
		
	}
	
	private static void cal() {
		int sum=0;
		boolean[][] visit = new boolean[N][N];
		for(int i=1;i<N-1;i++) {
			for(int j=1;j<N-1;j++) {
				if(check[i][j]) {
					sum+=board[i][j];
					visit[i][j]=true;
					for(int dir=0;dir<4;dir++) {
						int ny = i+dy[dir];
						int nx = j+dx[dir];
						if(visit[ny][nx]) return;
						visit[ny][nx]=true;
						sum+=board[ny][nx];
					}
				}
			}
		}
		
		answer = Math.min(sum, answer);
		return;
	}
	
}
