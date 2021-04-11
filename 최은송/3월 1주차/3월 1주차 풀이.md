## 3월 1주차 풀이
---
## 1. BOJ S1 1074 Z
### **문제설명**
* 2^N * 2^N 인 정사각형을 2^N-1 * 2^N-1로 4등분해서 Z 모양으로 탐색할 때 r행 c열을 몇번째로 방문하는지 구하라

### **Idea**
* 시간제한이 0.5초이고 최대 배열 사이즈가 2^15이기 때문에 완탐으로 풀 수 없다
* 1사분면, 2사분면, 3사분면, 4사분면으로 나눠서 r행 c열이 어디에 속해있는지 확인하고 cnt를 올려준다.
* 배열의 사이즈가 n일때 (r, c)가 2사분면이면 cnt를 n/2 * n/2만큼 더해준다.
* 배열 사이즈가 1이 되면 (r, c)를 찾았으므로 cnt를 출력하고 끝낸다.



### **구현**
```java
int cnt = 0, r = 0, c = 0, n = 1 << N;		
while(n > 0){
    n = n >> 1;
    //2사분면 
    if(R < r+n && C < c+n) {
        cnt += n * n * 0;
    }
    // 1사분면 
    else if(R < r + n) {
        cnt += n * n;
        c += n;
    }
    // 3사분면
    else if( C < c+n) {
        cnt += n * n * 2;
        r += n;
    }
    // 4사분면
    else {
        cnt += n * n * 3;
        r += n;
        c += n;
    }	
    
    if(n == 1) {
        System.out.println(cnt);
        break;
    }
}	
```

### 정리
처음에 입력값과 시간제한을 보지 않고 완탐으로 풀었다가 시간 초과가 났다. 다시한번 문제를 꼼꼼히 읽어야겠다 싶었다. 그리고 if, else if 조건문을 `else if(R < r+n && C >= r+n)`  이렇게 하니 틀렸었다. 이것때문에 한참을 헤맸는데 이유를 알수없어서 찜찜하다🤔

---
## 1. BOJ ㅎ3 1377 버블소트 
### **Idea**
* 버블소트로 정렬했을 때 i를 출력하는 문제다.
* i는 버블소트 라운드 횟수 + 1 이다.
* 버블소트 한 라운드마다 숫자는 한칸밖에 이동하지 못한다. 
* 처음 입력받을 때 숫자 인덱스와, 정렬 후의 숫자 인덱스의 차이가 가장 큰 값 + 1 을 하면 i가 나온다.

### **구현**
```java
int[][] arr = new int[N][2];

//arr[][0] : 수, arr[][1]: 인덱스
for(int i=0; i<N; i++) {
    arr[i][0] = Integer.parseInt(in.readLine());
    arr[i][1] = i;
}
// 입력받은 수를 기준으로 정렬 
Arrays.sort(arr, new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
    }
});

int cnt = 0;
for(int i=0; i<N; i++) {
    if(arr[i][1] - i > cnt)
        cnt = arr[i][1] - i;
}
```

### 정리
처음에 그냥 문제에 나와있는 코드 쓰면 되는거 아닌가 싶어서 복붙했는데 시간초과 나왔다. 버블소트의 정의만 잘 알면 나름 풀기 쉬웠던 문제였다. 


---
## 1. BOJ S3 14425 문자열 집합 
### **Idea**
* hashset으로 N개의 문자열을 입력받는다
* M개의 문자열을 입력받으며 hashset에 있는지 확인해준다.

### **구현**
```java
HashSet<String> n = new HashSet<String>();

for(int i=0; i<N; i++) {
    n.add(in.readLine());
}

int cnt = 0; String word;

for(int i=0; i<M; i++) {
    word = in.readLine();
    if(n.contains(word)) {
        cnt++;
    }
}
```

### 정리
처음에 hashSet에 문자열이 있으면 hashSet에서 지웠었더니 오답이 나왔다. N개의 문자열은 중복이 없지만 검사할 M개의 문자열에는 중복이 없다는 말이 없었기 때문에 지우면 안됐었다. 다시한번 문제를 꼼꼼히 읽어야겠다..