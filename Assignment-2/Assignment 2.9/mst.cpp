#include<bits/stdc++.h>
using namespace std;

const int MAX = 1e4 + 5;
int id[MAX], n,e;
pair <long long, pair<int, int> > p[MAX];

struct graph{
int row;
int col;
int data;
};
graph g[1000];

bool comp(struct graph i,struct graph j) { return (i.data<j.data); }

void initialize()
{
    for(int i = 0;i < MAX;++i)
        id[i] = i;
}

int root(int x)
{
    while(id[x] != x)
    {
        id[x] = id[id[x]];
        x = id[x];
    }
    return x;
}

void union1(int x, int y)
{
    int p = root(x);
    int q = root(y);
    id[p] = id[q];
}


int kruskal()
{
    int x, y;
    int cost, minimumCost = 0;
    for(int i = 0;i < e;i++)
    {
        // Selecting edges one by one in increasing order from the beginning
        x = g[i].row;
        y = g[i].col;
        cost = g[i].data;
        cout<<cost<<"\n";
        // Check if the selected edge is creating a cycle or not
        if(root(x) != root(y))
        {
            minimumCost += cost;
            union1(x, y);
        }
    }
    cout<<minimumCost;
    return minimumCost;
}


int main(){
initialize();
int n;
cout<<"Enter no of nodes"<<endl;
cin>>n;
cout<<"Enter no of edges"<<endl;
//int e;
cin>>e;
//struct graph g[e+1];
g[0].row=0;g[0].col=0;g[0].data=999999;
cout<<"Enter row col data"<<endl;
for(int i=1;i<=e;i++){
    cin>>g[i].row>>g[i].col>>g[i].data;
}
sort(g+1,g+e+1,comp);

for(int i=1;i<=e;i++){
    cout<<g[i].row<<" "<<g[i].col<<" "<<g[i].data<<endl;
}
int a =kruskal();
cout<<"The weight of minimum spanning tree is "<<a;
}
