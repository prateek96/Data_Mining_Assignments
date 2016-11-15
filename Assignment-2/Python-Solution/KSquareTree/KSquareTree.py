from BitVector import BitVector
import math as m


class KSquareTree:
    def __init__(self, k):
        self.k = k
        self.k2 = k * k
        self.n = 0
        self.h = 0
        self.T = BitVector(size=0)
        self.L = BitVector(size=0)

    def __str__(self):
        return self.T.__str__() + '\n' + self.L.__str__()

    def __len__(self):
        return self.n

    def create(self, graph):
        """
        Creates a k^2 tree graph representation from the given graph.

        :param graph: An adjacency matrix representation of the graph.
                      It is assumed that len(graph) % k == 0.
        :return: None
        """

        assert len(graph) % self.k == 0

        self.n = len(graph)
        self.h = int(m.ceil(m.log(len(graph), self.k)))

        temp = [BitVector(size=0) for _ in range(self.h)]

        def build(n, l, p, q):
            c = BitVector(size=0)
            for i in range(self.k):
                for j in range(self.k):
                    if l == self.h - 1:
                        c = c + BitVector(intVal=graph[p + i][q + j])
                    else:
                        c = c + build(n / self.k,
                                      l + 1,
                                      p + i * (n / self.k),
                                      q + j * (n / self.k))

            if c == BitVector(size=self.k2):
                return BitVector(intVal=0)

            temp[l] += c
            return BitVector(intVal=1)

        build(self.n, 0, 0, 0)

        self.L = temp[self.h - 1]

        for i in range(self.h - 1):
            self.T += temp[i]

    def edges_from(self, p):
        """
        Returns the list of edges which are neighbours of p.

        :param p: Vertex
        :return: Vertices which are reachable from p
        """
        nodes = []

        def direct(n, p, q, x):
            if x >= len(self.T):
                if self.L[x - len(self.T)] == 1:
                    nodes.append(q)
            else:
                if x == -1 or self.T[x] == 1:
                    y = self.T.rank_of_bit_set_at_index(x) * self.k2 \
                        + self.k * int(m.floor(p / (n / self.k)))

                    for j in range(self.k):
                        direct(n / self.k,
                               p % (n / self.k),
                               q + (n / self.k) * j,
                               y + j)

        direct(self.n, p, 0, -1)

        return nodes

    def edges_to(self, q):
        """
        Returns the list of vertices from which q is reachable.

        :param q: Vertex
        :return: Vertices from which q can be reached
        """
        nodes = []

        def reverse(n, q, p, x):
            if x >= len(self.T):
                if self.L[x - len(self.T)] == 1:
                    nodes.append(p)
            else:
                if x == -1 or self.T[x] == 1:
                    y = self.T.rank_of_bit_set_at_index(x) * self.k2 \
                        + self.k * int(m.floor(q / (n / self.k)))

                    for j in range(self.k):
                        reverse(n / self.k,
                               q % (n / self.k),
                               p + (n / self.k) * j,
                               y + j * self.k)

        reverse(self.n, q, 0, -1)

        return nodes
