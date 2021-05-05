import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14391 {
	
	static int N,M,answer;
	static StringTokenizer st;
	static StringBuilder sb;
	static String[][] board;
	
	public static void main(String[] args) throws IOException,NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		board = new String[N][M];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				board[i][j] = str.substring(j, j+1);
			}
		}
		garo();
		sero();
		System.out.println(N+M);
		System.out.println(answer);
	}

	private static void sero() {
		int sum=0;
		for(int j=0;j<M;j++) {
			sb=new StringBuilder();
			boolean flag=true;
			for(int i=0;i<N;i++) {
				if(flag&&board[i][j].equals("0")) {
					continue;
				}else {
					flag=false;
					sb.append(board[i][j]);
				}
			}
			if(sb.toString().equals("")) continue;
			sum+=Integer.parseInt(sb.toString());
		}
		answer = Math.max(sum, answer);
	}

	private static void garo() {
		
		int sum=0;
		for(int i=0;i<N;i++) {
			sb=new StringBuilder();
			boolean flag=true;
			for(int j=0;j<M;j++) {
				if(flag&&board[i][j].equals("0")) {
					continue;
				}else {
					flag=false;
					sb.append(board[i][j]);
				}
			}
			if(sb.toString().equals("")) continue;
			sum+=Integer.parseInt(sb.toString());
		}
		answer = sum;
	}

}
