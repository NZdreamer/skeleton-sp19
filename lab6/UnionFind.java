public class UnionFind {

    // TODO - Add instance variables?
    private int[] disjointSet;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        disjointSet = new int[n];
        for (int i : disjointSet) {
            disjointSet[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < -1 || vertex >= n) {
            throw new IllegalArgumentException("v1 is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        int num = 0;
        for (int i : disjointSet) {
            if (find(i) == root) {
                num++;
            }
        }
        return num;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if (disjointSet[v1] == -1) {
            return -1 * sizeOf(v1);
        }
        return disjointSet[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (sizeOf(v1) > sizeOf(v2)) {
            disjointSet[v2] = find[v1];
        }
        else {
            disjointSet[v1] = find[v2];
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (disjointSet[vertex] = -1) {
            return vertex;
        }
        find(disjointSet(vertex));
    }

}
