package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1614_영식이의손가락 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int finger = Integer.parseInt(in.readLine());
		long num = Integer.parseInt(in.readLine());
		
		long result = 0;
		if(finger == 1 || finger == 5) {
			result = (finger-1) + 8 * num;
		}else {
			result = (finger-1) + (num/2) * 8 + (num%2) *(8 - 2*(finger-1));
		}
		System.out.println(result);
	}

}
