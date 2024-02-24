import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_12904_A와B_조성우 {
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        String curStr = T;
        while (true) {
            if (curStr.length() == S.length()) {
                if (curStr.equals(S)) {
                    res = 1;
                    break;
                }
                res = 0;
                break;
            }

            char last_char = curStr.charAt(curStr.length() - 1);
            curStr = curStr.substring(0, curStr.length() - 1);

            if (last_char == 'B') {
                StringBuffer sb = new StringBuffer(curStr);
                curStr = sb.reverse().toString();
            }
        }
        System.out.print(res);
    }
}