package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BK_14500 {
    static int N, M;
    static int[][] arr;

    static int[][] delx = {{0, 1, 2, 3}, {0, 0, 0, 0}, {0, -1, -2, -3}, {0, 0, 0, 0},
                            {0, 1, 1, 0}, {0, -1, -1, 0}, {0, -1, -1, 0}, {0, 1, -1, }};
    static int[][] dely = {{0, 0, 0, 0}, {0, 1, 2, 3}, {0, 0, 0, 0}, {0, -1, -2, -3},
                            {0, 0, 1, 1}, {0, 0, 1, -1}, {0, 0, -1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


    }
}
