import java.io.*;
import java.util.*;

public class bj_2110_공유기설치_조성호 {
  static int n, c;
  static int[] map;

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken()); //
    c = Integer.parseInt(st.nextToken());

    map = new int[n];
    for(int i = 0; i < n; i++)
      map[i] = Integer.parseInt(br.readLine());

    sb.append(findWifi());
    bw.write(sb.toString());
    bw.flush();
    bw.close();
    br.close();
  }

  static int findWifi() {
    Arrays.sort(map);	// 이분 탐색은 정렬된 배열에 사용 가능.

    int low = 1;	// 최소 거리
    int high = map[n-1] - map[0] + 1;	// 최대치는 왼쪽 끝에서 오른쪽 끝 집 간 거리

    while(low < high) {
      int mid = (low + high) / 2;

      if(canInstall(mid) < c)
        high = mid;		// mid 거리에 대해 설치 가능한 공유기 개수가 M 개수에 못미치면 거리를 좁혀야 하기 때문에 high 를 줄인다.


      else
        low = mid + 1;	// mid 거리에 대해 설치 가능한 공유기 개수가 M 개수를 넘으면 거리를 늘려야 하기 때문에 low를 키운다.
    }

    return (low - 1);
  }

  static int canInstall(int d) {
    int cnt = 1;	// 첫 집은 무조건 설치
    int lastLocate = map[0];	// 가장 최근에 설치한 집의 위치 저장

    for(int i=1; i < map.length; i++) {
      int locate = map[i];

      if(locate - lastLocate >= d) {
        cnt++;
        lastLocate = locate;
      }
    }

    return cnt;
  }
}
