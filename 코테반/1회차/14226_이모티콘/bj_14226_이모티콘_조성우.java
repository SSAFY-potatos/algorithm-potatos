import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class bj_14226_이모티콘_조성우 {
  static int S;
  static Queue<Emoji> q = new ArrayDeque<>();
  static boolean[][] visited = new boolean[2002][1001]; // 화면, 클립보드
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    S = Integer.parseInt(br.readLine());
    q.add(new Emoji(1, 0));
    visited[1][0] = true;

    int sec = 0;
    OUTER_LOOP:
    while(!q.isEmpty()){
      int qSize = q.size();
      for (int i = 0; i < qSize; i++){
        Emoji cur_emoji = q.poll();
        int curScreen = cur_emoji.N_screen;
        int curClipboard = cur_emoji.N_clipboard;

        if (curScreen == S) break OUTER_LOOP;

        // 붙여넣기
        if (curScreen < S && !visited[curScreen + curClipboard][curClipboard]){
          q.offer( new Emoji(curScreen + curClipboard, curClipboard) );
          visited[curScreen + curClipboard][curClipboard] = true;
        }

        if (curScreen >= 1){ // 삭제, 복사는 화면에 한개 이상의 이모티콘이 있어야 함
          // 삭제
          if (!visited[curScreen-1][curClipboard]){
            q.offer( new Emoji(curScreen - 1, curClipboard) );
            visited[curScreen - 1][curClipboard] = true;
          }

          // 복사: 화면상 이모티콘이 아직 S보다 작은 경우 복사
          if (curScreen < S && !visited[curScreen][curScreen]){
            q.offer(new Emoji(curScreen, curScreen));
            visited[curScreen][curScreen] = true;
          }
        }
      }
      sec++;
    }

    System.out.println(sec);
  }

  static class Emoji {
    int N_screen;
    int N_clipboard;
    public Emoji (int N_screen, int N_clipboard){
      this.N_screen = N_screen;
      this.N_clipboard = N_clipboard;
    }
  }
}
