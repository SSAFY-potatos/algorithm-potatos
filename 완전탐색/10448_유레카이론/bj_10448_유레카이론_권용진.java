import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // arr[45] = 990
        int[] arr = new int[45];
        for (int i = 1; i < 45; i++) {
            arr[i] = i * (i + 1) / 2;
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            boolean flag = false;
            outerLoop:
            for (int i = 1; i < 45; i++) {
                for (int j = i; j < 45; j++) {
                    for (int l = i; l < 45; l++) {
                        if(arr[i] + arr[j] + arr[l] == K) {
                            flag = true;
                            break outerLoop;
                        }
                    }
                }
            }

            if (flag) System.out.println(1);
            else System.out.println(0);
        }
    }
}