import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class fri_baek_1600 {
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int jumpCnt;
        int moveCnt;

        public Node(int x, int y, int jumpCnt, int moveCnt) {
            this.x = x;
            this.y = y;
            this.jumpCnt = jumpCnt;
            this.moveCnt = moveCnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.moveCnt, o.moveCnt);
        }
    }
    static int[][] del = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    static int k, w, h;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        boolean[][][][] v = new boolean[h][w][k + 1][12];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.x == h - 1 && cur.y == w - 1) return cur.moveCnt;

            for (int i = 0; i < 12; i++) {
                if (cur.jumpCnt == k && i == 4) break;
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 1 || v[nx][ny][cur.jumpCnt][i]) continue;
                v[nx][ny][cur.jumpCnt][i] = true;
                if (i >= 4) pq.offer(new Node(nx, ny, cur.jumpCnt + 1, cur.moveCnt + 1));
                else pq.offer(new Node(nx, ny, cur.jumpCnt, cur.moveCnt + 1));
            }
        }
        return -1;
    }
}