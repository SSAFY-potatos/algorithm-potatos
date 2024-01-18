import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] table;
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        table = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < N; j++) {
                table[i][j] = s.charAt(j);
            }
        }

        // logic
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char temp = table[i][j];

                if (i < N - 1) {
                    table[i][j] = table[i + 1][j];
                    table[i + 1][j] = temp;
                    answer = Math.max(checkRow(i, j), answer);
                    answer = Math.max(checkCol(i, j), answer);
                    answer = Math.max(checkRow(i + 1, j), answer);
                    answer = Math.max(checkCol(i + 1, j), answer);

                    table[i + 1][j] = table[i][j];
                    table[i][j] = temp;
                }

                if (j < N - 1) {
                    table[i][j] = table[i][j + 1];
                    table[i][j + 1] = temp;
                    answer = Math.max(checkRow(i, j), answer);
                    answer = Math.max(checkCol(i, j), answer);
                    answer = Math.max(checkRow(i, j + 1), answer);
                    answer = Math.max(checkCol(i, j + 1), answer);

                    table[i][j + 1] = table[i][j];
                    table[i][j] = temp;
                }
            }
        }

        System.out.println(answer);
    }

    static int checkRow(int y, int x) {
        char color = table[y][x];
        int count = 1;
        // right
        for (int i = x + 1; i < N; i++) {
            if (table[y][i] == color) count++;
            else break;
        }

        // left
        for (int i = x - 1; i >= 0; i--) {
            if (table[y][i] == color) count++;
            else break;
        }

        return count;
    }

    static int checkCol(int y, int x) {
        char color = table[y][x];
        int count = 1;
        // down
        for (int i = y + 1; i < N; i++) {
            if (table[i][x] == color) count++;
            else break;
        }

        // up
        for (int i = y - 1; i >= 0; i--) {
            if (table[i][x] == color) count++;
            else break;
        }

        return count;
    }
}