import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,S;
    static int[] arr;
    static boolean[] isSelected;
    static int ans=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        subSequenceSum(0,0);
        System.out.println(ans);
    }
    private static void subSequenceSum(int cnt, int sum){
        if(cnt == N){
            boolean check = false;
            for(boolean b : isSelected){
                if(b) check = true;
            }
            if(!check) return;
            if(sum == S){
                ans++;
            }
            return;
        }

        isSelected[cnt] = true;
        subSequenceSum(cnt+1,sum+arr[cnt]);
        isSelected[cnt] = false;
        subSequenceSum(cnt+1,sum);
    }
}
