import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static char[][] table;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        table = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                table[i][j] = s.charAt(j);
            }
        }

        // logic
        int answer = 64;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                answer = Math.min(answer, check(i, j));
            }
        }

        System.out.println(answer);
    }

    static int check(int y, int x) {
        int count = 0;
        for (int i = y; i < y + 8; i++) {
            for (int j = x; j < x + 8; j++) {

                if ((i + j) % 2 == 0 && table[i][j] == 'W') {
                    count++;
                } else if ((i + j) % 2 == 1 && table[i][j] == 'B') {
                    count++;
                }
            }
        }

        return Math.min(count, 64 - count);
    }
}