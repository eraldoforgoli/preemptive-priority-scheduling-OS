package Models;

public class Process {

	private int processNumber;
	private int processPriority;
	private int arrivingTime;
	private int burstTime;

	public Process(int processNumber, int processPriority, int arrivingTime, int burstTime) {
		this.processNumber = processNumber;
		this.processPriority = processPriority;
		this.arrivingTime = arrivingTime;
		this.burstTime = burstTime;
	}

	public int getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(int processNumber) {
		this.processNumber = processNumber;
	}

	public int getProcessPriority() {
		return processPriority;
	}

	public void setProcessPriority(int processPriority) {
		this.processPriority = processPriority;
	}

	public int getArrivingTime() {
		return arrivingTime;
	}

	public void setArrivingTime(int arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

}
