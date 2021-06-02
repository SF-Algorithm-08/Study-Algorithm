package com.company;
import java.util.*;
import java.io.*;
public class Main {

    private static int[] bit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        bit = new int[20];

        for(int i=-0;i<N;i++) {
            int target = Integer.parseInt(br.readLine());
            countBit(target);
        }

        System.out.println(cal(N));

    }

    private static long cal(int N) {
        long answer=0;

        for(int i=0;i<20;i++) {
            long val = (long)bit[i]*(long)(N-bit[i])*(long)(1<<i);
            //i번째 자리에서 bit[i]개중 1개, 나머지 중 1개를 xor했을 때 1이므로 고르는 경우의 수
            answer+=val;
        }
         return answer;
    }

    private static void countBit(int target) {

        for(int i=0;i<20;i++) {
            if((target & 1 << i)!=0) bit[i]++;
        }

    }
}
