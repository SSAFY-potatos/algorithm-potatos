import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int c = 1; c <= testCase; c++) {
            int numSize = Integer.parseInt(br.readLine());
            int[] T = new int[100];
            int n = 1;
            int t = 0;
            int answer = 0;
            while (t < numSize) {
                t = n * (n + 1) / 2;
                T[n - 1] = t;
                n++;
            }

            Loop1: for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    for (int k = 0; k < n - 1; k++) {
                        if ((T[i] + T[j] + T[k] == numSize)) {
                            answer = 1;
                            // System.out.println(T[i]+ " "+ T[j] + " " + T[k] + " ");
                            break Loop1;
                        }
                    }
                }
            }
            System.out.println(answer);
        }
    }

}