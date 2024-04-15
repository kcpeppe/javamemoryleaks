package com.kodewerk.monitoring;

import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;

public class PSFull {

    private long id;
    private int threadCount;
    private long durationInMS;
    private long startTimeInMS;
    private long endTimeInMS;
    private MemoryPool edenBeforeGC;
    private MemoryPool edenAfterGC;
    private MemoryPool survivorBeforeGC;
    private MemoryPool survivorAfterGC;
    private MemoryPool tenuredBeforeGC;
    private MemoryPool tenuredAfterGC;

    public PSFull(CompositeDataSupport gcInfo) {
        explode(gcInfo);
    }

    /*
    Avaliable keys for Parallel
    [PS Eden Space]
    [PS Survivor Space]
    [PS Old Gen]
    [Metaspace]
    [Compressed Class Space]
    [Code Cache]
 */
    private void explodeBeforeGC(TabularDataSupport memoryInfo) {
        edenBeforeGC = new MemoryPool(memoryInfo,"PS Eden Space");
        survivorBeforeGC = new MemoryPool(memoryInfo, "PS Survivor Space");
        tenuredBeforeGC = new MemoryPool(memoryInfo,"PS Old Gen");
    }

    private void explodeAfterGC(TabularDataSupport memoryInfo) {
        edenAfterGC = new MemoryPool(memoryInfo,"PS Eden Space");
        survivorAfterGC = new MemoryPool(memoryInfo, "PS Survivor Space");
        tenuredAfterGC = new MemoryPool(memoryInfo,"PS Old Gen");
    }

    private void explode(CompositeDataSupport gcInfo) {
        id = (Long)gcInfo.get("id");
        startTimeInMS = (Long)gcInfo.get("startTime");
        endTimeInMS = (Long)gcInfo.get("endTime");
        durationInMS = (Long)gcInfo.get("duration");
        threadCount = (Integer)gcInfo.get("GcThreadCount");
        explodeBeforeGC((TabularDataSupport)gcInfo.get("memoryUsageBeforeGc"));
        explodeAfterGC((TabularDataSupport)gcInfo.get("memoryUsageAfterGc"));
    }

    public long getId() {
        return id;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public long getDurationInMS() {
        return durationInMS;
    }

    public long getStartTimeInMS() {
        return startTimeInMS;
    }

    public long getEndTimeInMS() {
        return endTimeInMS;
    }

    public MemoryPool getEdenBeforeGC() {
        return edenBeforeGC;
    }

    public MemoryPool getEdenAfterGC() {
        return edenAfterGC;
    }

    public MemoryPool getSurvivorBeforeGC() {
        return survivorBeforeGC;
    }

    public MemoryPool getSurvivorAfterGC() {
        return survivorAfterGC;
    }

    public MemoryPool getTenuredBeforeGC() {
        return tenuredBeforeGC;
    }

    public MemoryPool getTenuredAfterGC() {
        return tenuredAfterGC;
    }

    @Override
    public String toString() {
        String padding = "                    ".substring(0, Long.toString(startTimeInMS).length() + 4);
        return startTimeInMS + "ms: " + toStringBeforeGC() + "\n" + padding + toStringAfterGC() + "\n"  + ", Duration: " + durationInMS + "ms";
    }

    public String toStringBeforeGC() {
        return getEdenBeforeGC().toString() + " " + getSurvivorBeforeGC() + " " + getTenuredBeforeGC();
    }

    public String toStringAfterGC() {
        return getEdenAfterGC().toString() + " " + getSurvivorAfterGC() + " " + getTenuredAfterGC();
    }
}
