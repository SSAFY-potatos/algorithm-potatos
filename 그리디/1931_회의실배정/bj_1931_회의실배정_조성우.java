import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_1931_회의실배정_조성우 {

    static class Meeting implements Comparable<Meeting> {

        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end > o.end) {
                return 1;
            } else if (this.end == o.end) {
                if (this.start > o.start) {
                    return 1;
                } else if (this.start == o.start) {
                    return 0;
                } else if (this.start < o.start) {
                    return -1;
                }
            } else {
                return -1;
            }
            return Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Meeting> meetings = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings.add(new Meeting(start, end));
        }

        Collections.sort(meetings);
        int cnt = 0;
        int cur_start, prev_end = 0;
        for (int i = 0; i < N; i++) {
            cur_start = meetings.get(i).start;

            if (prev_end <= cur_start) {
                cnt++;
                prev_end = meetings.get(i).end;
            }
        }
        System.out.println(cnt);
    }
}
