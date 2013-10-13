package philosophers;

public class MyPhilosopher extends Philosopher implements Runnable {

    volatile boolean stopFlag = false;

    public MyPhilosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime) {
        super(position, left, right, maxThinkingTime, maxEatingTime);
    }

    public void run() {
        while (!stopFlag) {
            think();
            System.out.println("[Philosopher " + position + "] acquiring left fork");
            if (!left.acquire(this)) continue;
            System.out.println("[Philosopher " + position + "] acquiring right fork");
            if (!right.acquire(this)) continue;
            eat();

        }
        System.out.println("[Philosopher " + position + "] stopped");
    }
}
