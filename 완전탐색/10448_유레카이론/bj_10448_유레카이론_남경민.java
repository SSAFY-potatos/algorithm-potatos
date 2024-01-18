import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] triNum = new int[45];
        for(int i=1;i<45;i++) {
            triNum[i] = i*(i+1)/2;
        }
        for(int i=0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            int result = eureka(num,triNum);
            System.out.println(result);
        }
    }

    public static int eureka(int N,int[] triNum) {
        for(int i=1;i<45;i++) {
            for(int j=1;j<45;j++) {
                for(int k=1;k<45;k++) {
                    int sum = triNum[i]+triNum[j]+triNum[k];
                    if(sum == N) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
