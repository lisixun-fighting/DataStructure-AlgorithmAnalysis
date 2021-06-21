package disjoint;

import java.util.Arrays;

public class DisjSets {

    private int[] s;

    public DisjSets(int numElements) {
        s = new int[numElements];
        Arrays.fill(s, -1);
    }

    public void union(int root1, int root2) {
        s[root2] = root1;
    }

    /**
     * Union two disjoint sets using the height heuristic
     * For simplicity, we assume root1 and root2 are distinct and represent set names
     * @param root1 the root of set1
     * @param root2 the root of set2
     */
    public void union1(int root1, int root2) {
        if (s[root2] < s[root1]) {
            s[root1] = root2;
        } else {
            if (s[root1] == s[root2])
                s[root1]--;
            s[root2] = root1;
        }
    }

    public int find(int x) {
        if (s[x] < 0)
            return x;
        return find(s[x]);
    }


}
