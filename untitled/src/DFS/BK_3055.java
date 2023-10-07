package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 탈출
public class BK_3055 {
    static int[] start;
    static int[] end;
    static char[][] forest;
    static int N;
    static int M;

    static List<int[]> waterList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        forest = new char[N][M];
        waterList = new ArrayList<>();
        start = new int[2];
        end = new int[2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            for (int j = 0; j < M; j++) {
                forest[i][j] = temp.charAt(j);
                //도착점
                if (forest[i][j] == 'D') {
                    end[0] = i;
                    end[1] = j;
                }
                //시작점
                else if (forest[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                //물좌표 리스트에 추가
                else if (forest[i][j] == '*') {
                    int[] water = {i, j};
                    waterList.add(water);
                }
            }
        }
        boolean[][] visited = new boolean[N][M];
        Player first = new Player(start[0], start[1], 0);
        int result = bfs(visited, first);
        //result에 변화가 없으면 조건 성립 x 
        if (result == Integer.MAX_VALUE) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }
    
    //이동 상태 표시할 클래스
    static class Player {
        int x;
        int y;
        int cnt;

        public Player(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};

    private static int bfs(boolean[][] visited, Player start) {
        //도착점에 들어온 cnt의 최소값 리턴하기 위해 MAX_VALUE로 초기화
        int result = Integer.MAX_VALUE;
        Queue<Player> q = new LinkedList<>();
        
        //시작점 큐에 추가
        q.add(start);
        
        //현재 턴 표시
        int turn = -1;
        
        while (!q.isEmpty()) {
            Player temp = q.poll();
            int x = temp.x;
            int y = temp.y;
            int size = waterList.size();
            //큐에서 꺼낸 Player객체의 cnt가 현재 턴보다 크다면 1턴이 끝난 것으로 간주 후 물 영역 확장
            if (turn < temp.cnt) {
                for (int i = 0; i < size; i++) {
                    int[] water = waterList.get(i);
                    for (int k = 0; k < 4; k++) {
                        int nx = water[0] + delx[k];
                        int ny = water[1] + dely[k];
                        if (nx < N && nx >= 0 && ny < M && ny >= 0 && forest[nx][ny] == '.') {
                            forest[nx][ny] = '*';
                            int[] newWater = {nx, ny};
                            waterList.add(newWater);
                        }
                    }
                }
                //텀을 현재 플레이어 cnt로 최신화
                turn = temp.cnt;
            }
            
            //4방 탐색 하면서 이동 가능한 좌쵸 큐에 추가
            for (int i = 0; i < 4; i++) {
                int nx = x + delx[i];
                int ny = y + dely[i];
                if (nx < N && nx >= 0 && ny < M && ny >= 0 && !visited[nx][ny] && (forest[nx][ny] == '.' || forest[nx][ny] == 'D')) {
                    if (nx == end[0] && ny == end[1]) {
                        result = Math.min(result, temp.cnt + 1);
                    } else {
                        Player next = new Player(nx, ny, temp.cnt + 1);
                        q.add(next);
                        visited[nx][ny] = true;
                    }

                }
            }
        }

        return result;
    }
}