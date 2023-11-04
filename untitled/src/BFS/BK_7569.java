package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BK_7569 {
    static int M, N, H, reuslt;
    static int[][][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[N][M][H];
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        reuslt = 0;
        bfs(arr);

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(arr[i][j][k] == 0){
                        reuslt = -1;
                        break;
                    }
                }
            }
        }

        System.out.println(reuslt);
    }

    static int[] delx = {1, -1, 0, 0};
    static int[] dely = {0, 0, 1, -1};
    static int[] delz = {1, -1};

    private static void bfs(int[][][] arr) {
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        
        //익은 토마도 전부 큐에 넣기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(arr[j][k][i] == 1){
                        q.offer(new Node(j, k, i, 0));
                    }
                }
            }
        }

        while (!q.isEmpty()){
            Node tomato = q.poll();
            reuslt = Math.max(tomato.w, reuslt);

            //z index 탐색
            for (int i = 0; i < 2; i++) {
                int nx = tomato.x ;
                int ny = tomato.y ;
                int nz = tomato.z + delz[i];
                if(cheakRange(nx, ny, nz) && arr[nx][ny][nz] == 0 ){
                    arr[nx][ny][nz] = 1;
                    q.offer(new Node(nx, ny, nz, tomato.w + 1));
                }
            }

            //4방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = tomato.x + delx[i];
                int ny = tomato.y + dely[i];
                int nz = tomato.z;

                if(cheakRange(nx, ny, nz) && arr[nx][ny][nz] == 0 ){
                    arr[nx][ny][nz] = 1;
                    q.offer(new Node(nx, ny, nz, tomato.w + 1));
                }
            }

        }
    }

    public static boolean cheakRange(int nx, int ny, int nz){
        if(nx < N && nx >= 0 && ny < M && ny >= 0 && nz < H && nz >= 0){
            return true;
        }else{
            return false;
        }
    }

    public static void print(int[][][] arr) {
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(arr[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println("---------------------------------");
        }
    }

    public static class Node{
        int x, y, z, w;

        public Node(int x, int y, int z, int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }
    }
}
