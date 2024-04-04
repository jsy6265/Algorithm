package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BK_1753 {

    static class Node {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static int N, E, start;
    static List<List<Node>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list.get(from).add(new Node(to, dist));
        }

        int[] arr = dijk();

        for (int i = 1; i < N + 1; i++) {
            if (arr[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(arr[i]);
            }
        }
    }

    private static int[] dijk() {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            int nodeIdx = 0;
            int nodeVal = Integer.MAX_VALUE;

            //방문하지 않은 노드중 최소값 구하기
            for (int j = 1; j < N + 1; j++) {
                if (!visited[j] && dist[j] < nodeVal) {
                    nodeIdx = j;
                    nodeVal = dist[j];
                }
            }

            visited[nodeIdx] = true;

            //찾은 노드와 연결된 노드중 현재 노드를 거쳐가는 것이 비용이 적으면 dist배열 수정
            for (int j = 0; j < list.get(nodeIdx).size(); j++) {
                Node adj = list.get(nodeIdx).get(j);

                if (dist[adj.to] > dist[nodeIdx] + adj.dist) {
                    dist[adj.to] = dist[nodeIdx] + adj.dist;
                }
            }
        }

        return dist;
    }
}
