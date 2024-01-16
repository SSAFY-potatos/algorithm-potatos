import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2231_분해합_조성우 {

    public static int calcMinConstructor(int TARGET) {
        for (int curVal = 0; curVal < TARGET; curVal++) {
            int tempDiv = curVal;
            int curSum = curVal;

            while (tempDiv != 0) {
                curSum += tempDiv % 10;
                tempDiv /= 10;
            }

            if (curSum == TARGET) {
                return curVal;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int res = calcMinConstructor(N);
        System.out.println(res);
    }

}