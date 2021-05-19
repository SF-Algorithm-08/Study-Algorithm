package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_G5_1089_스타트링크 {
	
	static final char[][][] WRAPPER = {{{'#','#','#'},{'#','.','#'},{'#','.','#'},{'#','.','#'},{'#','#','#'}},		//0
									{{'.','.','#'},{'.','.','#'},{'.','.','#'},{'.','.','#'},{'.','.','#'}},	//1
									{{'#','#','#'},{'.','.','#'},{'#','#','#'},{'#','.','.'},{'#','#','#'}},	//2
									{{'#','#','#'},{'.','.','#'},{'#','#','#'},{'.','.','#'},{'#','#','#'}},	//3
									{{'#','.','#'},{'#','.','#'},{'#','#','#'},{'.','.','#'},{'.','.','#'}},	//4
									{{'#','#','#'},{'#','.','.'},{'#','#','#'},{'.','.','#'},{'#','#','#'}},	//5
									{{'#','#','#'},{'#','.','.'},{'#','#','#'},{'#','.','#'},{'#','#','#'}},	//6
									{{'#','#','#'},{'.','.','#'},{'.','.','#'},{'.','.','#'},{'.','.','#'}},	//7
									{{'#','#','#'},{'#','.','#'},{'#','#','#'},{'#','.','#'},{'#','#','#'}},	//8
									{{'#','#','#'},{'#','.','#'},{'#','#','#'},{'.','.','#'},{'#','#','#'}}};	//9
	static int N, R, C, cnt;
	static char arr[][];
	static ArrayList<Integer>[] nums;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		R = 5;
		C = 3 * N + 1 * (N-1);
		
		arr = new char[R][C];
		for(int r = 0; r < R; r++) 
			arr[r]= in.readLine().toCharArray();
		
		findNums();
	
		double ans = findSum();
		if(ans == 0)	System.out.println("-1");
		else			System.out.printf("%.5f\n",ans);
	}
	
	static double findSum() {
		double avg = 0f;
		int mul = 1;
		
		for(int i = N-1; i >= 0; i--, mul *= 10) {	//끝자리(1의자리)부터 더해준다. 
			long sum = 0;	//99999999까지므로 long형 안하면 오버플로우 
			int cnt = 0;
			for(int j = 0, len = nums[i].size(); j < len; j++) {
				sum += nums[i].get(j) * mul;
				cnt++;
			} 
			if(cnt == 0) 	return 0;		//만약 중간 자리가 가능한 수가 없다면 -1 출력 
			avg += sum / (double)cnt;		// 각 자리수에서 더한 값에 mul 곱해주고, 평균 내주기.   		
		}
		return avg;
	}
	
	static void findNums() {
		nums = new ArrayList[N];	//각 자리수에 대해 나올 수 있는 수를 저장 
		for(int i = 0; i < N; i++) {
			nums[i] = new ArrayList<Integer>();
		} 
		
		int idx = 0;
		for(int n = 0; n < N; n++) {
			idx = 3 * n + 1 * n;	
			//각 자리수에 대해 0~9까지 전부 대입해보기 
			loop:
			for(int i = 0; i < 10; i++) {
				for(int r = 0; r < R; r++) {
					for(int c = 0; c < 3; c++) {
						// NUM엔 불이 꺼져 있는데 입력엔 불이 켜져 있으면 그 수는 안됨 
						if(WRAPPER[i][r][c] == '.' && arr[r][idx + c] == '#') {
							continue loop;
						}
					}
				}
				nums[n].add(i);
			}
		}
	}
}