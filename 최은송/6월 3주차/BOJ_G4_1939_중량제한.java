package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1939_중량제한 {
    static int N, M, start, end, ans = 0;
    static LinkedList<Edge> adj[];

    static class Edge implements Comparable<Edge>{
        public int to, cost;
        public Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.cost, this.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new LinkedList[N+1];
        for(int i=1; i<=N; i++)
            adj[i] = new LinkedList<Edge>();

        int from, to, cost;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(in.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }
        st = new StringTokenizer(in.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        prim();
        System.out.println(ans);
    }

    static void prim(){
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        boolean[] visited = new boolean[N+1];

        pq.offer(new Edge(start, Integer.MAX_VALUE));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();

            if(visited[cur.to]) continue;
            visited[cur.to] = true;

            for(Edge edge : adj[cur.to]){
                if(edge.cost > cur.cost){
                    edge.cost = cur.cost;
                }
                if(edge.to == end){
                    if(edge.cost > ans) ans = edge.cost;
                }
                if(!visited[edge.to]) {
                    pq.offer(new Edge(edge.to, edge.cost));
                }
            }
        }
    }
}
