import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main (String[] args)throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int NLength = (int)(Math.log10(N)+1);
        int result = 0;

        for(int i = (N - (NLength * 9)); i < N; i++) {
            int number = i;
            int sum = 0;

            while(number != 0) {
                sum += number % 10;
                number /= 10;
            }


            if(sum + i == N) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
