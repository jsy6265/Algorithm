package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//백준 나는야 포켓몬 마스터 이다솜
public class BK_1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int encyclopediaNum = Integer.parseInt(st.nextToken());
        int qusetionNum = Integer.parseInt(st.nextToken());
        //두개의 해시로 이름 - 번호, 번호 - 이름의 비교
        HashMap<String, Integer> ency = new HashMap<>();
        HashMap<Integer, String> encyre = new HashMap<>();
        //hashmap에 이름, 도감번호로 저장
        for (int i = 0; i < encyclopediaNum; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            ency.put(temp, i + 1);
            encyre.put(i + 1, temp);
        }
        for (int i = 0; i < qusetionNum; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();
            //질문이 숫자면 temp로 조회시 null이 나온다
            if (ency.get(temp) == null) {
                //entrySet을 사용해 Map의 전체 key와 value를 꺼낸다
                System.out.println(encyre.get(Integer.parseInt(temp)));
            } else {
                //질문이 이름이면 hashmap에서 get으로 해당 벨류의 키값 가져오기
                System.out.println(ency.get(temp));
            }

        }
    }
}