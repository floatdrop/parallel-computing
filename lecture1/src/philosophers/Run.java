package philosophers;

public class Run {

    static int count = 5;
    static int thinkingTime = 100;
    static int eatingTime = 100;
    static int workingTime = 60000;
    static boolean debug = false;

    public static void main(String[] args) throws Exception {
        parseArguments(args);

        MyPhilosopher[] phils = new MyPhilosopher[count];

        Fork last = new Fork();
        Fork left = last;
        for (int i = 0; i < count; i++) {
            Fork right = (i == count - 1) ? last : new Fork();
            phils[i] = new MyPhilosopher(i, left, right, eatingTime, thinkingTime);
            left = right;
        }

        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(phils[i]);
            threads[i].start();
        }

        Thread.sleep(workingTime);

        for (MyPhilosopher phil : phils) {
            phil.stopFlag = true;
        }
        for (Thread thread : threads) {
            thread.join();
        }

        for (MyPhilosopher phil : phils) {
            System.out.println("[Philosopher " + phil.position + "] ate " 
                    + phil.eatingTime + " times and waited " + phil.thinkingTime + " ms");
        }
    }

    public static void parseArguments(String[] args) {
        count = args.length > 0 ? Integer.parseInt(args[0]) : count;
        workingTime = args.length > 1 ? Integer.parseInt(args[1]) * 1000 : workingTime;
        thinkingTime = args.length > 2 ? Integer.parseInt(args[2]) : thinkingTime;
        eatingTime = args.length > 3 ? Integer.parseInt(args[3]) : eatingTime;
        debug = args.length > 4 ? args[4].equals("1") : debug;
    }

}
