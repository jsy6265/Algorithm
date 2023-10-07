package BFS;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.LinkedList;
        import java.util.Queue;
        import java.util.StringTokenizer;

//백준 미로탐색
public class BK_2178 {
    
    //이동 상태 표시할 클래스
    static class Node {
        int x; //row 좌표
        int y; //col 좌표
        int cnt; //이동 횟수

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            for (int j = 0; j < M; j++) {
                maze[i][j] = Character.getNumericValue(temp.charAt(j));
            }
        }
        int result = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        //문제에 1,1의 좌표에서 시작한다 명시됨
        Node start = new Node(0, 0, 0);
        q.add(start);

        int[] delx = {0, 0, 1, -1};
        int[] dely = {1, -1, 0, 0};

        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        
        //큐에서 Node 객체 꺼내서 4방 탐색 후 이동 가능한 곳 다시 큐에 추가
        while (!q.isEmpty()) {
            Node temp = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = temp.x + delx[k];
                int ny = temp.y + dely[k];

                if (nx < N && nx >= 0 && ny < M && ny >= 0 && !visited[nx][ny] && maze[nx][ny] == 1) {
                    if (nx == N - 1 && ny == M - 1) {
                        result = Math.min(result, temp.cnt + 1);
                    } else {
                        //이동할 좌표, 현재 위치 카운트 + 1
                        Node next = new Node(nx, ny, temp.cnt + 1);
                        visited[nx][ny] = true;
                        q.add(next);
                    }
                }
            }

        }
        System.out.println(result + 1);
    }
}