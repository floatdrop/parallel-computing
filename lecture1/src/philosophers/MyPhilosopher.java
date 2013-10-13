package philosophers;

public class MyPhilosopher extends Philosopher implements Runnable {

    volatile boolean stopFlag = false;

    public MyPhilosopher(int position, Fork left, Fork right, int thinkingTime, int eatingTime) {
        super(position, left, right);
        this.thinkingTime = thinkingTime;
        this.eatingTime = eatingTime;
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