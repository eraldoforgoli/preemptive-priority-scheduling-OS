package Models;

public class ScheduledProcess {

    private int processNumber;
    private int exeTime;

    public ScheduledProcess(int processNumber, int exeTime) {
        this.processNumber = processNumber;
        this.exeTime = exeTime;
    }

    public int getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }
}
