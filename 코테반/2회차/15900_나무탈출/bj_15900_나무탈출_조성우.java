import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_15900_나무탈출_조성우 {

  static int N;
  static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
  static boolean[] visited;
  static int totalDistance = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    visited = new boolean[N + 1];

    for (int i = 0; i <= N; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      int parent = Integer.parseInt(st.nextToken());
      int child = Integer.parseInt(st.nextToken());

      tree.get(parent).add(child);
      tree.get(child).add(parent);
    }

    dfs(1, 0);

    System.out.println(totalDistance % 2 == 0 ? "No" : "Yes");
  }

  static void dfs(int curNode, int dist) {
    visited[curNode] = true;

    if (tree.get(curNode).size() == 1 && curNode != 1) {
      totalDistance += dist;
      return;
    } // 리프처리

    for (int nextNode : tree.get(curNode)) {
      if (!visited[nextNode]) {
        dfs(nextNode, dist + 1);
      }
    }
  }
}