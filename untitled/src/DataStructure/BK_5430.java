package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BK_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            //함수 문자열
            st = new StringTokenizer(bf.readLine());
            String function = st.nextToken();

            st = new StringTokenizer(bf.readLine());
            //배열 크기
            int N = Integer.parseInt(st.nextToken());
            //배열에 들어갈 숫자
            String arrStr = bf.readLine();
            //제일 앞에과 뒤에 대괄호 자르기
            arrStr = arrStr.substring(1, arrStr.length() - 1);
            List<String> arr = new ArrayList<>();

            boolean isReverse = false;
            boolean isError = false;

            if (!arrStr.isEmpty()) {
                if (arrStr.contains(",")) {
                    String[] str = arrStr.split(",", N);
                    arr.addAll(Arrays.asList(str));
                } else {
                    arr.add(arrStr);
                }
            }

            for (int i = 0; i < function.length(); i++) {
                char temp = function.charAt(i);
                if (temp == 'R') {
                    if (isReverse) {
                        isReverse = false;
                    } else {
                        isReverse = true;
                    }
                } else if (temp == 'D') {
                    if (arr.isEmpty()) {
                        sb.append("error").append("\n");
                        isError = true;
                        break;
                    } else if (isReverse) {
                        arr.remove(arr.size() - 1);
                    } else {
                        arr.remove(0);
                    }

                }
            }

            if (!isError) {
                if (isReverse) {
                    Collections.reverse(arr);
                }
                sb.append("[");
                for (int i = 0; i < arr.size(); i++) {
                    String temp = arr.get(i);
                    sb.append(temp);
                    if (i < arr.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]").append("\n");

            }


        }
        System.out.println(sb);
    }
}
