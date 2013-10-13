package philosophers;

public class Fork {
    boolean clean = false;
    Philosopher owner;

    public synchronized boolean acquire(Philosopher phil) {
        if (!clean) {
            System.out.println("[Philosopher " + phil.position + "] took " + (phil.left == this ? "left" : "right") + " fork");
            if (this.owner != null && this.owner != phil && this.owner.state == State.Thinking) {
                this.clean = true;
            }
            this.owner = phil;
            return true;
        }

        if (clean && owner == phil)
            return true;

        return false;
    }
}
