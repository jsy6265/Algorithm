package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BK_2638 {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, result, chesseCnt;
    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};
    static int[][] arr;
    static boolean[][] visied;
    static List<Node> chesses;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        chesses = new ArrayList<>();
        chesseCnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    chesses.add(new Node(i, j));
                    chesseCnt++;
                }
            }
        }

        result = 0;

        while (chesseCnt != 0){
            result++;
            visied = new boolean[N][M];
            dfs(0, 0);
            removeChesse();
        }

        System.out.println(result);
    }

    private static void removeChesse() {
        for (int i = 0; i < chesses.size(); i++) {
            Node chesse = chesses.get(i);
            int cnt = 0;

            for (int j = 0; j < 4; j++) {
                int nx = chesse.x + delx[j];
                int ny = chesse.y + dely[j];
                if(nx < 0 && nx >= N && ny < 0 && ny >= M) continue;
                if(arr[nx][ny] == 2){
                    cnt++;
                }
            }

            if(cnt > 1){
                arr[chesse.x][chesse.y] = 0;
                chesses.remove(i);
                chesseCnt--;
                i--;
            }
        }
    }

    static void dfs(int x, int y){
        visied[x][y] = true;
        arr[x][y] = 2;
        for (int i = 0; i < 4; i++) {
            int nx = x + delx[i];
            int ny = y + dely[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visied[nx][ny] && arr[nx][ny] != 1){
                dfs(nx, ny);
            }
        }
    }
}
