import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14392_new {
	
	static int N,M,answer;
	static StringTokenizer st;
	static StringBuilder sb;
	static String[][] board;
	static boolean[][] visit;
	
	public static void main(String[] args) throws IOException,NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		board = new String[N][M];
		visit = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				board[i][j] = str.substring(j, j+1);
			}
		}
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int cnt) {
		if(cnt==N*M) {
			count();
			return;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dfs(cnt+1);
				visit[i][j]=true;
				dfs(cnt+1);
				visit[i][j]=true;
			}
		}
	}

	private static void count() {
		// TODO Auto-generated method stub
		
	}


}
