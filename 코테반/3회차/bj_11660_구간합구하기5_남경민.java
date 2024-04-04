
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_11660_구간합구하기5_남경민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr= new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(i==0 && j==0) {
                    arr[i][j] = num;
                }
                else if(i==0 && j!=0) {
                    arr[i][j] = arr[i][j-1] + num;
                }else if(i!=0 && j==0) {
                    arr[i][j] = arr[i-1][j] + num;
                }else {
                    arr[i][j] = (arr[i-1][j] + arr[i][j-1] + num) -arr[i-1][j-1];
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = arr[x2-1][y2-1];
            if(y1!=1) {
                sum-= arr[x2-1][y1-2];
            }
            if(x1!=1) {
                sum-= arr[x1-2][y2-1];
            }
            if(x1!=1 && y1!=1) {
                sum+= arr[x1-2][y1-2];
            }

            System.out.println(sum);
        }
    }
}