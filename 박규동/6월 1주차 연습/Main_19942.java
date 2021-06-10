import java.util.*;
import java.io.*;

public class Main_19942 {

    private static int N,ans_cost,ans_set;
    private static boolean check;
    private static List<Info> list;
    private static Info target;
    private static class Info {
        int mp,mf,ms,mv;
        int cost;
        public Info(int mp,int mf,int ms,int mv) {
            this.mp=mp; this.mf=mf; this.ms=ms; this.mv=mv;
        }
        public Info(int mp,int mf,int ms,int mv,int cost) {
            this.mp=mp; this.mf=mf; this.ms=ms; this.mv=mv; this.cost=cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        target = new Info(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Info(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }
        ans_cost = Integer.MAX_VALUE;
        recur(0,0,0,0,0,0,0);
        printAns();

    }

    private static void printAns() {
        if(ans_set==0) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans_cost).append("\n");
        for(int i=0;i<16;i++) {
            if((ans_set & 1 << i) == 0) continue;
            sb.append(i+1).append(" ");
        }
        System.out.println(sb);
    }

    private static void recur(int tp, int tf, int ts, int tv, int flag, int cost, int idx) {
        if(cost>ans_cost) return;
        if(tp>=target.mp && tf>=target.mf && ts>=target.ms && tv>=target.mv && cost<ans_cost) {
            ans_cost = cost;
            ans_set = flag;
            return;
        }

        for(int i=idx;i<N;i++) {
            if((flag & 1 << i) != 0) continue;
            Info nut = list.get(i);
            recur(tp+nut.mp,tf+nut.mf,ts+nut.ms,tv+nut.mv, flag | 1<<i, cost+nut.cost,i);
        }

    }
}
