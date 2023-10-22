//import java.util.*;
//class KruskalAlgorithm {
//    class Edge implements Comparable<Edge> {
//        int source, destination, weight;
//        public int compareTo(Edge edgeToCompare) {
//            return this.weight - edgeToCompare.weight;}}
//    // create class Subset for union
//    class Subset {
//        int parent, value;}
//    int vertices, edges;
//    Edge edgeArray[];
//    // using constructor to create a graph
//    KruskalAlgorithm(int vertices, int edges) {
//        this.vertices = vertices;
//        this.edges = edges;
//        edgeArray = new Edge[this.edges];
//        for (int i = 0; i < edges; ++i)
//            edgeArray[i] = new Edge();}
//    void applyKruskal() {
//        Edge finalResult[] = new Edge[vertices];
//        int newEdge = 0;
//        int index = 0;
//        for (index = 0; index < vertices; ++index)
//            finalResult[index] = new Edge();
//        Arrays.sort(edgeArray);
//        // create an array of the vertices of type Subset for the subsets of vertices
//        Subset subsetArray[] = new Subset[vertices];
//        // aloocate memory to create vertices subsets
//        for (index = 0; index < vertices; ++index)
//            subsetArray[index] = new Subset();
//        // it is used to create subset with single element
//        for (int vertex = 0; vertex < vertices; ++vertex) {
//            subsetArray[vertex].parent = vertex;
//            subsetArray[vertex].value = 0;}
//        index = 0;
//        while (newEdge < vertices - 1) {
//            Edge nextEdge = new Edge();
//            nextEdge = edgeArray[index++];
//
//            int nextSource = findSetOfElement(subsetArray, nextEdge.source);
//            int nextDestination = findSetOfElement(subsetArray, nextEdge.destination);
//
//            //if the edge doesn't create cycle after including it, we add it in the result and increment the index
//            if (nextSource != nextDestination) {
//                finalResult[newEdge++] = nextEdge;
//                performUnion(subsetArray, nextSource, nextDestination);}}
//        for (index = 0; index < newEdge; ++index)
//            System.out.println(finalResult[index].source + " - " + finalResult[index].destination + ": " + finalResult[index].weight);}
//
//    // create findSetOfElement() method to get set of an element
//    int findSetOfElement(Subset subsetArray[], int i) {
//        if (subsetArray[i].parent != i)
//            subsetArray[i].parent = findSetOfElement(subsetArray, subsetArray[i].parent);
//        return subsetArray[i].parent;
//    }
//
//    // create performUnion() method to perform union of two sets
//    void performUnion(Subset subsetArray[], int sourceRoot, int destinationRoot) {
//
//        int nextSourceRoot = findSetOfElement(subsetArray, sourceRoot);
//        int nextDestinationRoot = findSetOfElement(subsetArray, destinationRoot);
//
//        if (subsetArray[nextSourceRoot].value < subsetArray[nextDestinationRoot].value)
//            subsetArray[nextSourceRoot].parent = nextDestinationRoot;
//        else if (subsetArray[nextSourceRoot].value > subsetArray[nextDestinationRoot].value)
//            subsetArray[nextDestinationRoot].parent = nextSourceRoot;
//        else {
//            subsetArray[nextDestinationRoot].parent = nextSourceRoot;
//            subsetArray[nextSourceRoot].value++;}}
//    public static void main(String[] args) {
//        int v, e;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter number of vertices: ");
//        v = sc.nextInt();
//        System.out.println("Enter number of edges");
//        e = sc.nextInt();
//        KruskalAlgorithm g = new KruskalAlgorithm(v, e);
//        for(int i = 0; i < e; i++){
//            System.out.println("Enter source value for edge["+ i +"]");
//            g.edgeArray[i].source = sc.nextInt();
//
//            System.out.println("Enter destination value for edge["+ i +"]");
//            g.edgeArray[i].destination = sc.nextInt();
//
//            System.out.println("Enter weight for edge["+i+"]");
//            g.edgeArray[i].weight = sc.nextInt();
//        }
//        g.applyKruskal();}}

import java.util.*;
public class Graphe{
    static class DSU{
        private int parent[];
        private int rank[];
        DSU(int n){
            parent=new int[n];
            rank=new int[n];
            for(int i=0;i<n;i++) {
                parent[i]=i;
                rank[i]=0;}}
        public int find(int node){
            if(node==parent[node]) return node;
            return parent[node]=find(parent[node]);}
        public void union(int u,int v){
            u=find(u);// Make u,v as a leader of its tree.
            v=find(v);
            // If u and v are not equal,because if they are equal then
            // it means they are already in same tree and it does not make
            // sense to perform union operation.
            if(u!=v) {
                // Checking tree with smaller depth/height.
                if(rank[u]<rank[v]) {
                    int temp=u;
                    u=v;
                    v=temp;}
                // Attaching lower rank tree to the higher one.
                parent[v]=u;
                // If now ranks are equal increasing rank of u.
                if(rank[u]==rank[v])
                    rank[u]++;}}
    }
    static class Edge{
        int u,v,weight;
        Edge(int u,int v,int weight){
            this.u=u;
            this.v=v;
            this.weight=weight;}}
    static int V, E;
    static List<Edge> edges;
    Graphe(int V,int E){
        this.V=V;
        this.E=E;
        edges=new ArrayList<>();}
    static void MST_Kruskal(){
        int e=0,i=0,sum=0;
        DSU dsu=new DSU(V);

        Collections.sort(edges,
                new Comparator<Edge>(){
                    public int compare(Edge e1,Edge e2){
                        return e1.weight-e2.weight;}});
        while(e<V-1) {
            Edge edge=edges.get(i++);
            int u=dsu.find(edge.u);
            int v=dsu.find(edge.v);
            // Checking if adding edge with endpoints u and v form a cycle.If not
            if(u!=v) {
                System.out.println("Adding edge "+ edge.u +" <---> "+ edge.v +" to MST");
                sum+=edge.weight;
                // Including the edge.
                dsu.union(u,v);
                e++;}}
        System.out.println("MST has a weight of "+sum);}
    static void addEdge(int u,int v,int weight){
        edges.add(new Edge(u,v,weight));}
    public static void main(String args[]){
        Graphe g=new Graphe(6,9);
        g.addEdge(0,1,7);
        g.addEdge(0,2,8);
        g.addEdge(1,2,3);
        g.addEdge(1,4,6);
        g.addEdge(2,3,3);
        g.addEdge(2,4,4);
        g.addEdge(3,4,2);
        g.addEdge(3,5,2);
        g.addEdge(4,5,2);
        g.MST_Kruskal();}}


