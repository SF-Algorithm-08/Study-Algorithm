import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14715 {

	static int K, cnt, answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		boolean flag = false;
		while (true) {
			flag=false;
			for (int i = 2; i <= K; i++) {
				if (K % i == 0) {
					cnt++;
					K /= i;
					flag = true;
					break;
				}
			}
			if (flag == false)
				break;
		}
		while(true) {
			int tmp=1;
			tmp <<= answer;
			if(tmp>=cnt) break;
			answer++;
		}
		System.out.println(answer);

	}

}
