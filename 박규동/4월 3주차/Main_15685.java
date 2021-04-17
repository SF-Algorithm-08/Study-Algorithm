import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15685 {

	static boolean[][] board;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static ArrayList<Integer> pattern;

	static StringTokenizer st;
	static int n,answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new boolean[101][101];
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());

			makePattern(dir, gen);
			drawDragon(sx, sy);
		}
		count();
		System.out.println(answer);
	}

	private static void count() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(board[i][j]&&board[i+1][j]&&board[i][j+1]&&board[i+1][j+1]) answer++;
			}
		}
	}

	private static void makePattern(int dir, int gen) {
		pattern = new ArrayList<>();
		pattern.add(dir);
		for (int i = 0; i < gen; i++) {
			int len = 1 << i;
			for (int j = 1; j <= len; j++) {
				pattern.add((pattern.get(len - j) + 1) % 4);
			}

		}
	}

	private static void drawDragon(int sx, int sy) {
		board[sx][sy] = true;
		for (int i = 0; i < pattern.size(); i++) {
			int dir = pattern.get(i);
			int nx = sx + dx[dir];
			int ny = sy + dy[dir];
			board[nx][ny] = true;
			sx = nx;
			sy = ny;
		}
	}

}
