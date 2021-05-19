## 2월 4주차 풀이
---
## 1. BOJ S2 20364 부동산 다툼 
### **Idea**
* 오리들이 각자 자신이 갖고 싶은 땅을 가져간다. 땅은 1을 루트로 하는 완전 이진 트리 형식으로 이루어져있다.
* 오리가 원하는 땅까지 가는 길에 이미 땅이 점유되어 있으면 땅을 가져가지 못하고, 처음 마주치는 길을 출력한다.
* 땅을 가질 수 있으면 0을 출력한다.
* 완전 이진 트리이기 때문에 현재 땅부터 2씩 나누어가며 루트노드까지 거슬러 올라간다
* 가는 길에 처음으로 점유된 땅을 만나면 그 땅을 출력한다.
* 중간에 멈추지 않고 루트 노드까지 올라가면 0을 출력한다.


### **구현**
```java
    boolean[] used = new boolean[N+1];
		for(int i = 0; i < Q; i++) {
			int duck = Integer.parseInt(in.readLine().trim());
			int first = 0;
			int node = duck;

			while(node > 0) {      //node>1=이 안되는 이유는 1번이 점유될 수도 있기 때문
				if(used[node])	
					first = node;
				node /= 2;
			}
			if(first != 0)
				System.out.println(first);
			else {
				System.out.println(0);
				used[duck] = true;
			}
		}
```

### 정리
해결 아이디어 자체는 쉽게 생각해냈지만, while문이 종료하는 조건을 node>=1 로 두어서 계속 틀렸었다. 반례가 없도록 꼼꼼히 생각해보고 코드를 짜야겠다는 생각이 들었다.

---
## 1. BOJ S5 12782 비트 우정지수
### **Idea**
* 사용할 수 있는 연산은 <Strong>1. 비트의 자리를 바꾸는 것 2. 0에서 1, 1에서 0으로 수를 바꾸는 것</strong> 두가지가 있다.
* 왼쪽, 오른쪽 수의 1의 개수를 카운팅해서 그 차이만큼 비트를 바꿔야 한다.
* 각 비트별로 다른 비트의 개수를 카운탱해서, 그만큼 자리를 바꿔줘야 하는데 이미 위에서 비트 바꾸는 연산을 했으니 (비트개수차이 - 1개수차이) 해준다.
* 두 자리를 바꿀땐 1번의 swap만 하면 되니 / 2를 해준다
* (a의 1의 개수 - b의 1의 개수) + (비트차이 - (a의 1의 개수 - b의 1의 개수)) / 2


### **구현**
```java
    a = st.nextToken();
    b = st.nextToken();
    int diff = 0, a_one = 0, b_one = 0;
    for(int i = 0; i < a.length(); i++) {
        if(a.charAt(i) != b.charAt(i))	diff++;		// a,b가 다르면 diff++
        if(a.charAt(i)-'0' == 1)	a_one++;			// a에 1이 몇갠지 카운트 
        if(b.charAt(i)-'0' == 1)	b_one++;			// b에 1이 몇갠지 카운트 
    }
    int change = Math.abs(a_one - b_one);
    int result = change + (diff - change) / 2;
    System.out.println(result);
```

### 정리
알쏭달쏭해서 그림을 그리며 생각해보고, 문제에 주어진 연산을 언제 사용해야될까 생각해보니 수식이 떠올랐다. 그런데 완성하기까지 너무 오래걸렸다. 가볍게 생각하면 금방 생각할 수 있는 식인데 나중에 이와 같은 문제를 만나면 큰그림을 보기 위해 노력해야겠다. 


---
## 1. BOJ G5 13549 숨바꼭질 
### **Idea**
* bfs를 이용해 2 * x, x - 1, x + 1을 각각 큐에 넣어주며 목적지에 도달하면 break한다.
* 가지치기를 위해 visited를 만들어 한번 간 지점은 다시 안가게 했다.
* 만약 가려는 지점이 0 미만이거나 100001 이상이면 가지 않는다.

### **구현**
```java
   Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {N, cnt});
		while(!q.isEmpty()) {
			x = q.peek()[0];
			cnt = q.peek()[1];
			q.poll();
			if(x == K) {
				break;
			}
			if(2*x < max && !visited[2*x]) {
				q.offer(new int[] {2 * x, cnt});
				visited[2*x] = true;
			}
			if(x-1 >= 0 && !visited[x-1]) {
				q.offer(new int[] {x-1, cnt+1});
				visited[x-1] = true;
			}
			if(x+1 < max && !visited[x+1]) {
				q.offer(new int[] {x+1, cnt+1});
				visited[x+1] = true;
			}
		}
```

### 정리
visited를 사용하지 않은 코드는 금방 짜서 제출했지만 메모리 초과가 떴었다. 아마 q에 너무 많은 수가 들어가서 그런 것이었다. 그러 항상 깊게 생각하지 않고 코드를 짜는 나쁜 습관이 들었는데 좀 더 깊게 생각하면 좋을 것 같다. 