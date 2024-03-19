import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] chess = new char[N][M];
        char[][] chessW = new char[8][8];
        char[][] chessB = new char[8][8];

        char[] W = new char[M];
        char[] B = new char[M];

        int minCntW = 50*50;
        int minCntB = 50*50;

        W[0] = 'W';
        B[0] = 'B';

        for (int i = 0; i < 7; i++) {
            if (W[i] == 'W' ) {
                W[i+1] = 'B';
            }else if(W[i] =='B'){
                W[i+1] = 'W';
            }
            if (B[i] == 'B') {
                B[i+1] = 'W';
            }else{
                B[i+1] = 'B';
            }
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if(i%2==0 && i<8 && j<8){
                    chessW[i][j] = W[j];
                    chessB[i][j] = B[j];
                }else if(i<8 && j<8 && i%2!=0){
                    chessW[i][j] = B[j];
                    chessB[i][j] = W[j];
                }
                chess[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(i+8 <=N && j+8 <=M ){
                    int cntW = 0;
                    int cntB = 0;
                    for (int k = 0; k < 8; k++) {
                        for (int q = 0; q < 8; q++) {
                            if (chessW[k][q] != chess[k+i][q+j]) {
                                cntW ++;
                            }
                            if (chessB[k][q] != chess[k+i][q+j]) {
                                cntB ++;
                            }
                        }
                    }
                    minCntW = Math.min(minCntW,cntW);
                    minCntB = Math.min(minCntB,cntB);
                }
            }
        }


        System.out.println(Math.min(minCntB,minCntW));
    }
}
