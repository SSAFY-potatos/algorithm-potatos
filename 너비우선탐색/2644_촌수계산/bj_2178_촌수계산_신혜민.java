import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[][] arr = new boolean[n + 1][n + 1];
        boolean[] check = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = true;
            arr[y][x] = true;
        }

        Queue<Person> q = new LinkedList<>();

        q.offer(new Person(start, 0));
        check[start] = true;

        int result = -1;

        while (!q.isEmpty()) {
            Person cnt = q.poll();

            if (cnt.x == end) {
                result = cnt.r;
                break;
            }

            for (int i = 1; i < n + 1; i++) {
                if (arr[cnt.x][i] && !check[i]) {
                    check[i] = true;
                    q.offer(new Person(i, cnt.r + 1));
                }
            }
        }

        System.out.println(result);
    }
}

class Person {
    int x;
    int r;

    public Person(int x, int r) {
        this.x = x;
        this.r = r;
    }
}