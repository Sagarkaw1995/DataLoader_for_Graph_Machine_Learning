# DataLoader_for_Graph_Machine_Learning



from dataclasses import dataclass

'''

#We want to write a very fast Graph Machine Learning algorithm. The model is computing embeddings based on the aggregation of neighbours embeddings. 

'''


@dataclass

class Vertex:

    vertexId: int

    feat: float

    label: int

    neighbours: list[object]    


'''

#You're writing the dataloader of a graph machine learning model. The dataloader is given a list of Vertex [v1, v3, v4].

#The batch loader need to return a list of lists of Vertex, with the form (for hop=2, num_sampled_neighbours=2):

[[v1, v3, v4],

[v11, v12, v31, v32, v41, v42],

[v111, v112, v121, v122, v311, v312, v321, v322, v411, v412, v421, v422]]
