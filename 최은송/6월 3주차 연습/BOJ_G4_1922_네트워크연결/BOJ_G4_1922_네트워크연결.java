package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1922_네트워크연결 {
    static int N, M;
    static int[] parents;

    static class Edge implements Comparable<Edge>{
        public int from, to, cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int find(int a){
        if(a == parents[a]) return a;
        return find(parents[a]);
    }
    static boolean union(int a, int b){
        int roota = find(a);
        int rootb = find(b);
        if(roota != rootb) {
            parents[rootb] = roota;
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        parents = new int[N+1];
        for(int i=0; i<=N; i++)
            parents[i] = i;

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(from, to, cost));
        }
        int ans = 0;
        int cnt = 0;
        while(!pq.isEmpty() && cnt < N){
            Edge cur = pq.poll();
            if(!union(cur.from, cur.to)){
                cnt++;
                ans += cur.cost;
            }
        }
        System.out.println(ans);
    }
}
