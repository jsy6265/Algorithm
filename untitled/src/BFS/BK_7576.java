package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BK_7576 {
    static int N, M, result;
    static int[][] arr;
    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;

        bfs();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 0){
                    result = -1;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static class Node {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    q.offer(new Node(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node now = q.poll();

            result = Math.max(result, now.w);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + delx[i];
                int ny = now.y + dely[i];
                if(nx < M && nx >= 0 &&  ny < N &&  ny >= 0 && !visited[nx][ny] && arr[nx][ny] == 0){
                    q.offer(new Node(nx, ny, now.w + 1));
                    visited[nx][ny] = true;
                    arr[nx][ny] = 1;
                }
            }
        }
    }
}