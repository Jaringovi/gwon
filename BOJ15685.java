// 14312KB | 100ms

import java.io.*;
import java.util.*;

public class BOJ15685 {
    private static final int[][] DIRS = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    public static void main(String[] args) throws IOException {
        // 1. 0~10세대 드래곤 커브를 다 만들어둠 (오른쪽 시작 기준으로, 시작점부터 어떤 방향으로 전진하는지)
        // 2. 모든 드래곤 커브들을 시작점부터 시작해서 미리 생성해둔 드래곤 커브 방향 정보에 따라 선분 기록
        // 선분 기록 방법
        // 1. 우측 꼭지점의 좌표를 기준으로 하는 사각형 배열 생성
        // 2. 선분이 그어질 때 해당 선분 양 옆의 사각형들에 각 사각형 기준으로 어떤 방향의 선분이 그어졌는지 기록
        // 3. 정사각형 찾기 쉬워짐
        // 하지만 선분이 아니라 꼭지점을 기록하는 문제였습니다~~ 괜히 삽질했네

        final int[][] DRAGON_CURVE = new int[11][];

        DRAGON_CURVE[0] = new int[] {0};
        for (int gen = 1; gen <= 10; gen++) {
            int[] prevCurve = DRAGON_CURVE[gen - 1];
            int[] curCurve = Arrays.copyOf(prevCurve, prevCurve.length * 2);
            for (int i = 0; i < prevCurve.length; i++) {
                curCurve[curCurve.length - i - 1] = (curCurve[i] + 1) % 4;
            }
            DRAGON_CURVE[gen] = curCurve;
        }

        final boolean[][] visited = new boolean[101][101];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        final int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            final int d = Integer.parseInt(st.nextToken());
            final int gen = Integer.parseInt(st.nextToken());
            for (int dir : DRAGON_CURVE[gen]) {
                dir = (dir + d) % 4;
                visited[x][y] = true;

                x = x + DIRS[dir][0];
                y = y + DIRS[dir][1];
            }
            visited[x][y] = true;
        }

        int answer = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (visited[x][y] && visited[x][y + 1] && visited[x + 1][y] && visited[x + 1][y + 1]) answer++;
            }
        }
        System.out.println(answer);
    }
}
