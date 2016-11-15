def bfs(graph, source, target):
    """
    Breadth first search on the graph.

    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :param source: Source vertex
    :param target: Target vertex
    :return: Whether target was reachable from source
    """

    assert source < len(graph) and target < len(graph)

    curr = [source]
    opened = [source]

    while len(curr) > 0:
        v = curr.pop(0)

        if v == target:
            return True
        else:
            for x in graph.edges_from(v):
                if x not in opened:
                    curr.append(x)
                    opened.append(x)

    return False


def find_cycle(graph):
    """
    Find cycles on the graph.

    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :return: Whether graph is cyclic
    """

    opened = [0]

    def dfs(src):
        for v in graph.edges_from(src):
            if v not in opened:
                opened.append(v)
                dfs(v)
            elif v != src:
                return True

        return False

    return dfs(0)


def bron_kerbosch(graph):
    """
    Bron Kerbosch algorithm to find all the maximal cliques in a graph.

    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :return: The list of maximal cliques.
    """

    cliques = []
    stack = []
    nd = None
    disc_num = len(graph)
    search_node = (set(), set([i for i in range(len(graph))]), set(), nd, disc_num)
    stack.append(search_node)

    while len(stack) > 0:
        (c_compsub, c_candidates, c_not, c_nd, c_disc_num) = stack.pop()

        if len(c_candidates) == 0 and len(c_not) == 0:
            if len(c_compsub) > 2:
                cliques.append(c_compsub)
                continue

        for u in list(c_candidates):
            if (c_nd is None) or (c_nd not in graph.edges_from(u)):
                c_candidates.remove(u)
                nu = graph.edges_from(u)
                new_compsub = set(c_compsub)
                new_compsub.add(u)
                new_candidates = set(c_candidates.intersection(nu))
                new_not = set(c_not.intersection(nu))

                if c_nd is not None:
                    if c_nd in new_not:
                        new_disc_num = c_disc_num - 1
                        if new_disc_num > 0:
                            new_search_node = (new_compsub, new_candidates, new_not, c_nd, new_disc_num)
                            stack.append(new_search_node)

                    else:
                        new_disc_num = len(graph)
                        new_nd = c_nd

                        for cand_nd in new_not:
                            cand_disc_num = len(new_candidates) - len(
                                new_candidates.intersection(graph.edges_from(cand_nd)))

                            if cand_disc_num < new_disc_num:
                                new_disc_num = cand_disc_num
                                new_nd = cand_nd

                        new_search_node = (new_compsub, new_candidates, new_not, new_nd, new_disc_num)
                        stack.append(new_search_node)
                else:
                    new_search_node = (new_compsub, new_candidates, new_not, c_nd, c_disc_num)
                    stack.append(new_search_node)

                c_not.add(u)
                new_disc_num = 0

                for x in c_candidates:
                    if u not in graph.edges_from(x):
                        new_disc_num += 1

                if 0 < new_disc_num < c_disc_num:
                    new1_search_node = (c_compsub, c_candidates, c_not, u, new_disc_num)
                    stack.append(new1_search_node)
                else:
                    new1_search_node = (c_compsub, c_candidates, c_not, c_nd, c_disc_num)
                    stack.append(new1_search_node)

    return [list(c) for c in cliques]


def mst(graph):
    """
    Find the minimum spanning tree of the graph.
    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :return: Minimum spanning tree
    """
    minst = [[0 for _ in range(len(graph))] for _ in range(len(graph))]

    for v in range(len(graph)):
        for x in graph.edges_from(v):
            minst[v][x] = 1

    mst = set()
    x = set()

    x.add(0)

    while len(x) != len(graph):
        crossing = set()

        for o in x:
            for k in range(len(graph)):
                if k not in x and minst[o][k] != 0:
                    crossing.add((o, k))

        edge = sorted(crossing, key=lambda e: minst[e[0]][e[1]])[0]

        mst.add(edge)
        x.add(edge[1])

    return list(mst)


def shortest_path(graph, src):
    """
    Find the length of the shortest path from source to all vertices.

    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :param src: Source vertex
    :return: Lengths of all shortest paths and the paths themselves
    """
    visited = {src: 0}
    path = {}

    nodes = set([i for i in range(len(graph))])

    while nodes:
        min_node = None
        for node in nodes:
            if node in visited:
                if min_node is None:
                    min_node = node
                elif visited[node] < visited[min_node]:
                    min_node = node

        if min_node is None:
            break

        nodes.remove(min_node)
        current_weight = visited[min_node]

        for edge in graph.edges_from(min_node):
            weight = current_weight + (edge in graph.edges_from(min_node))
            if edge not in visited or weight < visited[edge]:
                visited[edge] = weight
                path[edge] = min_node

    return visited, path


def betweeness_centrality(graph):
    """
    Calculate the betweeness centrality of vertices using Brande's algorithm.
    Algorithm found here: http://algo.uni-konstanz.de/publications/b-fabc-01.pdf

    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :return: The betweeness centrality value of all nodes
    """
    C = [0 for _ in range(len(graph))]

    for s in range(len(graph)):
        S = []                                   # Empty stack (pop(-1))
        P = [[] for _ in range(len(graph))]
        sigma = [0.0 for _ in range(len(graph))]
        sigma[s] = 1.0
        d = [-1.0 for _ in range(len(graph))]
        Q = [s]                                  # Queue (pop(0))

        while len(Q) > 0:
            v = Q.pop(0)
            S.append(v)

            for w in graph.edges_from(v):
                if d[w] < 0:
                    Q.append(w)
                    d[w] = d[v] + 1

                if d[w] == d[v] + 1:
                    sigma[w] += sigma[v]
                    P[w].append(v)

        delta = [0 for _ in range(len(graph))]

        while len(S) > 0:
            w = S.pop(-1)

            for v in P[w]:
                delta[v] += (sigma[v] / sigma[w]) * (1 + delta[w])

            if w != s:
                C[w] += delta[w]

    return C


def global_cluster_coeff(graph):
    """
    Calculate the global cluster coefficient of the graph.

    :param graph: A graph which supports the methods edges_from(v) and edges_to(v)
    :return: The global cluster coefficient
    """

    def clustering_coefficient(g, v):
        neighbors = g.edges_from(v)

        if len(neighbors) == 1:
            return -1.0

        links = 0

        for w in neighbors:
            for u in neighbors:
                if u in g.edges_from(w):
                    links += 0.5

        return 2.0 * links / (len(neighbors) * (len(neighbors) - 1))

    total = 0.0

    for v in range(len(graph)):
        total += clustering_coefficient(graph, v)

    return total / len(graph)
