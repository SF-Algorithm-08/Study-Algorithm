package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 01타일
 */

public class BOJ_S3_1904 {
	public static int N;
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        if(N<3) {
        	System.out.println(N);
        	return;
        }
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
    	for(int n = 3; n<=N; n++){
    		arr[n] = (arr[n-1]+arr[n-2]);
    		arr[n] %= 15746;
    	}
        System.out.println(arr[N]);
	}

}
