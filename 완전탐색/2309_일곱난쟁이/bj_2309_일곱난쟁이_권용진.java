import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];
        int total = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        // logic
        outerLoop:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) continue;
                if (total - (arr[i] + arr[j]) == 100) {
                    arr[i] = 0;
                    arr[j] = 0;
                    break outerLoop;
                }
            }
        }

        Arrays.sort(arr);
        for (int i = 2; i < 9; i++) {
            System.out.println(arr[i]);
        }
    }
}