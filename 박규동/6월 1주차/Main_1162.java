import java.io.*;
import java.util.*;

public class Main_1162 {

    private static int N,M,K;
    private static ArrayList<Info>[] info;

    private static class Info {
        int dest;
        long cost;
        int pack;

        public Info(int dest,long cost,int pack) {
            this.dest = dest;
            this.cost = cost;
            this.pack = pack;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        info = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) info[i] = new ArrayList<>();

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int dest=Integer.parseInt(st.nextToken());
            long cost=Long.parseLong(st.nextToken());

            info[start].add(new Info(dest,cost,0));
            info[dest].add(new Info(start,cost,0));
        }
        long answer = find(1,N);
        System.out.println(answer);
    }

    private static long find(int start, int end) {
        if(K>=M) return 0;

        long[][] dist = new long[N+1][K+1];
        for(int i=1;i<N+1;i++) Arrays.fill(dist[i],Long.MAX_VALUE);
        for(int i=0;i<K+1;i++) dist[start][i]=0;

        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if(o1.cost>o2.cost) return 1;
                else if(o1.cost==o2.cost) return 0;
                return -1;
            }
        });

        pq.add(new Info(start,0,0));

        while(!pq.isEmpty()) {
            int cdest = pq.peek().dest;
            long ccost = pq.peek().cost;
            int cpack = pq.peek().pack;
            pq.poll();

            if(ccost>dist[cdest][cpack]) continue;

            for(int i=0;i<info[cdest].size();i++) {
                int ndest = info[cdest].get(i).dest;
                long ncost = info[cdest].get(i).cost;

                if(ncost+ccost<dist[ndest][cpack]) {
                    dist[ndest][cpack] = ncost+ccost;
                    pq.add(new Info(ndest,ncost+ccost,cpack));
                }

                if(cpack<K && ccost<dist[ndest][cpack+1]) {
                    dist[ndest][cpack+1] = ccost;
                    pq.add(new Info(ndest,ccost,cpack+1));
                }
            }
        }

        return dist[N][K];
    }

}
