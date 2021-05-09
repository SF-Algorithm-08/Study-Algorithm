package boj;

import java.util.Scanner;

/**
 * 별 찍기 - 18
 * 입력 	| 총 라인 수	| 총 너비
 *  1 	| 1			| 1
 *  2	| 3			| 5
 *  3	| 7			| 13
 *  4	| 15		| 29
 *  n	| 2^n-1		| 라인수*2-1
 *  (짝수면 역삼각형)
 *  n번째 삼각형은 1, 2, ..., n-1번째 삼각형의 모든 모양을 갖고 있다
 *  => n에서 줄여나가면서 삼각형 다 그려주기
 *  
 *  ## 유의사항!
 *  1) char형은 초기에 null이기 때문에 ' '으로 초기화시켜줘야 함
 *  2) 오른쪽에 공백이 있으면 출력 초과 발생
 *  3) 맨 마지막에 개행이 무의미하게 두 번 반복
 *  ## 이상한 점
 *  draw함수에 각 n에 대한 높이를 재귀할 때 인자로 (1<<n-1)-1값을 넣어줬는데 이상하게 답이 안나왔다. -> 함수 내에서 계산해주니까 됨.. 왜..?
 */

public class BOJ_G4_10993 {
	static char[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int height = (1<<N)-1;
		int width = height*2-1;
		map = new char[height][width];
		// 공백문자로 map 초기화
		for(int i = 0 ; i<height ; i++) {
			for(int j = 0 ; j<width ; j++) {
				map[i][j] = ' ';
			}
		}
		boolean trans = false;
		if(N%2 == 0) trans = true; // 짝수면 역삼각형
		
		// 삼각형의 시작점 구하기
		int W = trans? (1<<N)-2 : 0; // 마지막 줄 인덱스는 (1<<N)-2
		int H = (1<<N)-2; // 전체 열에서 중간
		draw(N, W, H, trans);
		
		// 오른쪽 공백 안나오게끔 출력
		if(!trans) { // 홀수면 똑바로 삼각형
			for(int i = 0 ; i<height ; i++, H++) {
				for(int j = 0 ; j<=H ; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		else {
			for(int i = 0 ; i<height ; i++, width--) {
				for(int j = 0 ; j<width ; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	private static void draw(int n, int x, int y, boolean trans) {
		// 큰 삼각형의 꼭짓점부터 출발
		map[x][y] = '*';
		if(n == 1) return;
		
		int height = (1<<n)-1;
		
		// 투포인터처럼 양쪽으로 뻗어나가면서 삼각형 그려주기
		int left = y;
		int right = y;
		for(int i = 0 ; i<height-1 ; i++) {
			left--;
			right++;
			
			if(!trans) x++; // 그냥 삼각형이면 행 내리면서 그리기
			else x--; // 역삼각형이면 행 올라가면서 그리기
			
			map[x][left] = '*';
			map[x][right] = '*';
		}
		for(int j = left+1 ; j<right ; j++) {
			map[x][j] = '*';
		}
		
		// 인자 값 변경해서 재귀 돌리기 : 다음 삼각형은 이전삼각형의 마지막줄과 한 줄 차이에 그려짐
		if(!trans) draw(n-1, x-1, y, !trans); // 그냥 삼각형이었면 마지막 줄의 이전 라인부터 시작
		else draw(n-1, x+1, y, !trans); // 역삼각형이었으면 마지막 줄의 다음 라인부터 시작
		
	}

}
