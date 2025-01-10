import java.io.*;

public class BOJ5052 {
    private static class Node {
        boolean end = false;
        final Node[] children = new Node[10];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            Node root = new Node();
            boolean wrong = false;

            final int N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n++) {
                final String phone = br.readLine();
                if (wrong)
                    continue;
                Node cur = root;
                for (int i = 0; i < phone.length(); i++) {
                    char ch = phone.charAt(i);
                    int idx = ch - '0';
                    if (cur.children[idx] == null) {
                        cur.children[idx] = new Node();
                    }
                    cur = cur.children[idx];

                    if (cur.end) {
                        wrong = true;
                    }
                }
                cur.end = true;
                // 123 12 같이 이후에 prefix가 추가되는 경우도 확인 필요
                for (Node child : cur.children)
                    if (child != null)
                        wrong = true;
            }

            if (wrong)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }
}
