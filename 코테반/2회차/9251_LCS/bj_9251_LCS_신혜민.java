import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {
    static String input1, input2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input1 = br.readLine();
        input2 = br.readLine();

        int size1 = input1.length();
        int size2 = input2.length();

        dp = new int[size1+1][size2+1];

        for(int i = 1; i <= size1; i++) {
            for(int j = 1; j <= size2; j++) {
                if(input1.charAt(i-1) == input2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[size1][size2]);
    }
}