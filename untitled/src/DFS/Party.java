package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//영문 소문자 문자열 입력받아서 조합짜기
public class Party {
    static char[] member, pick;
    static int k, result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        String adventurer = st.nextToken();

        st = new StringTokenizer(bf.readLine());
        k = Integer.parseInt(st.nextToken());

        member = new char[adventurer.length()];
        visited = new boolean[adventurer.length()];
        pick = new char[k];

        for (int i = 0; i < adventurer.length(); i++) {
            member[i] = adventurer.charAt(i);
        }

        result = Integer.MIN_VALUE;
        dfs(0);

        System.out.println(result);
    }

    private static void dfs(int depth) {
        if (depth == k) {
            HashMap<Character, Integer> map = new HashMap<>();

            double ad = 0;
            for (int i = 0; i < k; i++) {
                if (!map.containsKey(pick[i])) {
                    map.put(pick[i], 1);
                } else {
                    map.replace(pick[i], map.get(pick[i]) + 1);
                }
            }

            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                ad += Math.pow(entry.getKey() - 96, entry.getValue());
            }
            double sumad = ad % (Math.pow(10, 9) + 7);
            result = Math.max(result, (int) sumad);
            return;
        }

        for (int i = 0; i < member.length; i++) {
            if (!visited[i]) {
                pick[depth] = member[i];
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}