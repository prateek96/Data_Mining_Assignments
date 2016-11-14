//BFS COO FORMAT
#include<iostream>
#include <list>

using namespace std;

struct graph{
    int row;
    int col;
    int data;
};
int n,e;


graph g[1000];


void BFS(int s)
{
    bool *visited = new bool[n];
    for(int i = 0; i < n; i++)
        visited[i] = false;

    list<int> queue;

    visited[s] = true;
    queue.push_back(s);

    list<pair<int,int > >::iterator i;

    while(!queue.empty())
    {
        s = queue.front();
        cout << s << " ";
        queue.pop_front();

        for(int i=0;i<e;i++){
            if(g[i].row==s){
                if(!visited[g[i].col])
                {
                    visited[g[i].col] = true;
                    queue.push_back(g[i].col);
                }
            }
        }


    }
}

/*
4
4
0 1 100
1 3 200
1 2 100
2 0 200
*/

int main(){


    cout<<"Enter no. nodes"<<endl;
    cin>>n;
    cout<<"Enter no. edges"<<endl;
    cin>>e;

    cout<<"Enter row col data"<<endl;

    for(int i=0;i<e;i++){
        cin>>g[i].row>>g[i].col>>g[i].data;
    }

    for(int i=0;i<e;i++){
        cout<<g[i].row<<" "<<g[i].col<<" "<<g[i].data<<endl;
    }

    cout << "Following is Breadth First Traversal "
         << "(starting from vertex 1) \n";
    BFS(1);

    return 0;
}
