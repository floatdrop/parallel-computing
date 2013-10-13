package philosophers;

public class MyPhilosopher extends Philosopher implements Runnable {

    volatile boolean stopFlag = false;

    public MyPhilosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime) {
        super(position, left, right, maxThinkingTime, maxEatingTime);
    }

    public void run() {
        while (!stopFlag) {
            think();
            if (left.hold.tryLock()) {
                System.out.println("[Philosopher " + position + "] took left fork");
                if (right.hold.tryLock()) {
                    System.out.println("[Philosopher " + position + "] took right fork");
                    eat();
                    right.hold.unlock();
                }
                left.hold.unlock();
            }
        }
        System.out.println("[Philosopher " + position + "] stopped");
    }
}