import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_4796_캠핑_조성우 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testN = 0;
        while (true) {
            testN++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            if (L == 0) {
                return;
            }
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            int dayCanRent = V / P * L;
            dayCanRent += (V % P < L) ? V % P : L;
            System.out.println("Case " + testN + ": " + dayCanRent);
        }
    }

}
