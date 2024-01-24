import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Floor{
    int f;
    int d;

    public Floor(int f, int d) {
        this.f = f;
        this.d = d;
    }

    public int getF() {
        return f;
    }

    public int getD() {
        return d;
    }
}

public class Main {
    static int bfs(Floor n, int F,int G,int U,int D,boolean[] visited){
        Queue<Floor> queue = new LinkedList<>();
        queue.add(n);
        visited[n.getF()] = true;

        while(queue.size()!=0){
            Floor f = queue.poll();
            if(f.getF() == G){
                return f.getD();
            }

            if((f.getF()+U)>0 && (f.getF()+U)<=F){
                if(visited[f.getF()+U] == false){
                    queue.add(new Floor(f.getF()+U,f.getD()+1));
                    visited[f.getF()+U] = true;
                }
            }

            if((f.getF()-D)>0 && (f.getF()-D)<=F){
                if (visited[f.getF() - D] == false) {
                    queue.add(new Floor(f.getF() - D, f.getD() + 1));
                    visited[f.getF() - D] = true;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F+1];

        int answer = bfs(new Floor(S,0),F,G,U,D,visited);
        if(answer == -1 ){
            System.out.println("use the stairs");
        }else{
            System.out.println(answer);
        }
    }
}