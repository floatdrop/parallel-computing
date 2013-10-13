package philosophers;

public class MyPhilosopher extends Philosopher implements Runnable {

    volatile boolean stopFlag = false;

    public MyPhilosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime) {
        super(position, left, right, maxThinkingTime, maxEatingTime);
    }

    public void run() {
        while (!stopFlag) {
            think();
            synchronized (left) {
                System.out.println("[Philosopher " + position + "] took left fork");
                synchronized (right) {
                    System.out.println("[Philosopher " + position + "] took right fork");
                    eat();
                }
            }
        }
        System.out.println("[Philosopher " + position + "] stopped");
    }
}