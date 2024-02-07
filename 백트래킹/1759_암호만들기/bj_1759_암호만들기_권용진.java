import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int L;
    static int C;
    static char[] alps;
    static char[] table;
    static List<Character> vowel = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new char[L];
        alps = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alps[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alps);

        sol(0, 0, 0, 0);
        System.out.println(sb);
    }

    static void sol(int depth, int start, int numC, int numV) {
        if (depth == L) {
            if (numC < 2 || numV == 0) return;

            for (int i = 0; i < L; i++) {
                sb.append(table[i]);
            }

            sb.append("\n");
            return;
        }

        for (int i = start; i < C; i++) {
            char curr = alps[i];
            if (vowel.contains(curr)) {
                table[depth] = curr;
                sol(depth + 1, i + 1, numC, numV + 1);
            } else {
                table[depth] = curr;
                sol(depth + 1, i + 1, numC + 1, numV);
            }
        }
    }
}