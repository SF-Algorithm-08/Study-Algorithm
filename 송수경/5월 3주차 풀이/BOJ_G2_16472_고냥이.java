package boj.Gold;
//G2 고냥이 (이분 탐색,투 포인터)
//solved

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_G2_16472_고냥이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String S = sc.next();
		sc.close();
		
		boolean[] used = new boolean[26];	//알파벳 사용 여부
		ArrayList<Alphabet> list = new ArrayList<>();	//사용된 알파벳, 그 인덱스 저장
		int cnt = 0, idx = 0, curlen = 0, maxlen = 0;	//쓰인 알파벳 개수, 검사하는 인덱스, 현재 길이, 가장 긴 길이
		do {
			char cur = S.charAt(idx);
			if(used[cur-'a']) {	//기존에 인식한 알파벳인 경우
				for(Alphabet a : list) {	//기존에 있던 cur 객체 삭제
					if(a.ch==cur) {
						list.remove(a);
						break;
					}
				}
				curlen++;
			} else {	//새로운 알파벳인 경우
				used[cur-'a'] = true;
				
				if(cnt<N) {	//인식할 알파벳 남은 경우
					cnt++;
					curlen++;
				} else {	//알파벳 지워야하는 경우
					Alphabet remove = list.get(N-1);
					list.remove(N-1);
					used[remove.ch-'a'] = false;
					curlen = idx - remove.idx;
				}
			}
			list.add(0, new Alphabet(cur, idx));
			maxlen = maxlen>curlen?maxlen:curlen;
		} while(++idx<S.length());
		
		System.out.println(maxlen);
	}

	static class Alphabet{
		int idx; char ch;
		public Alphabet(char ch, int idx) {
			this.ch = ch;
			this.idx = idx;
		}
	}
}

/*
3
abbssvssba
*/
