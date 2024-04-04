package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BK_1916 {
    static class Node{
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, start, end;
    static List<List<Node>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijk();
    }

    private static void dijk() {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            int nodeIdx = 0;
            int nodeVal = Integer.MAX_VALUE;

            for (int j = 1; j < N + 1; j++) {
                if(!visited[j] && dist[j] < nodeVal){
                    nodeIdx = j;
                    nodeVal = dist[j];
                }
            }
            visited[nodeIdx] = true;

            for (int j = 0; j < list.get(nodeIdx).size(); j++) {
                Node adj = list.get(nodeIdx).get(j);

                if(dist[adj.to] > dist[nodeIdx] + adj.cost){
                    dist[adj.to] = dist[nodeIdx] + adj.cost;
                }
            }
        }

        System.out.println(dist[end]);
    }
}
