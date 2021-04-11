import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main_1377 {

	static int N,answer;
	static Pair[] arr;
	
	static class Pair {
		Integer index;
		Integer value;
		public Pair(Integer index,Integer value) {
			this.index=index;
			this.value=value;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr = new Pair[N];
		
		for(int i=0;i<N;i++) {
			Integer a = Integer.parseInt(br.readLine());
			arr[i] = new Pair(i,a);
		}
		Arrays.sort(arr,new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.value-o2.value;
			}
		});
		for(int i=0;i<N;i++) {
			answer = Math.max(answer,arr[i].index-i);
		}
		System.out.println(answer+1);
	}

}
