package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//백준 바이러스
public class BK_2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        //바이러스에 걸린 컴퓨터 검사하는 배열
        int[] com = new int[N];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.get(x - 1).add(y - 1);
            list.get(y - 1).add(x - 1);
        }

        boolean[] visited = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        //문제에 1번 컴퓨터가 바이러스에 걸렸다 명시됨
        //1번째 컴퓨터 방문 처리 후 com배열 1로 바꾸어 바이러스에 걸린 컴퓨터 체크
        //큐에 추가 후 BFS로 1번 컴퓨터와 연결된 정점들 체크
        visited[0] = true;
        com[0] = 1;
        q.add(0);

        while (!q.isEmpty()) {
            List<Integer> temp = list.get(q.poll());
            for (int i = 0; i < temp.size(); i++) {
                int k = temp.get(i);
                if (!visited[k]) {
                    q.add(k);
                    com[k] = 1;
                    visited[k] = true;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            if (com[i] == 1) {
                result++;
            }
        }

        System.out.println(result - 1);
    }
}