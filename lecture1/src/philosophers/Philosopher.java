package philosophers;

import java.util.Random;

public class Philosopher {

    enum State {
        Thinking,
        Eating,
        Hungry
    }

    private final int maxEatingTime;
    private final int maxThinkingTime;
    int position;
    Fork left;
    Fork right;
    int eatingTime = 0;
    long thinkingTime = 0;
    long startWait;
    Random rnd = new Random();
    State state;

    public Philosopher(int position, Fork left, Fork right, int maxThinkingTime, int maxEatingTime) {
        this.state = State.Thinking;
        this.position = position;
        this.left = left;
        this.right = right;
        this.maxEatingTime = maxEatingTime;
        this.maxThinkingTime = maxThinkingTime;
    }
    
    public void eat() {
        this.state = State.Eating;
        thinkingTime += System.currentTimeMillis() - startWait;
        System.out.println("[Philosopher " + position + "] is eating");
        try {
            Thread.sleep(rnd.nextInt(maxEatingTime));
        } catch (InterruptedException e) { e.printStackTrace(); }
        eatingTime++;
        System.out.println("[Philosopher " + position + "] finished eating");        
    }

    public void think() {
        this.state = State.Thinking;
        System.out.println("[Philosopher " + position + "] is thinking");
        try {
            Thread.sleep(rnd.nextInt(maxThinkingTime));
        } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("[Philosopher " + position + "] is hungry");
        startWait = System.currentTimeMillis();
    }  
    
}
