import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int findMax(int N, char[][] inputArr) {
        int row_cnt = 1;
        int col_cnt = 1;
        int row_max = 0;
        int col_max = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                char row_curr = inputArr[i][j];
                char row_next = inputArr[i][j+1];

                char col_curr = inputArr[j][i];
                char col_next = inputArr[j+1][i];

                if (row_curr == row_next)
                    row_cnt++;
                else
                    row_cnt =1;

                if (col_curr == col_next)
                    col_cnt++;
                else
                    col_cnt =1;

                if(row_max < row_cnt)
                    row_max = row_cnt;
                if(col_max < col_cnt)
                    col_max = col_cnt;
            }
            row_cnt = 1;
            col_cnt = 1;
        }

        return row_max > col_max ? row_max : col_max;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] inputArr = new char[N][N];

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                inputArr[i][j] = line.charAt(j);
            }
        }

//		System.out.println(Arrays.deepToString(inputArr));

        int answer = 0;
        int methodAnswer = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(j+1 < N) {
                    char temp = inputArr[i][j+1];
                    inputArr[i][j+1] = inputArr[i][j];
                    inputArr[i][j] = temp;

                    methodAnswer = findMax(N, inputArr);
                    if(answer <methodAnswer)
                        answer = methodAnswer;

                    temp = inputArr[i][j+1];
                    inputArr[i][j+1] = inputArr[i][j];
                    inputArr[i][j] = temp;
                }

                if(i+1 < N) {
                    char temp = inputArr[i+1][j];
                    inputArr[i+1][j] = inputArr[i][j];
                    inputArr[i][j] = temp;

                    methodAnswer = findMax(N, inputArr);
                    if(answer <methodAnswer)
                        answer = methodAnswer;

                    temp = inputArr[i+1][j];
                    inputArr[i+1][j] = inputArr[i][j];
                    inputArr[i][j] = temp;
                }
            }
        }

        System.out.println(answer);


    }

}
