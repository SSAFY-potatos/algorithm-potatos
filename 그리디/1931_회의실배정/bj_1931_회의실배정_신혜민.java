import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int N = Integer.parseInt(br.readLine());

        List<Room> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Room(start, end));
        }

        list.sort(null);

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 우선순위 큐로 안하면 계속 정렬해줘야함 -> 자동 오름차순 정렬
        pq.offer(list.get(0).end);

        for(int i = 1; i < N; i++){
            int end = pq.peek();

            if(end <= list.get(i).start){
                pq.poll();
            }
            pq.add(list.get(i).end);
        }

        System.out.println(pq.size());
    }
}

class Room implements Comparable<Room>{
    int start, end;

    Room(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Room o) {
        if(this.start == o.start){
            return this.end - o.end;
        }
        return this.start - o.start;
    }
}