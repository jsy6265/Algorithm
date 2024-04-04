package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BK_2096 {
    static int N, max, min;
    static int[][] arr;

    static int[] delx = {-1, -1, -1};
    static int[] dely = {-1, 0, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        int[][] minDp = new int[N][3];
        int[][] maxDp = new int[N][3];

        for (int[] id : minDp){
            Arrays.fill(id, Integer.MAX_VALUE);
        }

        for (int[] ad : maxDp){
            Arrays.fill(ad, Integer.MIN_VALUE);
        }

        for (int i = 0; i < 3; i++) {
            minDp[0][i] = arr[0][i];
            maxDp[0][i] = arr[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int nx = i + delx[k];
                    int ny = j + dely[k];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < 3){
                        minDp[i][j] = Math.min(minDp[i][j], minDp[nx][ny] + arr[i][j]);
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[nx][ny] + arr[i][j]);
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            min = Math.min(minDp[N-1][i], min);
            max = Math.max(maxDp[N-1][i], max);
        }

        System.out.println(max + " " + min);
    }

}
