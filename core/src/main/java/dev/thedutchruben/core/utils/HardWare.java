package dev.thedutchruben.core.utils;

public class HardWare {


    public static long getFreeMB() {
        Runtime r = Runtime.getRuntime();
        long memUsed = (r.totalMemory() - r.freeMemory()) / 1048576L;
        long maxMem = r.maxMemory() / 1048576L;
        return maxMem - memUsed;
    }
}
