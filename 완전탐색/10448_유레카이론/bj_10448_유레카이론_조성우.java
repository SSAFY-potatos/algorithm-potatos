import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_10448_유레카이론_조성우 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 활용할 삼각수 생성, 저장
        int first = 1;
        int next = 2;
        int idxCnt = 0;
        while (first < 1000) { // K 1000 이하 조건 고려
            first += next;
            next += 1;
            idxCnt++;
        }

        int[] triangleNumbers = new int[idxCnt];
        first = 1;
        next = 2;
        for (int i = 0; i < idxCnt; i++) {
            triangleNumbers[i] = first;
            first += next;
            next += 1;
        }

        // 메인
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            boolean found = false;

            // - 브루트 포스 검사
            for (int a = 0; a < idxCnt; a++) {
                for (int b = 0; b < idxCnt; b++) {
                    for (int c = 0; c < idxCnt; c++) {
                        if (triangleNumbers[a] + triangleNumbers[b] + triangleNumbers[c] == K) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }

            }

            // 결과 출력
            if (found) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}