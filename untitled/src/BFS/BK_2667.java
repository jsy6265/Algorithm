package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 단지번호붙이기
public class BK_2667 {
    static List<Integer> dangi;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] town = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            for (int j = 0; j < N; j++) {
                town[i][j] = Character.getNumericValue(temp.charAt(j));
            }
        }

        dangi = new ArrayList<>();

        bfs(N, town);

        System.out.println(dangi.size());
        
        //단지 별 가구를 오름차 순으로 정렬
        Collections.sort(dangi);

        for (int x : dangi) {
            System.out.println(x);
        }
    }

    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};

    private static void bfs(int N, int[][] town) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visite = new boolean[N][N];
        
        //배열 순회 하면서 1을 발견하면 BFS로 해당 좌표와 연결된 모든 집을 방문
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //해당 좌표를 방문한 적이 없고 값이 1이면 새로운 단지 형성 가능 조건 충족
                if (!visite[i][j] && town[i][j] == 1) {
                    //좌표를 방문 처리 후 큐에 추가
                    int[] start = {i, j};
                    visite[i][j] = true;
                    q.add(start);
                    //단지에 몇개의 가구가 있는지 체크
                    int cnt = 1;
                    //큐가 빌때 까지 인접한 가구 추가
                    while (!q.isEmpty()) {
                        int[] temp = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = temp[0] + delx[k];
                            int ny = temp[1] + dely[k];
                            if (nx < N && nx >= 0 && ny < N && ny >= 0 && !visite[nx][ny] && town[nx][ny] == 1) {
                                int[] next = {nx, ny};
                                visite[nx][ny] = true;
                                q.add(next);
                                cnt++;
                            }
                        }
                    }
                    //새로운 단지가 몇개의 가구가 있는지 리스트에 추가
                    dangi.add(cnt);
                }
            }
        }
    }
}