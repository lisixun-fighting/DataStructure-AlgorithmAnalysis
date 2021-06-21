package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class UnweightedPathDemo {
    private static class Vertex {
        public int dist;
        public Vertex path;
        public Set<Vertex> adjacent = new HashSet<>();
    }

    private static final int INF = -1;

    public void unweighted(Vertex s, Vertex[] vertices) {
        Queue<Vertex> q = new LinkedList<>();
        for (Vertex v : vertices) {
            v.dist = INF;
        }
        s.dist = 0;
        q.add(s);
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            for (Vertex w : v.adjacent) {
                if (w.dist == INF) {
                    w.path = v;
                    w.dist = v.dist+1;
                    q.add(w);
                }
            }
        }
    }
}
