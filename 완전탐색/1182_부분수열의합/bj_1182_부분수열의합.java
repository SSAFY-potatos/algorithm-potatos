import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] inputArray = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            inputArray[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        int arraySum = 0;
        for(int i = 0; i < N; i++){
            for (int j = i; j < N; j++){
//                System.out.println(i + " " + j);
                arraySum += inputArray[j];
//                System.out.println(arraySum);
                if(j != i) {
                    if (arraySum == S) {
                        answer++;
                    }
                }
            }
            arraySum = 0;
        }
        System.out.println(answer);
    }
}