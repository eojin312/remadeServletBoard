package example;

public class Robot {
    private String status;
    private int speed;

    public Robot() {
        this.status = "STOPPED";
        this.speed = 0;
    }

    public String run(int speed) {
        this.status = "RUNNING";
        this.speed = speed;
        int aa = 4;
        return this.status;
    }

    public String stop() {
        this.status = "STOPPED";
        this.speed = 0;
        return this.status;
    }

    public String getStatus() {
        return this.status;
    }
}
