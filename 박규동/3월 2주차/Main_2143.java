import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2143 {

	static int N,M;
	static int T;
	static long answer;
	static int[] a,b;
//	static ArrayList<Integer> aSum,bSum;
	static int[] aSum,bSum;
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		N=Integer.parseInt(br.readLine());
		a=new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			a[i]=Integer.parseInt(st.nextToken());
		}
		M=Integer.parseInt(br.readLine());
		b=new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			b[i]=Integer.parseInt(st.nextToken());
		}
		
		solution();
		System.out.println(answer);
	}
	
	private static void solution() {
//		aSum = new ArrayList<>();
//		bSum = new ArrayList<>();
		aSum = new int[N*(N+1)/2];
		bSum = new int[M*(M+1)/2];
		int idx=0;
		for(int i=0;i<N;i++) {
			int sum=a[i];
//			aSum.add(sum);
			aSum[idx++]=sum;
			for(int j=i+1;j<N;j++) {
				sum+=a[j];
//				aSum.add(sum);
				aSum[idx++]=sum;
			}
		}
		
		idx=0;
		for(int i=0;i<M;i++) {
			int sum=b[i];
//			bSum.add(sum);
			bSum[idx++]=sum;
			for(int j=i+1;j<M;j++) {
				sum+=b[j];
//				bSum.add(sum);
				bSum[idx++]=sum;
			}
		}
		Arrays.sort(aSum);
//		Collections.sort(aSum);
		int remain;
		for(int i=0;i<bSum.length;i++) {
			remain = T-bSum[i];
			count(remain);
		}
	}
	
	private static void count(int remain) {
	    int sidx,eidx;
		int start = 0;
//	    int end = aSum.size();
		int end = aSum.length;
	    
	    while(start < end) {
	    	int mid = (start + end) / 2;
	        if(aSum[mid] >= remain) {	//aSum.get(mid)
	        	end = mid;
	        }
	        else {
	        	start = mid + 1;
	        }
	    }
	    sidx=end;
	    
		start = 0;
	    end = aSum.length;
	    
	    while(start < end) {
	    	int mid = (start + end) / 2;
	        if(aSum[mid] <= remain) {
	        	start = mid + 1;
	        }
	        else {
	        	end = mid;
	        }
	    }
	    eidx=end;
	    answer+=eidx-sidx;
	}

}
