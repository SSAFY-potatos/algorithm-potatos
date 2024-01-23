import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1260_DFS와BFS_조성우 {
    /*
     * 아이디어:
     * 그래프탐색
     * - '정점 번호가 작은 것' 먼저 방문
     * - 큐 구현을 위해 collections - deque사용
     * 1-2
     * |\|
     * 3-4
     * DFS: 1 2 4 3
     * BFS: 1 2 3 4
     */
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[][] arr;

    static int node, line, start;

    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        line = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        arr = new int[node + 1][node + 1]; // node의 값을 index로 하는 node+1 x node+1 그래프관계보드
        visited = new boolean[node + 1]; // node + 1만큼 check할(visited) bool배열

        //// 그래프 입력
        // "fromNode(a) toNode(b)" 토크나이징하여
        // 각각을 arr[a][b], arr[b][a] 에 1로 연결 표시 (무방향 (양방향) 그래프라서)
        for (int i = 0; i < line; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());
            arr[a][b] = arr[b][a] = 1;
        }

        //// dfs 탐색 (재귀) (++ stack 사용하는 것도 공부)
        dfs(start);
        sb.append("\n");

        visited = new boolean[node + 1];

        //// bfs 탐색
        bfs(start);
        System.out.println(sb);

    }

    //// dfs 구현 (재귀)
    public static void dfs(int start) {
        visited[start] = true; // 시작정점 방문처리
        sb.append(start + " ");

        // 대상노드 1 ~ last node까지
        for (int i = 1; i <= node; i++) {
            // 현재 start노드와 관계 존재 & 방문처리 안된 경우
            if (arr[start][i] == 1 && !visited[i])
                dfs(i); // dfs(targetNode); 로 콜백
        } // arr[][]는 scope가 밖에 있으므로, 콜백 깊이와 관계 없이 기록됨
          // dfs가 쭉 들어가다가, 직전 시점 노드의 자식노드 가능성을 모두 확인한 경우 종료(탈출)
    }

    //// bfs 구현
    public static void bfs(int start) {
        // 큐는 선입선출 : ㅇ->[ㅇㅇㅇ]->ㅇ
        q.add(start); // 시작노드 삽입
        visited[start] = true; // 시작노드 방문처리

        while (!q.isEmpty()) { // 큐가 텅 빌때까지 계속
            start = q.poll(); // 정방향 꺼내기( ->ㅇ-> )
            sb.append(start + " ");
            // 대상 노드 1~last 노드까지
            for (int i = 1; i <= node; i++) {
                // 시작 노드와 연결되었는데 : arr[start][i] == 1 && 방문처리 안된 경우
                // 방문해야할 노드로 큐에 추가 (but, bfs기에 맨 뒷순위로 들어감)
                if (arr[start][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true; // 방문처리: 어차피 양방향(대각 대칭) 2차원배열이라, 중복방지도 됨
                }
            }
        }

    }

}