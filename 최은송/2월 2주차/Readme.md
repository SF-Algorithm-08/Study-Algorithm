## 2월 2주차 정리
---
## 1. BOJ S2 2841 외계인의 기타 연주
### **Idea**
* 줄별로 프랫을 저장할 수 있는 stack을 N개의 배열로 만든다
* 현재 줄에 프랫이 없을 때 push, cnt++
* 이전 프랫 > 현재 프랫. 현재 프랫보다 이전 프랫이 같거나 작아질때까지 stack에서 pop. cnt++
* 이전 프랫 < 현재 프랫. push, cnt++
* 이전 프랫 == 현재 프랫. 같으면 누를 필요가 없으므로 continue

### **구현**
<pre>
<code>
Stack<Integer>[] line = new Stack[7];
for(int i=1; i<7; i++)
    line[i] = new Stack<Integer>();
    ...
for(int i = 0; i < N; i++) {
    String[] play = in.readLine().trim().split(" ");
    int ln = Integer.parseInt(play[0]);
    int prat = Integer.parseInt(play[1]);
    
    // 멜로디가 비었을 때 
    if(line[ln].isEmpty()) {
        cnt++;
        line[ln].push(prat);
        
    }else {
        // 이전 프랫 > 현재 프랫
        while(!line[ln].isEmpty() && line[ln].peek() > prat) {
            cnt++;
            line[ln].pop();
        }
        // 이전 프랫 < 현재 프랫. 4 5 7 4 에서 5,7을 pop했을 때 peek()이 현재 프랫과 같을 수 있음. 그러면 cnt++ 하면 안됨. 그렇기 때문에 if로 조건을 걸어줘야 함 
        if(line[ln].isEmpty() || line[ln].peek() < prat) {
            line[ln].push(prat);
            cnt++;
        }
        // 이전프랫 == 현재 프랫
        if(line[ln].peek() == prat)	continue;
    }
}
</code>
</pre>

### **회고**
> 처음에 문제를 읽고 이해하는게 너무 오래 걸렸다. 어떨때 cnt가 증가하는지 이해하는게 한참 걸려서 힘들었다ㅠㅠ
그리고 이전 프랫보다 현재 프랫이 작으면 pop하고 바로 현재 프랫을 push 했는데 그러니까 오류가 났다. 왜 오류가 났는지 한참 찾았는데 만약 2라는 줄에 프랫이 4 5 7 4 이렇게 쌓여있고 5,7을 pop하면 이전 프랫인 4와 현재 프랫 4가 동일하기 때문에 cnt를 올려주면 안됐다. 


-------

## 2. BOJ S1 2447 별찍기
### **Idea**
* 배열의 사이즈인 N은 3의 거듭제곱이므로 배열을 9개의 블록으로 나눌 수 있다. 블록을 9개로 나눌 때 가운데를 비우고 나머지 부분을 찍어주는 방식은 동일하므로 재귀적으로 풀 수 있다.
* 재귀의 기저조건을 N = 1일때로 설정한다. N=1이면 더이상 쪼갤 수 없으므로 *을 찍어준다.
* 블록을 9개로 나누어 각 블록의 처음 행, 열 좌표와 N/3을 인자로 재귀함수를 호출한다.
* 함수를 호출할 때 가운데 블록은 제외한다.
> 출력할 때 System.out.print로 했다가 시간초과가 났다. StringBuilder로 append해서 출력하니 통과했다. N 최댓값이 3^7 = 2187이기 때문에 2187 * 2187 = 4,782,969 이기 때문에 그런듯

### **구현**
<pre>
<code>
private static void paint(int n, int r, int c) {
    if(n == 1) {
        map[r][c] = '*';
        return;
    }
    int n3 = n / 3;
    for(int i=0; i<3; i++) {
        for(int j=0; j<3; j++) {
            // 가운데 블록은 비워야 하기 때문에 continue한다.
            if(i == 1 && j == 1)	continue;
            paint(n3, r + i*n3, c + j*n3);
        }
    }
}
</pre>
</code>

### **회고**
> 재귀를 사용할 때 플랫하게 생각하는 법을 더 연구해야겠다. 또한 문제를 풀 때 입출력 사이즈를 보고 어떤 함수를 사용해야 할지도 항상 생각해야겠다.

---

## BOJ S5 1018 체스판 칠하기
### **Idea**
* White로 시작하는 배열과, Black으로 시작하는 8X8 배열을 만든다
* 입력받은 배열을 첫번째칸부터 8x8씩 이동하며 White 배열과 Black 배열을 비교해서 다시 칠해야하는 칸을 체크한다.
* 매번 8x8 비교할때마다 다시칠해야 할 칸의 최솟값을 갱신한다.


### **구현**
<pre>
<code>
static char[][] w_board = {
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'}
};

static char[][] b_board = {
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'},
        {'B','W','B','W','B','W','B','W'},
        {'W','B','W','B','W','B','W','B'}
};
int min = Integer.MAX_VALUE;
for(int i=0; i+8 <= N; i++) {
    for(int j=0; j+8 <= M; j++) {
        
        int wcnt = 0, bcnt = 0;
        //흰색으로 시작할 때 
        for(int r = i; r < i+8; r++) {
            for(int c = j; c < j+8; c++) {
                if(map[r][c] != w_board[r-i][c-j])
                    wcnt++;
            }
        }
        
        //검은색으로 시작할 때
        for(int r = i; r < i+8; r++) {
            for(int c = j; c < j+8; c++) {
                if(map[r][c] != b_board[r-i][c-j])
                    bcnt++;
            }
        }
        int wbmin = Math.min(wcnt, bcnt);
        min = Math.min(min, wbmin);
    }
}
</pre>
</code>

### **회고**
> 좀더 수학적이고 깔끔하게 리팩토링 할 수 있을 것 같다. 겹치는 코드가 너무 많아서ㅠㅠ 