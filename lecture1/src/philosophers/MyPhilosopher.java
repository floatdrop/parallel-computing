package philosophers;

public class MyPhilosopher extends Philosopher implements Runnable {

    volatile boolean stopFlag = false;
    Table table;

    public MyPhilosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime, Table table) {
        super(position, left, right, maxThinkingTime, maxEatingTime);
        this.table = table;
    }

    public void run() {
        while (!stopFlag) {
            think();
            synchronized (table) {
                synchronized (left) {
                    System.out.println("[Philosopher " + position + "] took left fork");
                    synchronized (right) {
                        System.out.println("[Philosopher " + position + "] took right fork");
                        eat();
                    }
                }
            }
        }
        System.out.println("[Philosopher " + position + "] stopped");
    }
}