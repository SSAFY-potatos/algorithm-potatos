import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = 0;
        while (true) {
            t++;
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (L == 0 && P == 0 && V ==0) break;

            int answer = 0;
            while (V > 0) {
                answer += Math.min(L, V);
                V -= P;
            }

            sb.append("Case ").append(t)
                    .append(": ").append(answer)
                    .append("\n");

        }

        System.out.println(sb);
    }
}