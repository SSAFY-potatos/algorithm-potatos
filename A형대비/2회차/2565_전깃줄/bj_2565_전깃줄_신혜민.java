import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<wire> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.add(new wire(a,b));
        }

        list.sort(null);
        int[] dp = new int[N];

        dp[0] = 1;

        for(int i = 1; i < N; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(list.get(j).b < list.get(i).b){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        Arrays.sort(dp);
        System.out.println(N-dp[N-1]);
    }
}

class wire implements Comparable<wire>{
    int a, b;

    wire(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(wire o) {
        return this.a - o.a;
    }
}