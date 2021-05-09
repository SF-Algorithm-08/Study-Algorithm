import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1344 {

	static double a,b;
	static int[] comb;
	static double[] answer;
	static int[] prior = {2,3,5,7,11,13,17};
	static final int time = 90/5;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		a=Double.parseDouble(br.readLine());
		b=Double.parseDouble(br.readLine());
		comb = new int[7];
		
		for(int i=0;i<7;i++) {
			comb[i] = (int)cal_comb(prior[i]);
		}
		answer = new double[7];
		double pa=0;
		double pb=0;
		a/=100.0;
		b/=100.0;
		
		//a와 b가 소수만큼 골을 넣을 확률
		for(int i=0;i<7;i++) {
			pa+=comb[i]*Math.pow(a,prior[i])*Math.pow(1-a,18-prior[i]);
			pb+=comb[i]*Math.pow(b,prior[i])*Math.pow(1-b,18-prior[i]);
		}
		
		System.out.printf("%.7f",pa+pb-pa*pb);
		
	}

	private static long cal_comb(int idx) {
		long value = 1;
		for(int i=time;i>time-idx;i--) {
			value *= i;
		}
		
		for(int i=1;i<=idx;i++) {
			value /= i;
		}
		return value;
	}

}
