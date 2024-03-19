import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class 유레카이론 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int[] arr = new int[45];

        arr[0] = 1;

        for(int i = 1; i < 45; i++){
            arr[i] = i * (i+1) / 2;
        }

        for(int t = 0; t < K; t++){
            int check = 0;
            int input = Integer.parseInt(br.readLine());
            for(int i = 0; i < 45; i++){
                for(int j = 0; j < 45; j++){
                    for(int k = 0; k < 45; k++){
                        int sum = arr[i] + arr[j] + arr[k];
                      
                        if(sum == input){
                            check = 1;
                            break;
                        }
                    }
                }
            }
            System.out.println(check);
        }
    }
}
