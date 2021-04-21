package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_4386_별자리만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		double[][] adjMatrix = new double[N][N];
		double[][] pos = new double[N][2];
		boolean[] visited = new boolean[N];
		double[] minEdge = new double[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Double.parseDouble(st.nextToken());
			pos[i][1] = Double.parseDouble(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				double dist = getDist(pos[i][0], pos[i][1], pos[j][0], pos[j][1]);
				adjMatrix[j][i] = adjMatrix[i][j] = dist;
			}
			minEdge[i] = Double.MAX_VALUE;
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
				if (!visited[j] && adjMatrix[minVertex][j] != 0 && minEdge[j] > adjMatrix[minVertex][j]) {
					minEdge[j] = adjMatrix[minVertex][j];
				}
			}
		}

		System.out.printf("%.2f", result);
	}

	private static double getDist(double X1, double Y1, double X2, double Y2) {
		return Math.sqrt(Math.pow(X1 - X2, 2) + Math.pow(Y1 - Y2, 2));
	}
}
