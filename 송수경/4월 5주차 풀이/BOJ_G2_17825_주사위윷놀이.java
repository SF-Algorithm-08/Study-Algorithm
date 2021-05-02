package boj.Gold;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_G2_17825_주사위윷놀이 {
	static int max;
	static int[] dice = new int[10];
	static ArrayList<ArrayList<Pos>> map = new ArrayList<>();	//게임판 정보 (시작점 포함)
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<10; i++) dice[i] = sc.nextInt();
		sc.close();
		
		init();	//게임판 초기화
		
		int[] player = new int[4];	//4개의 말의 위치
		play(0, 0, player);	//이동 횟수, 점수, 말의 상태
		
		System.out.println(max);
	}

	private static void play(int move, int score, int[] player) {
		if(move==10) {
			max = Math.max(max, score);
			return;
		}
		
		Pos next; int nextP, prevP;
		for(int i=0; i<4; i++) {	//4개의 말
			if(player[i]==31) continue;	//이미 도착한 지점이면 pass
			next = movePlayer(player[i], move);	//매개변수: 움직임 시작할 장소, 몇 번째 움직임인지
			nextP = next.next;
			
			if(nextP!=31 && (nextP==player[(i+1)%4] || nextP==player[(i+2)%4] || nextP==player[(i+3)%4])) continue;
			prevP = player[i];
			player[i] = nextP;
			play(move+1, score+next.score, player);
			player[i] = prevP;
		}
	}

	private static Pos movePlayer(int start, int move) {
		int m = dice[move], score=0;
		
		if(map.get(start).size()>1) {	//안쪽으로 들어가는 지점인 경우
			score = map.get(start).get(1).score;
			start = map.get(start).get(1).next;
			m--;
		}
		while (m-->0 && start<31) {
			score = map.get(start).get(0).score;
			start = map.get(start).get(0).next;
		}
		return new Pos(score, start);
	}

	static void init() {
		for(int i=0; i<33; i++) map.add(new ArrayList<Pos>());
		//시작
		map.get(0).add(new Pos(2, 1));
		//테두리
		for(int i=1; i<20; i++) map.get(i-1).add(new Pos(2*i, i));
		map.get(19).add(new Pos(40, 31));
		//안쪽으로 들어가는 지점
		map.get(5).add(new Pos(13, 20));
		map.get(10).add(new Pos(22, 23));
		map.get(15).add(new Pos(28, 25));
		//안쪽 (중앙까지)
		map.get(20).add(new Pos(16, 21));
		map.get(21).add(new Pos(19, 22));
		map.get(22).add(new Pos(25, 28));
		
		map.get(23).add(new Pos(24, 24));
		map.get(24).add(new Pos(25, 28));
		
		map.get(15).add(new Pos(27, 26));
		map.get(26).add(new Pos(26, 27));
		map.get(27).add(new Pos(25, 28));
		//중앙부터 마지막
		for(int i=28, score=6; i<31;) map.get(i).add(new Pos(5*score++, i++));
		//도착
		map.get(31).add(new Pos(0, 32));
	}
	
	static class Pos {
		int score, next;	//점수, 다음 노드
		//0~19: 바깥쪽(시작~38) / 20~27: 안쪽(중앙 25 전까지) / 28~32: 중앙부터 끝까지
		public Pos(int score, int next) {
			this.score = score;
			this.next = next;
		}
	}
}
