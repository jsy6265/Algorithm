package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BK_2206 {
    static int N, M, result;
    static int[][] map;

    static int[] delx = {1, -1, 0, 0};
    static int[] dely = {0, 0, 1, -1};

    static class Node {
        int x, y, work, block;

        public Node(int x, int y, int work, int block) {
            this.x = x;
            this.y = y;
            this.work = work;
            this.block = block;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String low = st.nextToken();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(low.charAt(j));
            }
        }

        result = Integer.MAX_VALUE;

        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

//        dfs(0, 0, 0, 0, visited);
        bfs();

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];

        if (map[0][0] == 1) {
            q.offer(new Node(0, 0, 1, 1));
            visited[0][0][1] = true;
        } else {
            q.offer(new Node(0, 0, 1, 0));
            visited[0][0][0] = true;
        }


        while (!q.isEmpty()) {
            Node temp = q.poll();
            if (temp.x == N - 1 && temp.y == M - 1) {
                result = Math.min(result, temp.work);
            }

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + delx[i];
                int ny = temp.y + dely[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny][temp.block]) {
                    if (map[nx][ny] == 0) {
                        q.offer(new Node(nx, ny, temp.work + 1, temp.block));
                    } else if (temp.block < 1) {
                        q.offer(new Node(nx, ny, temp.work + 1, temp.block + 1));
                    }

                    visited[nx][ny][temp.block] = true;
                }
            }
        }
    }
}


//private static void dfs(int work, int block, int x, int y, boolean[][] visited) {
//    if (block > 1 || work > result) {
//        return;
//    }
//
//    if (x == N - 1 && y == M - 1) {
//        result = Math.min(work + 1, result);
//    }
//
//    for (int i = 0; i < 4; i++) {
//        int nx = x + delx[i];
//        int ny = y + dely[i];
//
//        if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
//            visited[nx][ny] = true;
//            if (map[nx][ny] == 0) {
//                dfs(work + 1, block, nx, ny, visited);
//            } else {
//                dfs(work + 1, block + 1, nx, ny, visited);
//            }
//            visited[nx][ny] = false;
//        }
//    }
//}