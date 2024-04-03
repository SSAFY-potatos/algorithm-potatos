import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[N - 1] + 1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int cnt = 1;
            int prev = arr[0];

            for (int i = 1; i < N; i++) {
                if (arr[i] - prev >= mid) {
                    cnt++;
                    prev = arr[i];
                }
            }

            if (cnt < C) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(left);
    }
}
