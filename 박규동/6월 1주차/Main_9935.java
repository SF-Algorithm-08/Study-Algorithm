import java.io.*;

public class Main_9935 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();

        int slen = str.length();
        int tlen = target.length();

        char key = target.charAt(tlen-1);
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<slen;i++) {
            char cur = str.charAt(i);
            sb.append(cur);

            if(cur==key) {
                if(sb.length()<tlen) continue;
                if(sb.substring(sb.length()-tlen,sb.length()).equals(target)) {
                    sb.delete(sb.length()-tlen,sb.length());
                }
            }
        }
        if(sb.length()==0) System.out.println("FRULA");
        else System.out.println(sb);
    }
}
