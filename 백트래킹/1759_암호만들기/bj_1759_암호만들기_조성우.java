import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1759_암호만들기_조성우 {

    static int L, C;
    static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        chars = new char[C];
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars);
        // System.out.println(Arrays.toString(chars));
        dfs(0, new StringBuffer());
    }

    static void dfs(int dep, StringBuffer sb) {
        // System.out.println("sb===" + sb + dep);
        if (sb.length() == L) {
            int za = 0, mo = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (isMoum(sb.charAt(i))) {
                    mo++;
                } else {
                    za++;
                }
            }
            if (mo >= 1 && za >= 2) {
                System.out.println(sb.toString());
            }
            return;
        }
        if (dep == C) {
            return;
        }

        sb.append(chars[dep]);
        dfs(dep + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        dfs(dep + 1, sb);

    }

    static boolean isMoum(char c) {
        return "aeiou".indexOf(c) >= 0 ? true : false;
    }
}