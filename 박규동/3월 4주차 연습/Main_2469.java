import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_2469 {

	static int k,n,cut;
	static ArrayList<String> before;
	static ArrayList<String> after;
	static String result;
	static int[] arr1,arr2;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		k=Integer.parseInt(br.readLine());
		n=Integer.parseInt(br.readLine());
		result = br.readLine();
		arr2=new int[k];
		for(int i=0;i<k;i++) {
			arr2[i]=result.charAt(i)-'A';
		}
		
		arr1 = new int[k];
		for(int i=0;i<k;i++) {
			arr1[i]=i;
		}
		
		before = new ArrayList<>();
		after = new ArrayList<>();
		boolean flag=false;
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			if(str.charAt(0)=='?') {
				flag=true;
				cut=i; //0~i-1  +  i+1~n-1
			}
			
			if(flag==false) {
				for(int j=0;j<k-1;j++) {
					if(str.charAt(j)=='-') {
						int tmp = arr1[j];
						arr1[j]=arr1[j+1];
						arr1[j+1]=tmp;
					}
				}
			}
			else {
				after.add(str);
			}
		}
		
		for(int i=after.size()-1;i>=0;i--) {
			for(int j=0;j<k-1;j++) {
				if(after.get(i).charAt(j)=='-') {
					int tmp = arr2[j];
					arr2[j]=arr2[j+1];
					arr2[j+1]=tmp;
				}
			}
		}
		
//		for(int i=0;i<k;i++) {
//			System.out.print(arr1[i]);
//		}
//		System.out.println();
//		for(int i=0;i<k;i++) {
//			System.out.print(arr2[i]);
//		}
		
		if(check()) {
			System.out.println(sb.toString());
		}
		else {
			for(int i=0;i<k-1;i++) {
				System.out.print("x");
			}
		}
		
	}

	private static boolean check() {
		for(int i=0;i<k-1;i++) {
			if(arr1[i]==arr2[i]) {
				sb.append("*");
			}
			else if((arr1[i]==arr2[i+1])&&(arr1[i+1]==arr2[i])) {
				sb.append("-");
				int tmp = arr1[i];
				arr1[i]=arr1[i+1];
				arr1[i+1]=tmp;
			}
			else return false;
		}
		
		return true;
	}

}
