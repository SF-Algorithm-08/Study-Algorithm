import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1026 {

	static int[] A;
	static Integer[] B;
	static int N,answer;
	static StringTokenizer sta,stb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		A = new int[N];
		B = new Integer[N];
		sta = new StringTokenizer(br.readLine());
		stb = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(sta.nextToken());
			B[i] = Integer.parseInt(stb.nextToken());
		}
		Arrays.sort(A);
		Arrays.sort(B,new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
		for(int i=0;i<N;i++) {
			answer += A[i]*B[i];
		}
		System.out.println(answer);

	}

}
