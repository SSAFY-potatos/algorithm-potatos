import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_12852_1로만들기2_조성우 {
    static DpItem[] dp;
    static int MAX = 1000001;

    static class DpItem {
        int cumCnt;
        String history;

        DpItem() {
            cumCnt = Integer.MAX_VALUE;
            history = "";
        }

        public DpItem(int cumCnt, String history) {
            this.cumCnt = cumCnt;
            this.history = history;
        }

        @Override
        public String toString() {
            return "DpItem{" +
                    "cumCnt=" + cumCnt +
                    ", history='" + history + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        dp = new DpItem[MAX];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new DpItem(Integer.MAX_VALUE, "");
        }

        dp[1].cumCnt = 0;
        dp[1].history = "1";
        for (int i = 1; i < X; i++) {
            if (i + 1 < MAX) {
                if (dp[i + 1].cumCnt > dp[i].cumCnt + 1) {
                    dp[i + 1].cumCnt = dp[i].cumCnt + 1;
                    dp[i + 1].history = dp[i].history + " " + (i + 1);
                }
            }
            if (i * 2 < MAX) {
                if (dp[i * 2].cumCnt > dp[i].cumCnt + 1) {
                    dp[i * 2].cumCnt = dp[i].cumCnt + 1;
                    dp[i * 2].history = dp[i].history + " " + i * 2;
                }
            }
            if (i * 3 < MAX) {
                if (dp[i * 3].cumCnt > dp[i].cumCnt + 1) {
                    dp[i * 3].cumCnt = dp[i].cumCnt + 1;
                    dp[i * 3].history = dp[i].history + " " + i * 3;
                }
            }

        }
        System.out.println(dp[X].cumCnt);
        StringTokenizer st = new StringTokenizer(dp[X].history);
        List<String> str = new ArrayList<>();

        while (st.hasMoreTokens()) {
            str.add(st.nextToken());
        }
        Collections.reverse(str);

        String res = String.join(" ", str);
        System.out.println(res);
    }
}
