package DataStructure;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 백준 듣보잡
public class BK_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        //input 받을 듣보 수
        int dudNum = Integer.parseInt(st.nextToken());
        int boNum = Integer.parseInt((st.nextToken()));

        HashMap<String, Integer> dudbo = new HashMap<>();

        //듣보 문자열 입력
        for (int i = 0; i < dudNum + boNum; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            //이미 해시맵에 있는 단어이면 value + 1
            if (dudbo.containsKey(temp)) {
                dudbo.put(temp, dudbo.get(temp) + 1);
            } else {
                dudbo.put(temp, 1);
            }
        }

        int cnt = 0;

        StringBuilder result = new StringBuilder();

        String[] key = dudbo.keySet().toArray(new String[0]);
        Arrays.sort(key);
        
        //해시맵 전체 순회하여 벨류가 1보다 큰 단어만 출력
        for (int i = 0; i < dudbo.size(); i++) {
            if (dudbo.get(key[i]) > 1) {
                result.append(key[i]).append("\n");
                cnt++;
            }
        }

        System.out.println(cnt);
        System.out.println(result);
    }
}