import java.io.*;

public class Main_1614 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long finger = Long.parseLong(br.readLine());
		long cnt = Long.parseLong(br.readLine());
		
		if(cnt==0) {
			System.out.println(finger-1);
			return;
		}
		long answer=0;
		if(finger==1) answer = cnt*8;
		else if(finger==5) answer = cnt*8+4;
		else {
			if(cnt%2==0) answer = cnt*4+finger-1;
			else answer = cnt*4-(finger-1)+4;
		}
		System.out.println(answer);
	} 
	/**
	 *  1 2 3 4			cnt가 홀수면 -> 방향, 짝수면 <- 방향
	 *    2 3 4 5
	 *  1 2 3 4
	 *    2 3 4 5
	 */
	
}
