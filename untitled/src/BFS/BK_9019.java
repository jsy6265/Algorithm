package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BK_9019 {
    //D = n * 2 (9999보다 크면 10000으로 나눈 나머지)
    //S = n - 1 (0이면 9999)
    //L = n의 1번쨰 자리수가 맨 뒤로
    //R = 마지막 자리수가 맨 앞으로
    //맨 앞자리가 0이면 생랼해야됨
    static Queue<Node> q;
    static boolean[] visited;

    static  StringBuilder sb;

    static class Node {
        //현재 숫자, 수행한 명령어
        int num;
        String command;

        public Node(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            q = new LinkedList<>();
            visited = new boolean[10000];

            bfs(A, B);
        }

        System.out.println(sb);
    }

    private static void bfs(int a, int b) {
        visited[a] = true;

        q.offer(new Node(a, ""));

        while (!q.isEmpty()) {
            Node now = q.poll();
            //현재 명령어를 수행한 숫자가 b와 같으면 종료
            if (now.num == b) {
                sb.append(now.command).append("\n");
                break;
            }

            D(now);
            S(now);
            L(now);
            R(now);
        }

    }

    static void D(Node now) {
        int result = 0;

        int x = (now.num * 2) % 10000;

        result = x;

        if (!visited[result]) {
            q.offer(new Node(result, now.command + "D"));
            visited[result] = true;
        }

    }

    static void S(Node now) {
        int result = 0;

        if(now.num == 0){
            result = 9999;
        }else{
            result = now.num - 1;
        }

        if (!visited[result]) {
            q.offer(new Node(result, now.command + "S"));
            visited[result] = true;
        }
    }

    static void L(Node now) {
        int val = 0;
        char[] arr = new char[4];

        String s = String.valueOf(now.num);
        int idx1 = 3;
        int idx2 = s.length()-1;

        while(idx2 >= 0) {
            arr[idx1--] = s.charAt(idx2--);
        }

        for(int i = idx1; i >= 0; i--) {
            arr[i] = '0';
        }

        char tmp = arr[0];

        for(int i = 1; i < 4; i++) {
            arr[i-1] = arr[i];
        }
        arr[3] = tmp;
        val = Integer.parseInt(new String(arr));
        if(!visited[val]) {
            visited[val] = true;
            q.offer(new Node(val, now.command + "L"));
        }

    }

    static void R(Node now) {
        int val = 0;
        char[] arr = new char[4];
        String s = String.valueOf(now.num);
        int idx1 = 3;
        int idx2 = s.length()-1;

        while(idx2 >= 0) {
            arr[idx1--] = s.charAt(idx2--);
        }

        for(int i = idx1; i >= 0; i--) {
            arr[i] = '0';
        }

        char tmp = arr[3];

        for(int i = 3; i >= 1; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;

        val = Integer.parseInt(new String(arr));
        if(!visited[val]) {
            visited[val] = true;
            q.offer(new Node(val, now.command + "R"));
        }

    }
}
