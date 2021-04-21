import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17609 {
	
	static int T;
	static String target;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<T;i++) {
			target = br.readLine();
			if(isPalindrome(0,target.length()-1)) sb.append("0\n");
			else if(isPseudoPalindrome()) sb.append("1\n");
			else sb.append("2\n");
		}
		System.out.println(sb);
	}

	private static boolean isPseudoPalindrome() {
		
		int start=0;
		int end=target.length()-1;
		
		while(start<=end) {
			if(target.charAt(start)!=target.charAt(end)) break;
			start++;
			end--;
		}
		
		int nstart = start+1;
		int nend = end-1;
		boolean flag1=true;
		boolean flag2=true;
		
		flag1 = isPalindrome(start,nend);
		flag2 = isPalindrome(nstart, end);
		
		return flag1|flag2;
	}

	private static boolean isPalindrome(int start,int end) {
		while(start<=end) {
			if(target.charAt(start)!=target.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}
	
	

}
