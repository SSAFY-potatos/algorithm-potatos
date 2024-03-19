import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] table;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        table = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            table[i][0] = S;
            table[i][1] = T;
        }

        Arrays.sort(table, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                }

                return Integer.compare(o1[1], o2[1]);
            }
        });

        int answer = 0;
        int end = 0;
        for (int[] lecture : table) {
            if (lecture[0] >= end) {
                answer++;
                end = lecture[1];
            }
        }

        System.out.println(answer);
    }
}