class CSR:
    def __init__(self):
        self.n = 0
        self.IA = []
        self.JA = []

    def __len__(self):
        return self.n

    def __str__(self):
        return self.IA.__str__() + '\n' + self.JA.__str__()

    def create(self, g):
        self.n = len(g)
        self.IA = [0]

        for i in range(len(g)):
            k = 0

            for j in range(len(g)):
                if g[i][j] != 0:
                    k += 1
                    self.JA.append(j)

            self.IA.append(self.IA[i] + k)

    def edges_from(self, v):
        nodes = []

        for i in range(self.IA[v], self.IA[v + 1]):
            nodes.append(self.JA[i])

        return nodes
