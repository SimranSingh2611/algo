package search.binarySearch;

import java.util.HashSet;
import java.util.Set;

public abstract class VersionControl {
    private Set<Integer> badVersions;
    private int totalVersions;

    public VersionControl(boolean[] versions) {
        totalVersions = versions.length;
        badVersions = new HashSet<>();
        for(int i = 0; i < totalVersions; i++) {
            if(versions[i]) {
                badVersions.add(i + 1);
            }
        }
    }

    public boolean isBadVersion(int version) {
        if(version < 1 || version > totalVersions) {
            throw new IndexOutOfBoundsException();
        }
        return badVersions.contains(version);
    }
}