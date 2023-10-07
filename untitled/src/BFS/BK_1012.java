package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 유기농 배추
public class BK_1012 {
    static int arr[][];
    static boolean[][] visit;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int Tc = 0; Tc < T; Tc++) {
            st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr = new int[n][m];

            for (int i = 0; i < c; i++) {
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
            }

            visit = new boolean[n][m];
            int result = 0;
            
            //배열 전체 순회 하면서 방문한적 없는 배추가 나오면 BFS 호출
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 1 && !visit[i][j]) {
                        visit[i][j] = true;
                        bfs(i, j);
                        result++;
                    }
                }
            }
            System.out.println(result);
        }
    }

    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};
    
    static void bfs(int x, int y) {
        int nx;
        int ny;
        //4방 탐색 하여 배추가있는 지역은 이동가능
        //x, y 좌표에서 부터 이어진 모든 배추들을 하나의 구역으로 묶는다
        for (int i = 0; i < 4; i++) {
            nx = x + delx[i];
            ny = y + dely[i];
            if (nx < n && ny < m && nx >= 0 && ny >= 0 && arr[nx][ny] == 1 && !visit[nx][ny]) {
                visit[nx][ny] = true;
                bfs(nx, ny);
            }
        }
    }

}