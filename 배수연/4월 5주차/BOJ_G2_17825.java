package boj;

import java.util.Scanner;

public class BOJ_G2_17825 {
	static int ans;
	static int[] dice, chooseHorse;
	static int[][] horse;
	static final int[][] route = { // 제일 긴 길, 10지름길, 20지름길, 30지름길, 40이후, 40
	// index 0, 1, 2, 3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
			{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38},
			{13, 16, 19},
			{22, 24},
			{28, 27, 26},
			{25, 30, 35},
			{40}
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = 0;
		dice = new int[10];
		for(int i = 0 ; i<10 ; i++) {
			dice[i] = sc.nextInt();
		}
		
		// 각 10번의 주사위에서 몇 번 말을 움직일지 => 말 움직일 개수 정해서 다 움직여보기
		chooseHorse = new int[10];
		perm(0);
		System.out.println(ans);
	}

	private static void perm(int idx) {
		if(idx == 10) {
			ans = Math.max(ans, play());
			return;
		}
		for(int i = 0 ; i<4 ; i++) {
			chooseHorse[idx] = i;
			perm(idx+1);
		}
	}

	private static int play() {
		// 각 말이 몇 번 노선을 타는지 위치와 함께 저장해줘야 함
		// -> h번째 말을 움직일 때에는 해당 말이 현재 몇 번 라인인지 보고 그 라인대로 움직이기
		horse = new int[4][2]; // [말 번호][말 위치 인덱스 / 몇 번 라인]
		for(int i = 0 ; i<4 ; i++) {
			horse[i][0] = -1;
		}
		int sum = 0;
		
		// 순열로 구한 말 순서대로 움직여보기
		for(int i = 0 ; i<10 ; i++) {
			int horseNo = chooseHorse[i];
			int horseIdx = horse[horseNo][0];
			int horseLine = horse[horseNo][1];
			
			// 도착지에 도달한 경우는 horseLine을 -1로 지정해둠
			if(horseLine == -1) continue;
			
			// 말의 위치가 라인을 변경시킬 위치에 있으면 라인을 변경시켜주기
			if(horseLine == 0 && horseIdx != -1) {
				if(route[horseLine][horseIdx] == 10) {
					horseLine = 1;
					horseIdx = -1;
				}
				else if(route[horseLine][horseIdx] == 20) {
					horseLine = 2;
					horseIdx = -1;
				}
				else if(route[horseLine][horseIdx] == 30) {
					horseLine = 3;
					horseIdx = -1;
				}
			}
			
			// 주사위 값만큼 1씩 더해보면서 라인 변경해야하는지 판단
			// 0, 4번 라인은 40으로 모이게 / 10, 20, 30으로 출발하는 라인들 모두 25로 모이게 하기
			for(int j = 1 ; j<=dice[i] ; j++) {
				horseIdx++;
				if(horseLine == -1) break;
				if(horseIdx == route[horseLine].length) {
					if(horseLine == 0) {
						horseLine = 5;
						horseIdx = 0;
					}
					else if(horseLine == 1 || horseLine == 2 || horseLine == 3) {
						horseLine = 4;
						horseIdx  = 0;
					}
					else if(horseLine == 4) {
						horseLine = 5;
						horseIdx = 0;
					}
					else if(horseLine == 5) {
						horseLine = -1;
						horseIdx = 0;
					}
				}
			}
			
			// 라인이 -1이 됐다면 도착했다는 의미이므로 다른 말과 중복 검사 안하고 continue
			if(horseLine == -1) {
				horse[horseNo][1] = horseLine;
				continue;
			}
			
			// 말을 움직였는데 다른 말이 있는지 확인하기
			boolean isPossible = true;
			for(int j = 0 ; j<4 ; j++) {
				if(horse[j][0] == horseIdx && horse[j][1] == horseLine) isPossible = false;
			}
			
			if(isPossible) {
				horse[horseNo][0] = horseIdx;
				horse[horseNo][1] = horseLine;
				sum += route[horseLine][horseIdx];
			}
			// 중복으로 걸리게 된 경우에는 바로 다음 조합을 구해야 하기 때문에 return (안하면 29%에서 틀림)
			else return -1; 
		}
		return sum;
	}

}
