import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5525 {

	static StringBuilder sb;
	static int[] failarr;
	static String pn,S;
	static int M,N,answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		sb.append("IOI");
		for(int i=0;i<N;i++ ) {
			sb.append("OI");
		}
		pn = new String(sb.toString());
		failarr = new int[pn.length()];
		
		M=Integer.parseInt(br.readLine());
		S=br.readLine();
		
		int len=0;// 패턴 갯수
		for(int i=0;i<S.length()-2;i++) {
			if(S.charAt(i)=='I'&&S.charAt(i+1)=='O'&&S.charAt(i+2)=='I') {
				len++;
				if(len==N) {
					len--;    // pn의 앞에서 패턴 한 개 제거
					answer++;
				}
				i++; // 패턴길이만큼 건너뛰어야함
			}
			else len = 0; //패턴이 깨지면 갯수 초기화
		}
		
		System.out.println(answer);
		
	}


}
