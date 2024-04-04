package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//백준 녹색 옷 입은 애가 젤다지?
public class BK_4485 {
    static  int N, result;
    static int[][] cave, dp;

    static int[] delx = {0, 1, 0, -1};
    static int[] dely = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 0;

        while (++T > 0) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            cave = new int[N][N];
            dp = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            result = Integer.MAX_VALUE;
            dijk(0, 0);

            System.out.println("Problem " + T + " : " + dp[N-1][N-1]);
        }
    }

    static  class Node implements Comparable<Node>{
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
    private static void dijk(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(x, y, cave[x][y]));

        while (!pq.isEmpty()){

            Node temp = pq.poll();
            //여기 까지 오는데 더 큰 비용으로 왔으면 더 가봤자 최소값 아님
            if (dp[temp.y][temp.x] < temp.w)
                continue;
            for (int i = 0; i < 4; i++) {
                int nx = temp.x + delx[i];
                int ny = temp.y + dely[i];

                if(nx < N && nx >= 0 && ny < N && ny >= 0){
                    if (cave[ny][nx] + temp.w < dp[ny][nx]) {
                        dp[ny][nx] = cave[ny][nx] + temp.w;
                        pq.add(new Node(nx, ny,  dp[ny][nx]));
                    }
                }
            }
        }
    }
}
