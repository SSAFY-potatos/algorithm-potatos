import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, sum, cnt;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < (1 << N); i++) {
            sum = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            if (sum == S) {
                cnt++;
            }
        }
        if (S == 0) {
            System.out.println(cnt - 1);
        } else {
            System.out.println(cnt);
        }
    }
}