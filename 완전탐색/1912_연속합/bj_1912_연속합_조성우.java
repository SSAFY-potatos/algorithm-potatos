import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1912_연속합_조성우 {
    // 아이디어: N과 동일 크기 배열을 만들어 순서대로 누적합을 기록, 음수 전환 시점 새로 시작, 최대값 출력
    // 개선 예정: 배열 안만들고 매 시점 최대값만 비교 기록
    public static void main(String[] args) throws IOException {
        int max = Integer.MIN_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arrCumSum = new int[n];

        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            arrCumSum[i] = cur;
            if (i == 0 || arrCumSum[i - 1] < 0) {
                continue;
            }
            arrCumSum[i] += arrCumSum[i - 1];
        }
        for (int item : arrCumSum) {
            max = Math.max(max, item);
        }
        System.out.println(max);
    }
}
