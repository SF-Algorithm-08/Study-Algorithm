package boj.Silver;
//S1 1074 (분할 정복,재귀)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1074_Z {
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(2, N);	//검사하는 사각형의 한 변의 길이
		int lr = size/2;	//검사할 경계 라인(행)
		int lc = size/2;	//검사할 경계 라인(열)
		int point=lr*lr;	//각 사분면을 채우는 칸 수
		int half=size/4;
		ans=0;
		do {
			if(R<lr && C<lc) {	//1사분면
				lr -= half;
				lc -= half;
			} else if(R<lr)	{	//2사분면
				ans += point;
				lr -= half;
				lc += half;
			} else if(C<lc) {	//3사분면
				ans += point*2;
				lr += half;
				lc -= half;
			} else {			//4사분면
				ans += point*3;
				lr += half;
				lc += half;
			}
			half/=2;
			point/=4;
		} while(point>0);

		System.out.print(ans);
	}
}
