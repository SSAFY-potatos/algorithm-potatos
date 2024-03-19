import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 일곱난쟁이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];
        int sum = 0;

        for(int i = 0; i < 9; i++){
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        Arrays.sort(arr);

        int check1 = 0;
        int check2 = 0;

        for(int i = 0; i < 9; i++){
            for(int j = i+1; j < 9; j++){
                if(sum - arr[i] - arr[j] == 100){
                    check1 = arr[i];
                    check2 = arr[j];
                    break;
                }
            }
        }

        for(int i = 0; i < 9; i++){
            if(arr[i] == check1 || arr[i] == check2){
                continue;
            }

            System.out.println(arr[i]);
        }
    }
}
