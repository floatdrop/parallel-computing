package philosophers;

public class Fork {
    boolean clean = false;
    Philosopher owner;

    public synchronized boolean acquire(Philosopher phil) {
        if (this.owner == phil)
            return this.clean;

        System.out.println(
                "[Philosopher " + phil.position + "] requested " +
                (phil.left == this ? "left" : "right") + " " +
                (this.clean ? "clean" : "dirty") + " fork from " +
                (owner == null ? "table" : "philosopher " + owner.position + ", which is " + owner.state));

        if (this.owner == null || this.owner.state == State.Thinking || !clean) {
            this.clean = true;
            this.owner = phil;
            return true;
        }

        return false;
    }
}
