package philosophers;

import java.util.concurrent.TimeUnit;

public class MyPhilosopher extends Philosopher implements Runnable {

    volatile boolean stopFlag = false;

    public MyPhilosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime) {
        super(position, left, right, maxThinkingTime, maxEatingTime);
    }

    public void run() {
        while (!stopFlag) {
            think();
            try {
                if (left.hold.tryLock(this.maxEatingTime / 2, TimeUnit.MILLISECONDS)) {
                    System.out.println("[Philosopher " + position + "] took left fork");
                    if (right.hold.tryLock(this.maxEatingTime / 2, TimeUnit.MILLISECONDS)) {
                        System.out.println("[Philosopher " + position + "] took right fork");
                        eat();
                        right.hold.unlock();
                    }
                    left.hold.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[Philosopher " + position + "] stopped");
    }
}