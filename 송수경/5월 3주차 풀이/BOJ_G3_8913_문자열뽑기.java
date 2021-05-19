package boj.Gold;
//G3 문자열 뽑기 (자료 구조,다이나믹 프로그래밍,트리를 사용한 집합과 맵)
//solved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_G3_8913_문자열뽑기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++)	//각 테스트케이스마다 문자열 뽑기 수행
			sb.append(solve(br.readLine().trim())).append("\n");
		
		System.out.println(sb);
	}
	
	static int solve(String str) {
		int length = str.length();	//초기에 입력받은 문자열의 길이
		ArrayList<Integer> list = new ArrayList<>();	//그룹을 나타내는 ArrayList
		
//		1. 그룹 나누기
		int idx = 0; char pre = ' ';
		while(idx<length) {
			if(str.charAt(idx)==pre) list.set(list.size()-1, list.get(list.size()-1) + 1);
			else list.add(1);	//이전의 문자와 다르면 새롭게 요소 추가
			pre = str.charAt(idx++);
		}
		
//		2. 우선순위 큐: 문자열의 길이를 기준으로 오름차순 정렬
		PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.size() - o2.size();
			}
		});
		
//		3. 요소를 하나씩 지워보면서 우선순위 큐에 추가한다.
		pq.offer(list);
		ArrayList<Integer> curList = null;	//현재 참조하는 그룹 상태	
		while(!pq.isEmpty()) {
			curList = pq.poll();
			
			if(curList.size()==1) {	//그룹이 하나남았을 때
				if(curList.get(0)>1) return 1;	//2이상이면 빈 문자열로 바꿀 수 있으므로 1 리턴
				continue;	//아니라면 다음 그룹 검사하러 넘어가기
			}
			
			int preInt=0, curInt=0, nextInt=0;	//앞,뒤까지의 그룹을 이루는 문자 개수
			boolean flag = false;	//지운 그룹이 끝과 끝이 아니면 true
			for(int i=0, end=curList.size(); i<end; i++) {
				if(curList.get(i)==1) continue;	//하나면 못지운다
				//현재 그룹 지우기
				curInt = curList.get(i);
				curList.remove(i);
				
				if(i-1>=0 && i<curList.size()) {	//만약 끝과 끝이 아니라면
					flag = true;
					//앞, 뒤가 같은 문자이므로 합쳐준다. (문자는 a또는 b뿐이므로)
					preInt = curList.get(i-1);
					nextInt = curList.get(i);
					curList.remove(i);
					curList.remove(i-1);
					curList.add(i-1, preInt + nextInt);
				}
				
				//지운 그룹 정보를 우선순위 큐에 저장
				pq.offer((ArrayList<Integer>) curList.clone());
				
				//다시 상태 되돌려주기
				curList.add(i, curInt);
				if(flag) {	//끝과 끝이 아니었다면
					flag = false;
					//앞,뒤 그룹 정보까지 되돌리기
					curList.remove(i-1);
					curList.add(i, nextInt);
					curList.add(i-1, preInt);
				}
			}
		}
//		4. 위에서 return되지 못했다면 빈 문자열을 만들 수 없었다는 것이므로 0 리턴
		return 0;
	}
}
