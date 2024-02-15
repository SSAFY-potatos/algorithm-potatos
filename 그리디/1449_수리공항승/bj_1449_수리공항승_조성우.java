import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1449_수리공항승_조성우 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] holes = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            holes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(holes);

        int cnt = 0;
        int idx = 0;
        while (true) {
            if (idx == holes.length) {
                break;
            }

            int cur_hole = holes[idx];
            int cur_maxCover = cur_hole + (L - 1);

            while (true) {
                idx++;
                if (idx == holes.length) {
                    System.out.println(++cnt);
                    return;
                }
                if (holes[idx] > cur_maxCover) {
                    break;
                }
            }
            cnt++;
        }
    }
}
