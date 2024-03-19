//package main;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int M;
    static int[][] maze;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        maze = new int[N][M];
        visited = new boolean[N][M];

        for(int i =0; i<N; i++){
            for(int j =0; j<M; j++){
                maze[i][j] =  br.read() - '0';
            }
            br.read();
        }
        visited[0][0] = true;
        bfs(0,0);
//        System.out.println(maze[N-1][M-1]);
        System.out.println(maze[N-1][M-1]);

    }

    public static void bfs(int s, int e){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{s,e});

        while(!queue.isEmpty()){
            int curr[] = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx>=0 && nx<N && ny>=0 && ny<M){
                    if(maze[nx][ny] == 1 && !visited[nx][ny]){
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        maze[nx][ny] = maze[cx][cy]+1;
                    }
                }
            }
        }
    }
}
