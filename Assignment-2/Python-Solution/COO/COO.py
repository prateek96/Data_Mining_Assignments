class COO:
    def __init__(self):
        self.graph = []
        self.n = 0

    def __len__(self):
        return self.n

    def __str__(self):
        return self.graph.__str__()

    def create(self, g):
        """
        Create a graph in COO format from given Adjacency matrix.

        :param g: The graph in adjacency matrix format
        :return: None
        """

        self.n = len(g)

        for i in range(len(g)):
            for j in range(len(g)):
                if g[i][j] != 0:
                    self.graph.append((i, j))

    def edges_from(self, v):
        """
        Generate a list of nodes reachable from vertex v.

        :param v: Vertex
        :return: List of nodes
        """

        nodes = []

        for c in self.graph:
            if c[0] == v:
                nodes.append(c[1])

        return nodes

    def edges_to(self, v):
        """
        Generate a list of nodes from which vertex v is reachable.

        :param v: Vertex
        :return: List of nodes
        """

        nodes = []

        for c in self.graph:
            if c[1] == v:
                nodes.append(c[0])

        return nodes
