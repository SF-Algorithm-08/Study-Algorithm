## 3월 1주차 풀이
----
## 1. BOJ S1 6118 숨바꼭질 
### **문제설명**
* 재서기가 숨을 가장 먼 헛간의 번호, 거리, 같은 거리의 헛간 개수를 출력하라 

### **Idea**
* 헛간들의 연결 정보를 인접 리스트로 저장한다.
* BFS로 탐색한다. 큐에 헛간 번호와 거리를 저장해준다. 1번에서 출발해서 연결되어 있고, 아직 방문하지 않은 헛간을 큐에 넣어준다.
* 만약 현재 노드에서 연결된 다른 노드가 없거나, 모두 방문했다면 그 헛간이 1에서부터 가장 먼 헛간이 된다.
* 방문할 인접 노드가 없을 때의 거리를 최대값으로 갱신해준다. 

### **구현**
```java
static void bfs() {
    Queue<int[]> q = new LinkedList<int[]>();
    q.offer(new int[] {1, 0});
    visited[1] = true;
    
    while(!q.isEmpty()) {
        int cur = q.peek()[0];
        int dist = q.peek()[1];
        q.poll();
        
        int size = adj[cur].size();
        boolean visit = false;
        for(int i=0; i <size; i++) {
            int next = adj[cur].get(i);
            if(!visited[next]) {
                visited[next] = true;
                visit = true;
                q.offer(new int[] {next, dist+1});
            }
        }
        if(!visit) {
            if(dist > maxDist) {
                maxDist = dist;
                idx = cur; 
                cnt = 1;
            }else if(dist == maxDist) {
                idx = Math.min(idx, cur);
                cnt++;
            }
        }
    }
}
```

### **정리**
단순 BFS로 풀 수 있어서 쉬운 문제였다. 다만 1에서부터 떨어진 헛간의 거리가 같을 때 가장 작은 헛간의 수와, 개수를 출력해 줘야 했기 때문에 이 부분에서 조금 생각했어야 했다. 만약 거리가 max값보다 크다면 헛간 번호를 현재 헛간의 수로, 헛간 개수는 1로 저장하면 된다. 만약 거리가 max와 같다면 번호만 더 작은 것으로 갱신해주고 개수를 ++1 해주었다.

---
## 2. BOJ G4 5052 전화번호 목록 
### **문제설명**
* 한 전화번호가 다른 전화번호 처음에 포함돼 있는지 확인하는 문제 

### **Idea**
* 전화번호를 String 배열에 저장하고 정렬한다.
* i가 0부터 N-1개까지 i번째 번호가 i+1번째 번호에 포함되는지 체크한다.


### **구현**
```java
Arrays.sort(nums);

boolean contain = false;
for(int i=0; i<N-1; i++) {
    String num1 = nums[i];
    String num2 = nums[i+1];
    if(num1.length() < num2.length()) {
        if(num1.equals(num2.substring(0, num1.length()))) {
            contain = true;
            break;
        }
    }
}
String ans = contain ? "NO" : "YES";
System.out.println(ans);
```

### 정리
처음에 String 배열을 정렬하고 i를 0부터 N-1까지 반복하면서 i+1부터 N까지 전부 중복되는게 없는지 확인해줬다. String 개수가 최대 10,000개였기 때문에 시간초과가 떴다. 이걸 어떻게 해결해야하나 고민하다가 String 배열을 정렬하면 {12, 13, 123, 1234, 134}가 {12, 123, 1234, 13, 134} 이렇게 정렬하는 것을 보고 힌트를 얻었다. 만약 12가 123, 1234에 두번 포함된다고 해도 12가 바로 뒤에 오는 번호에 포함되는 것만 확인하면 바로 NO가 되기 때문에 모든 번호를 확인할 필요가 없던 것이었다ㅠㅠ HashSet으로 구현해야하나 싶어서 equals, hashCode 메서드 구현하고 난리를 쳤는데 간단하게 생각하면 될 문제였다.


---
## 1. BOJ G4 2573 빙산 
### **Idea**
* 데이터를 2차원 int 배열 map에 받아 저장한다.
* 1. 시간이 1 흐를때마다 map에서 0이 아닌 데이터의 주변 값이 0인 애들 수를 세줘서 그만큼 빼준다.
* 2. bfs로 map에 빙산이 몇 개 있는지 카운팅 해준다.
* 3. 만약 빙산 개수가 1 초과이면 1~2의 반복을 그만하고 시간을 출력한다.
* 4. 만약 map의 데이터 합이 0이면 빙산이 전부 녹은 것이므로 0을 출력한다.

```java
while(sum() != 0) {
    time++;
    melting();

    int cnt = cntIsland();
    if(cnt > 1) {
        System.out.println(time);
        return;
    }
}
System.out.println(0);
```
map의 합이 0이 될때까지 반복한다. sum이 0이 될때까지 빙산이 분리되지 않은 것이므로 0 출력.

```java
// 섬의 개수를 카운팅 
static int cntIsland() {
    int cnt = 0;
    
    for(int r=1; r<R-1; r++) {
        for(int c=1; c<C-1; c++) {
            if(map[r][c] != 0 && !visited[r][c]) {
                bfs(r,c);
                cnt++;
            }
        }
    }
    for(int i=0; i<R; i++)
        Arrays.fill(visited[i], false);
    return cnt;
}

static void bfs(int r, int c) {
    Queue<int[]> q = new LinkedList<int[]>();
    q.offer(new int[] {r, c});
    visited[r][c] = true;
    
    int nr = 0, nc = 0;
    boolean noNeighbor = true;
    while(!q.isEmpty()) {
        r = q.peek()[0];
        c = q.peek()[1];
        q.poll();
        for(int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if(map[nr][nc] != 0 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
                noNeighbor = false;
            }
        }
        if(noNeighbor) {
            return;
        }
    }
}
```
boolean visited[][] 배열을 선언해줘서 아직 방문하지 않은 map이 데이터를 기록했다. bfs를 이용해 한 덩이리씩 세준다.

```java
// 빙산 깎는 메서드 
static void melting() {
    int cnt, nr, nc;
    int[][] temp = new int[R][C];
    for(int r=1; r<R-1; r++) {
        for(int c=1; c<C-1; c++) {
            if(map[r][c] != 0) {
                cnt = 0;
                for(int d=0; d<4; d++) {
                    nr = r + dr[d];
                    nc = c + dc[d];
                    if(map[nr][nc] == 0)
                        cnt++;
                }
                temp[r][c] = map[r][c] - cnt <= 0 ? 0 : map[r][c] - cnt;
            }
        }
    }
    for(int i=1; i<R-1; i++)
        System.arraycopy(temp[i], 0, map[i], 0, C);
    }
}
```
사방탐색 해서 주변이 0인 애들의 개수를 카운팅해주고 현재 빙산의 높이서 카운트만큼 빼줬다. 처음에 바로 map 배열에 넣었더니 앞에 먼저 계산한 데이터가 0이 돼버려서 뒤에서 계산할 때 오류가 났다. 그래서 temp 배열을 선언해서 거기에 계산한 정보를 담고 나중에 map 배열에 복사해줬다.


### **정리**
전에 풀었던 백준 다리만들기 문제와 비슷한 유형이어서 생각보다 쉽게 풀었다. 다만 코드가 진짜 더러워서ㅠㅠ최적화를 할 수 있을 것 같은데 엄두가 안난다..
