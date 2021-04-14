import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_8972 {

	static int[] dy = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dx = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static char[][] board;
	static final String gameover = "kraj ";
	static StringBuilder sb;
	static StringTokenizer st;
	static int R, C;
	static String cmd;
	static Info jongsoo;
	static ArrayList<Info> crazy, remv;
	static int[][] bomb;

	static class Info {
		int y;
		int x;

		public Info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];

		String input = "";
		for (int i = 0; i < R; i++) {
			input = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = input.charAt(j);
				if (board[i][j] == 'I')
					jongsoo = new Info(i, j);
			}
		}
		StringBuilder sb = new StringBuilder();
		cmd = br.readLine();
		if (move())
			return;
		else {
			for (int i = 0; i < R; i++) {
				for(int j=0;j<C;j++) {
					if(board[i][j]=='I') sb.append("I");
					else {
						sb.append(board[i][j]=='R'?"R":".");
					}
				}
				sb.append("\n");
			}
			System.out.println(sb);
		}

	}

	private static boolean move() {

		int maxlen = cmd.length();
		int idx = 0;

		while (idx < maxlen) {
			int dir = cmd.charAt(idx) - '0' - 1;
			int ny = jongsoo.y + dy[dir];
			int nx = jongsoo.x + dx[dir];

			if (board[ny][nx] == 'R') {
				System.out.println(gameover + (idx+1));
				return true;
			}
			board[jongsoo.y][jongsoo.x] = '.';
			board[ny][nx] = 'I';
			jongsoo = new Info(ny, nx);
			bomb = new int[R][C];
			if (moveCrazy()) {
				System.out.println(gameover + (idx+1));
				return true;
			}
			bomb();
			idx++;
		}
		return false;

	}

	private static boolean moveCrazy() {

		crazy = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 'R') {
					bomb[i][j]=1;
					crazy.add(new Info(i, j));
				}
			}
		}

		for (int i = 0; i < crazy.size(); i++) {
			int cy = crazy.get(i).y;
			int cx = crazy.get(i).x;
			bomb[cy][cx]--;
			int tmp = Integer.MAX_VALUE;
			int dir = 0;

			for (int j = 0; j < 9; j++) {
				int ny = cy + dy[j];
				int nx = cx + dx[j];
				if (ny < 0 || nx < 0 || ny >= R || nx >= C)
					continue;
				int dist = Math.abs(ny - jongsoo.y) + Math.abs(nx - jongsoo.x);
				if (tmp > dist) {
					tmp = dist;
					dir = j;
				}
			}
			int ny = cy + dy[dir];
			int nx = cx + dx[dir];
			bomb[ny][nx]++;
			if (ny == jongsoo.y && nx == jongsoo.x)
				return true;
		}
		return false;
	}

	private static void bomb() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(i==jongsoo.y&&j==jongsoo.x) continue;
				if (bomb[i][j]==1) {
					board[i][j] = 'R';
				} else {
					board[i][j]='.';
				}
			}
		}
	}

}
