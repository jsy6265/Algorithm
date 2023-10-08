package BackTraking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//SW 벌꿀 채취
public class SW_2115 {
    static int N, M, C;
    static int sum;
    static int[][] honeycomb;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            honeycomb = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    honeycomb[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int cnt = 0;
                    int index = 0;
                    sum = 0;
                    int[] temp = new int[M];
                    boolean[] visited = new boolean[M];
                    for (int k = j; k < j + M; k++) {
                        temp[index] = honeycomb[i][k];
                        cnt += honeycomb[i][k];
                        sum += honeycomb[i][k] * honeycomb[i][k];
                        index++;
                    }

                    if (cnt > C) {
                        //조합 짜서 최대값 구하기
                        sum = 0;
                        MaxHoney(visited, temp, 0, 0, 0);
                    }

                    System.out.println(list.size() + " : " +sum);
                    list.add(sum);
                }
            }
            chose = new int[2];
            result = Integer.MIN_VALUE;
            boolean[] visited = new boolean[list.size()];
            dfs(visited, 0);

            System.out.println("#" + test_case + " " + result);
        }
    }


    private static void MaxHoney(boolean[] visited, int[] temp, int cnt, int res, int index) {
        if (index > M  || cnt > C) {
            return;
        } else {
            sum = Math.max(res, sum);
        }

        for (int i = 0; i < M; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int ncnt = cnt + temp[i];
                int nres = res + temp[i] * temp[i];
                MaxHoney(visited, temp, ncnt, nres, index + 1);
                visited[i] = false;

            }
        }
    }

    static int[] chose;
    static int result;

    private static void dfs(boolean[] visited, int death) {
        if (death == 2) {
            result = Math.max(result, list.get(chose[0]) + list.get(chose[1]));
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if(!visited[i]){
                visited[i] = true;
                chose[death] = i;
                dfs(visited, death + 1);
                visited[i] = false;
            }

        }
    }
}
