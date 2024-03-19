import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 분해합 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result = 0, sum, check;

        for(int i = 1; i < N-1; i++){
            sum = i;
            check = i;

            while(check != 0){
                sum += check % 10;

                check /= 10;

            }

            if(sum == N){
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
