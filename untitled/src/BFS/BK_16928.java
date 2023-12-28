package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BK_16928 {
    static int[] arr = new int[101];
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int ladder = Integer.parseInt(st.nextToken());
        int snake = Integer.parseInt(st.nextToken());

        for (int i = 0; i < ladder; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x] = y;
        }

        for (int i = 0; i < snake; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x] = y;
        }

        bfs();

        System.out.println(result);
    }

    static class Node{
        int x, w;

        public Node(int x, int w) {
            this.x = x;
            this.w = w;
        }
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();

        boolean[] visited = new boolean[101];

        q.offer(new Node(1, 0));
        visited[1] = true;

        while (!q.isEmpty()){
            Node temp = q.poll();

            if(temp.x > 99){
                result = temp.w;
                break;
            }

            for (int i = 1; i < 7; i++) {
                int nx = temp.x + i;
                if(nx < 101 && !visited[nx]){
                    if(arr[nx] == 0){
                        q.offer(new Node(nx, temp.w  + 1));
                        visited[nx] = true;
                    }
                    else {
                        q.offer(new Node(arr[nx], temp.w  + 1));
                        visited[nx] = true;
                    }
                }
            }
        }

    }
}
