import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            int sum = 0;

            if(L == 0){
                break;
            }

            sum += V / P * L;

            V %= P;
            if(V < L){
                sum += V;
            }
            else{
                sum += L;
            }

            System.out.println("Case " + idx + ": " + sum);
            idx++;
        }
    }
}
