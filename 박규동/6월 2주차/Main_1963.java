import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1963 {

    private static HashSet<Integer> prime;
    private static Queue<Prime> q;

    private static class Prime {
        String cur;
        String target;
        int cnt;
        public Prime(String cur, String target,int cnt) {
            this.cur = cur;
            this.target = target;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        prime = new HashSet<>();
        getPrime();
        for(int tc=0;tc<T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cur = st.nextToken();
            String target = st.nextToken();
            q = new LinkedList<>();
            sb.append(search(cur,target)).append("\n");
        }
        System.out.println(sb);

    }

    private static int search(String cur, String target) {

        q.add(new Prime(cur,target,0));
        boolean[] visit = new boolean[10000];
        visit[Integer.parseInt(cur)] = true;
        while(!q.isEmpty()) {
            Prime cPrime = q.poll();
            String val = cPrime.cur;
            if(val.equals(target)) return cPrime.cnt;

            for(int i=0;i<4;i++) {
                for(int j=0;j<10;j++) {
                    int next = change(i,j,val);
                    if(!prime.contains(next)) continue;
                    if(visit[next]) continue;
                    visit[next] = true;

                    q.add(new Prime(Integer.toString(next),target,cPrime.cnt+1));
                }
            }
        }
        return 0;
    }

    //digit : 자리 수 , num : 바꿀 수, val : 현재 비밀번호
    private static int change(int digit, int num, String val) {
        if(digit==0 && num ==0 ) return 0;
        char[] tmp = val.toCharArray();
        tmp[digit] = (char)(num+'0');
        String str = new String(tmp);
        return Integer.parseInt(str);
    }


    private static void getPrime() {
        int[] num = new int[10000];
        Arrays.fill(num,0);
        for(int i=2;i<10000;i++) {
            if(num[i]<0) continue;
            for(int j=2*i;j<10000;j+=i) num[j]=-1;
        }

        for(int i=2;i<10000;i++) {
            if(num[i]==0) prime.add(i);
        }
    }

}
