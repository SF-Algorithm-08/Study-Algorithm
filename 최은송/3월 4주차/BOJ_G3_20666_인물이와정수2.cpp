#include <iostream>
#include <stdio.h>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, p;
vector< pair<int, long long> > monster;
vector< pair<int, long long> > guide[100001];
bool visit[100001];
long long answer;

struct compare{
    bool operator()(pair<int,long long> a, pair<int, long long> b){
        return a.second > b.second;
    }
};

void solve(){
    priority_queue< pair<int, long long>, vector< pair<int, long long> >, compare>pq;
    for(int i=1; i<=N; i++)
        pq.push(make_pair(monster[i].first, monster[i].second));


    int cnt = 0;
    while(cnt < M){
        int cnum = pq.top().first;
        long long clevel = pq.top().second;
        pq.pop();

        if(visit[cnum]) continue;
        visit[cnum] = true;
        answer = max(clevel, answer);
        cnt++;

        // printf("cnum: %d\n",cnum);
        if(p != 0){
            int len = guide[cnum].size();
            // printf("len; %d\n",len);
            if(len != 0){
                for(int j=0; j<len; j++){
                    int nnum = guide[cnum][j].first;
                    if(!visit[nnum]){
                        monster[nnum].second -= guide[cnum][j].second;
                        long long nlevel = monster[nnum].second;
                        pq.push(make_pair(nnum, nlevel));
                    }
                }
            }
        }
    }
}

void input(){
    cin >> N >> M;

    int level = 0;
    monster.push_back(make_pair(0,0));
    for(int i=1; i<=N; i++){
        cin >> level;
        monster.push_back(make_pair(i, level));
    }
    cin >> p;
    int item, mon;
    long long t;
    if(p != 0){
        for(int i=0; i<p; i++){
            cin >> item >> mon >> t;
            guide[item].push_back(make_pair(mon, t));
            monster[mon].second += t;
        }
    }
    // for(int i=1; i<=N; i++)
    //     printf("monster: %d %lld\n", monster[i].first, monster[i].second);
}

int main(){
    input();
    solve();
    cout << answer << endl;
}