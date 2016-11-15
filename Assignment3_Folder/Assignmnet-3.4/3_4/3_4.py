def delta_Q(component_1, component_2):
    
    flag = False
    
    for i in component_1:
        for j in component_2:
            if(adj_mat[i][j] == 1):
                flag = True
                break
    
    if(flag == False):
        return 0

    Eij = 0.00
    
    for i in range(vertices):
        for j in range(vertices):
            if(adj_mat[i][j] == 1 and ((i in component_1 and j in component_2) or (i in component_2 and j in component_1))):
                Eij += 0.5

    Eij = Eij/edges
    
    Ai = 0.00
    
    for i in range(vertices):
        if(i in component_1):
            continue
        for j in range(vertices):
            if(adj_mat[i][j] == 1 and j in component_1):
                Ai += 0.5
    Ai = Ai/edges

    Aj = 0.00
    
    for i in range(vertices):
        if(i in component_2):
            continue
        for j in range(vertices):
            if(adj_mat[i][j] == 1 and j in component_2):
                Aj += 0.5
    Aj = Aj/edges
    
    return (2*(Eij - Ai*Aj))

vertices = int(input())
edges = int(input())

components = [[i] for i in range(vertices)]
adj_mat = [[0 for i in range(vertices)] for i in range(vertices)]

for i in range(edges):
    u,v = [int(j) for j in input().split()]
    adj_mat[u][v] = 1
    adj_mat[v][u] = 1
    
while(1):
    maximum = 0.00
    u,v = -1,-1
    
    for i in range(len(components)):
        for j in range(i+1, len(components)):
            if(delta_Q(components[i], components[j]) > maximum):
                maximum = delta_Q(components[i], components[j])
                u,v = i,j
                
    if(maximum == 0.00):
        break
    
    components.append(components[u] + components[v])
    del(components[u])
    del(components[v-1])
    
print(components)