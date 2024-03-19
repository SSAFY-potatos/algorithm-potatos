import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_11000_강의실배정_조성우 {

    static class Lecture implements Comparable<Lecture> {

        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start != o.start) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Lecture> lectures = new ArrayList<>();
        PriorityQueue<Integer> roomEnd = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lectures);
        roomEnd.offer(lectures.get(0).end);
        for (int i = 1; i < N; i++) {
            if (roomEnd.peek() <= lectures.get(i).start) {
                roomEnd.poll();
            }
            roomEnd.offer(lectures.get(i).end);
        }
        System.out.println(roomEnd.size());
    }

}