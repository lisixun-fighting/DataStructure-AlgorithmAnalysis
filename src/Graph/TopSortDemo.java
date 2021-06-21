package Graph;

import java.util.*;

/**
 * Course Schedule Problem
 */
public class TopSortDemo {
    private static class Vertex {
        public int indegree;
        public Set<Vertex> adjacent = new HashSet<>();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // create adjacency table
        Vertex[] vertices = new Vertex[numCourses];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex();
        }
        for (int[] prerequisite : prerequisites) {
            Vertex v = vertices[prerequisite[1]];
            Vertex u = vertices[prerequisite[0]];
            v.adjacent.add(u);
            u.indegree++;
        }
        return topsort(vertices);
    }

    public boolean topsort(Vertex[] vertexList) {
        Queue<Vertex> q = new LinkedList<>();
        int counter = 0;
        for (Vertex v : vertexList) {
            if (v.indegree == 0) {
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            counter++;
            for (Vertex w : v.adjacent) {
                if (--w.indegree == 0) {
                    q.add(w);
                }
            }
        }
        return  (counter == vertexList.length);
    }
}
