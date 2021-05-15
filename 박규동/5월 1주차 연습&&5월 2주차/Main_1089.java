import java.util.*;
import java.io.*;
public class Main_1089 {
	static final String[] digit = {
			"####.##.##.####",
			"..#..#..#..#..#",
			"###..#####..###",
			"###..####..####",
			"#.##.####..#..#",
			"####..###..####",
			"####..####.####",
			"###..#..#..#..#",
			"####.#####.####",
			"####.####..####"
	};
	
	static int N;
	static String[] input;
	static double[] answer;
	static double ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		input = new String[N];
		Arrays.fill(input, "");
		for(int i=0;i<5;i++) {
			String str = br.readLine();
			for(int j=0;j<4*N-2;j+=4) {
				input[j/4]+=str.substring(j,j+3);
			}
		}
		
		answer = new double[N];
		
		for(int i=0;i<N;i++) {
			String target = input[i].toString();
			int cnt=10;
			for(int j=0;j<10;j++) {
				boolean flag = true;
				String dict = digit[j];
				for(int k=0;k<15;k++) {
					if(dict.charAt(k)=='.'&&target.charAt(k)=='#') {
						flag=false;
						break;
					}
				}
				if(flag) {
					answer[i]+=j;
				}
				else cnt--;
			}
			if(cnt==0) {
				System.out.println(-1);
				return;
			}
			answer[i] /= cnt;
		}
		
		int pos = 1;
		for(int i=N-1;i>-1;i--) {
			ans+=answer[i]*pos;
			pos*=10;
		}
		
		System.out.printf("%f",ans);
		
	}
}
