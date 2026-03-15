package SS6.bai6;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
            if (deadlockedThreads != null && deadlockedThreads.length > 0) {
                System.out.println("Phat hien deadlock! Cac thread bi khoa:");
                for (long threadId : deadlockedThreads) {
                    ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
                    System.out.println("Thread: " + threadInfo.getThreadName() + " (ID: " + threadId + ")");
                }
              
            }
        }
    }

    public void stop() {
        running = false;
    }
}