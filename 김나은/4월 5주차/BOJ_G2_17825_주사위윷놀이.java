package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G2_17825_주사위윷놀이 {

	static int[] dice, order; // 주사위, 말 4개의 이동순서
	static Node[] horse;
	static Node start;
	static int ans;

	static class Node {
		int score;
		boolean isEmpty, isFinish;
		Node next, fastPath; // 파란색 칸일 때 2가지 방향 존재

		public Node(int score) {
			this.score = score;
			this.isEmpty = true;
		}

		public Node addNext(int score) {
			Node nextNode = new Node(score);
			this.next = nextNode;
			return nextNode;
		}

		public static Node getNode(Node start, int target) {
			Node temp = start;
			while (true) {
				if (temp == null) return null;
				if (temp.score == target) return temp;
				temp = temp.next;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		init();

		order = new int[10];
		horse = new Node[4];
		perm(0);
		System.out.println(ans);

	}

	private static void perm(int cnt) {
		if (cnt == 10) {
			ans = Math.max(ans, gameStart());
			return;
		}

		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			perm(cnt + 1);
		}
	}

	private static int gameStart() {
		Arrays.fill(horse, start); // 시작 칸에 말 4개 배치

		int score = 0;
		for (int i = 0; i < 10; i++) {
			Node cur = horse[order[i]]; // 순열에 할당된 순서대로 말 이동
			cur.isEmpty = true;  // 현재 있는 칸을 비워줌

			for (int j = 0; j < dice[i]; j++) { // 주사위에 나온 수만큼 이동
				if (j == 0 && cur.fastPath != null) { // 이동을 시작하려는 처음 위치가 파란색 칸이면
					cur = cur.fastPath; // 지름길로 이동
				} else {
					cur = cur.next;
				}
			}

			horse[order[i]] = cur; // 이동 후, 말 위치 업데이트

			if (!cur.isEmpty && !cur.isFinish) { // 이동을 마친 칸에 다른 말이 있다면, 해당 말은 고를 수 없다
				score = 0; // 점수 리셋
				break;
			} else {
				cur.isEmpty = false; // 말이 존재하는 것으로 표시
				score += cur.score;
			}
		} // 게임 종료

		for (int i = 0; i < 4; i++) {
			horse[i].isEmpty = true; // 다음 게임을 위해 말들의 위치 초기화
		}

		return score;
	}

	private static void init() {
		start = new Node(0); // 시작 칸의 점수는 0

		Node temp = start;
		for (int i = 2; i <= 40; i += 2) {
			temp = temp.addNext(i); // 윷놀이판 바깥 경로 설정
		}

		Node end = temp.addNext(0); // 도착 칸의 점수는 0
		end.isFinish = true;
		end.next = end; // 도착 칸을 넘어서는 이동에 대해 NullPointerException 방지

		Node crossroad = new Node(25); // 교차점(25)

		// 25(교차점) -> 30 -> 35 -> 40
		temp = crossroad.addNext(30);
		temp = temp.addNext(35);
		temp.next = Node.getNode(start, 40);

		// 10 -> 13 -> 16 -> 19 -> 25(교차점)
		temp = Node.getNode(start, 10);
		temp = temp.fastPath = new Node(13);
		temp = temp.addNext(16);
		temp = temp.addNext(19);
		temp.next = crossroad;

		// 20 -> 22 -> 24 -> 25(교차점)
		temp = Node.getNode(start, 20);
		temp = temp.fastPath = new Node(22);
		temp = temp.addNext(24);
		temp.next = crossroad;

		// 30 -> 28 -> 27 -> 26 -> 25(교차점)
		temp = Node.getNode(start, 30);
		temp = temp.fastPath = new Node(28);
		temp = temp.addNext(27);
		temp = temp.addNext(26);
		temp.next = crossroad;
	}

}
