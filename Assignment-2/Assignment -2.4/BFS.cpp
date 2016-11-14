//BFS CSR FORMAT
#include<iostream>
#include <list>

using namespace std;

class Graph
{
    int V;
    list<pair<int,int> > *adj;
public:
    Graph(int V);
    void addEdge(int v, int w, int c);
    void BFS(int s);
};

Graph::Graph(int V)
{
    this->V = V;
    adj = new list<pair<int,int> >[V];
}

void Graph::addEdge(int v, int w,int c)
{
    adj[v].push_back(make_pair(w,c));
}

void Graph::BFS(int s)
{
    bool *visited = new bool[V];
    for(int i = 0; i < V; i++)
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

        for(i = adj[s].begin(); i != adj[s].end(); ++i)
        {
            if(!visited[(*i).first])
            {
                visited[(*i).first] = true;
                queue.push_back((*i).first);
            }
        }
    }
}

/*
4
1 2 3 0
4
0 1 3 4
4
100 200 300 211
*/

int main()
{
    int n1, n2, n3;
    cin>>n1;
    int A1[n1];
    Graph g(1<<21);
    for(int i=0;i<n1;i++) {
        cin>>A1[i];
    }
    cin>>n2;
    int A2[n2];
    for(int i=0;i<n2;i++) {
        cin>>A2[i];
    }
    cin>>n3;
    int A3[n3];
    for(int i=0;i<n3;i++) {
        cin>>A3[i];
    }

    for(int i=1;i<n2;i++) {
        for(int j = A2[i-1];j < A2[i];j++) {
            g.addEdge(i-1,A1[j],A3[j]);
        }
    }



    cout << "Following is Breadth First Traversal "
         << "(starting from vertex 1) \n";
    g.BFS(1);

    return 0;
}
