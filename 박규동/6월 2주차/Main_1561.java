import java.util.*;
import java.io.*;

public class Main_1561 {

    private static long N;
    private static int M;
    private static int[] rides;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Long.parseLong(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        rides = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) rides[i] = Integer.parseInt(st.nextToken());
        if(M>=N) {
            System.out.println(N);
            return;
        }

        long jointime = bst(); //
        int answer = count(jointime);
        System.out.println(answer);
    }


    private static int count(long jointime) {
        long sum=M;
        long[] before = new long[M];
        long[] after = new long[M];
        for(int i=0;i<M;i++) {
            sum+=(jointime-1)/rides[i];
            before[i] = (jointime-1)/rides[i];
            after[i] = jointime/rides[i];
        }
        //sum:N이 타기 직전 시간까지 탄 사람들

        for(int i=0;i<M;i++) {
            if(before[i]==after[i]) continue;
            sum++;
            if(sum==N) return i+1;
        }

        return 0;
    }


    private static long bst() {
        long left = 0;
        long right = N*30L;
        long mid=0;
        long sum=0;
        long time=0;
        while(left<=right) {
            mid = (left+right)/2;
            sum=M;
            for(int i=0;i<M;i++) {
                sum+=mid/rides[i];
            }

            if(sum>=N) {
                time = mid;
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        return time;
    }

}
