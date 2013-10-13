package philosophers;

import java.util.Random;

public class Philosopher {

    private final int maxEatingTime;
    private final int maxThinkingTime;
    int position;
    Fork left;
    Fork right;
    int eatingTime = 0;
    long thinkingTime = 0;
    long startWait;
    Random rnd = new Random();

    public Philosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime) {
        this.position = position;
        this.left = left;
        this.right = right;
        this.maxEatingTime = maxEatingTime;
        this.maxThinkingTime = maxThinkingTime;
    }
    
    public void eat() {
        thinkingTime += System.currentTimeMillis() - startWait;
        System.out.println("[Philosopher " + position + "] is eating");
        try {
            Thread.sleep(rnd.nextInt(maxEatingTime));
        } catch (InterruptedException e) { e.printStackTrace(); }
        eatingTime++;
        System.out.println("[Philosopher " + position + "] finished eating");        
    }

    public void think() {
        System.out.println("[Philosopher " + position + "] is thinking");
        try {
            Thread.sleep(rnd.nextInt(maxThinkingTime));
        } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("[Philosopher " + position + "] is hungry");
        startWait = System.currentTimeMillis();
    }  
    
}
