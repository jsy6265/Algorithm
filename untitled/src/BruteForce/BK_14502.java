package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 연구소
public class BK_14502 {
    static int N, M;
    static int[][] arr;
    static int result;
    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};
    static int defultWall;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        defultWall = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    defultWall++;
                }
            }
        }
        result = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[N][M];

        dfs(visited, 0, 0);

        // 전체 공간 - (처음 벽의 수 + 3(추가된 벽의 수) + 바이러스가 퍼진 최소 값)
        System.out.println((M * N) - (defultWall + 3 + result));
    }

    //먼저 DFS로 벽을 세우는 모든 경우의수를 구한다
    private static void dfs(boolean[][] visited, int cnt, int wall) {
        //벽을 3개 세웠으면 bfs로 바이러스 증식 후 안전공간 체크
        if (wall == 3) {
            int ladella = bfs(arr);
            result = Math.min(result, ladella);
            return;
        }

        if (cnt > N * M) {
            return;
        }
        
        //배열 순회하면서 방문 한 적 없고 벽이 아닌 공간에 벽 세우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    arr[i][j] = 1;
                    dfs(visited, cnt + 1, wall + 1);
                    //배열 원상복구
                    arr[i][j] = 0;
                    visited[i][j] = false;
                }
            }
        }
    }

    // 조합 다구하면 바이러스 퍼뜨리고 안전구역 구하기
    private static int bfs(int[][] arr1) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int cnt = 0; // 바이러스가 차지한 공간 수

        // 배열 전체 순회로 바이러스 찾아서 큐에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 2) {
                    int[] temp = {i, j};
                    q.add(temp);
                    visited[i][j] = true;
                    cnt++;
                }
            }
        }

        // 큐에서 하나씩 꺼내서 4방 탐색 후 퍼져나갈 수 있으면 큐에 넣는다
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int i = temp[0];
            int j = temp[1];
            for (int k = 0; k < 4; k++) {
                int ix = i + delx[k];
                int jy = j + dely[k];
                if (ix < N && ix >= 0 && jy < M && jy >= 0 && arr1[ix][jy] == 0 && !visited[ix][jy]) {
                    int[] temp1 = {ix, jy};
                    q.add(temp1);
                    visited[ix][jy] = true;
                    cnt++;
                }

            }
        }

        return cnt;
    }
}