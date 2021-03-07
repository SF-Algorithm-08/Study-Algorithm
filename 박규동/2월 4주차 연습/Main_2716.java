import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2716 {

	static int N,answer;
	static Stack<Character> stack;
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			answer=0;
			str = new String(br.readLine());
			stack = new Stack<>();
			solution();
			System.out.println(answer);
		}
	}
	private static void solution() {
		int depth=0;
		int max=0;
		if(str.length()==0) {
			answer=1;
			return;
		}
		for(int i=0;i<str.length();i++) {
			char tmp = str.charAt(i);
			if(tmp=='[') {
				stack.add(tmp);
				depth++;
				max = Math.max(depth, max);
			}
			else {
				stack.pop();
				depth--;
			}
		}
		int result=1;
		int count=1;
		while(true) {
			if(count>max) break;
			result*=2;
			count++;
		}
		
		answer = result;
	}

}
