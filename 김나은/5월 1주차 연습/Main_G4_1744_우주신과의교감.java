package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_1744_우주신과의교감 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] pos = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}

		double[][] adjMatrix = new double[N][N];
		boolean[] visited = new boolean[N];
		double[] minEdge = new double[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				adjMatrix[j][i] = adjMatrix[i][j] = getDist(pos[i][0], pos[i][1], pos[j][0], pos[j][1]);
			}
			minEdge[i] = Double.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			adjMatrix[X-1][Y-1] = adjMatrix[Y-1][X-1] = 0;
		}

		double result = 0;
		minEdge[0] = 0;

		for (int c = 0; c < N; c++) {
			double min = Double.MAX_VALUE;
			int minVertex = 0;

			for (int i = 0; i < N; i++) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}

			result += min;
			visited[minVertex] = true;

			for (int j = 0; j < N; j++) {
				if (!visited[j] && minEdge[j] > adjMatrix[minVertex][j]) {
					minEdge[j] = adjMatrix[minVertex][j];
				}
			}
		}

		System.out.printf("%.2f", result);
	}

	private static double getDist(int r1, int c1, int r2, int c2) {
		return Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(c1 - c2, 2));
	}
}
