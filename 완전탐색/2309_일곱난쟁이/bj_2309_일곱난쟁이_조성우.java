import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_2309_일곱난쟁이_조성우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tallArr = new int[9];
        int arrSum = 0;

        for (int i = 0; i < 9; i++) {
            tallArr[i] = Integer.parseInt(br.readLine());
            arrSum += tallArr[i];
        }

        Arrays.sort(tallArr);
        for (int i = 0; i < 9; i++) {
            int tempRes = arrSum;
            for (int j = i + 1; j < 9; j++) {
                if (i == j)
                    continue;

                if (tempRes - (tallArr[i] + tallArr[j]) == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j)
                            continue;
                        System.out.println(tallArr[k]);
                    }
                    return;
                }
            }
        }
    }
}