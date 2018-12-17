import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PreemptiveSchedulingLogic {

	// ArrayList that order of the process execution
	private ArrayList<ScheduledProcess> scheduledProcesses;
	int currentTime;
	int exeTime;

	public PreemptiveSchedulingLogic() {
		scheduledProcesses = new ArrayList<ScheduledProcess>();
		currentTime = 0;
		exeTime = 0;
	}

	public ArrayList<ScheduledProcess> calculateProcessSchedule(ArrayList<Process> allProcesses) {

		// get the first arriving process
		Process process = this.getFirstArrivingProcess(allProcesses);
		Process nextProcess = this.getNextProcess(allProcesses, process.getArrivingTime());

		Process currentProcess = process;

		while (allProcesses.size() > 0) {

			Process nextHigherPriorityProcess = this.getNextHigherPriorityProcess(allProcesses, process);

			exeTime = nextHigherPriorityProcess.getArrivingTime() - currentTime;

			if (exeTime >= process.getBurstTime()) {

				process.setBurstTime(exeTime - process.getBurstTime());

				// delete this line
				System.out.println(process.getProcessNumber());

				scheduledProcesses.add(new ScheduledProcess(process.getProcessNumber(), process.getBurstTime()));
				allProcesses.remove(process.getProcessNumber() - 1);

				process = this.getNextProcess(allProcesses, process.getProcessNumber());
				// process = this.getNextProcessAfterCurrentProcess(allProcesses,
				// process.getArrivingTime(),
				// nextHigherPriorityProcess.getArrivingTime());

			} else if (exeTime < process.getBurstTime()) {
				process.setBurstTime(process.getBurstTime() - exeTime);
				// delete this line
				System.out.println(process.getProcessNumber());

				scheduledProcesses.add(new ScheduledProcess(process.getProcessNumber(), exeTime));

				// get the next process to execute
				process = this.getNextProcess(allProcesses, process.getProcessNumber());

				// process = this.getNextProcessAfterCurrentProcess(allProcesses,
				// process.getArrivingTime(),
				// nextHigherPriorityProcess.getArrivingTime());
			}
			/*
			 * else if (exeTime == process.getBurstTime()) {
			 * 
			 * // delete this line System.out.println(process.getProcessNumber());
			 * 
			 * scheduledProcesses.add(new ScheduledProcess(process.getProcessNumber(),
			 * exeTime)); allProcesses.remove(process.getProcessNumber() - 1); process =
			 * this.getNextProcess(allProcesses, process.getProcessNumber()); }
			 */ else { // behavior same as non-preemptive scheduling
						// delete this line
				System.out.println("breaaak");

				break;
			}
			if (process.getProcessNumber() == this.getMaximumPriorityProcess(allProcesses).getProcessNumber()) {
				System.out.println("breaaak");

				break;
			}
			currentTime += exeTime;

			// Process nextHigherPriorityProcess =
			// this.getNextHigherPriorityProcess(allProcesses, currentProcess);

		}
		return scheduledProcesses;

	}

	public Process getNextProcess(ArrayList<Process> allProcesses, int actualArrivingTime) {
		return allProcesses.get(actualArrivingTime + 1);
	}

	public Process getMaximumPriorityProcess(ArrayList<Process> allProcesses) {
		int max = Integer.MIN_VALUE;
		Process maxPriorityProcess = null;
		for (int i = 0; i < allProcesses.size(); i++) {
			if (allProcesses.get(i).getProcessPriority() > max) {
				max = allProcesses.get(i).getProcessPriority();
				maxPriorityProcess = allProcesses.get(i);
			}
		}
		return maxPriorityProcess;
	}

	public Process getNextHigherPriorityProcess(ArrayList<Process> allProcesses, Process process) {
		for (int i = 0; i < allProcesses.size(); i++) {
			if (process.getArrivingTime() < allProcesses.get(i).getArrivingTime()
					&& process.getProcessPriority() <= allProcesses.get(i).getProcessPriority()) {
				return allProcesses.get(i);
			}
		}

		return null;

	}

	public Process getFirstArrivingProcess(ArrayList<Process> allProcesses) {
		int min = Integer.MAX_VALUE;
		Process minArrivingTimeProcess = null;
		for (int i = 0; i < allProcesses.size(); i++) {
			if (allProcesses.get(i).getArrivingTime() < min) {
				min = allProcesses.get(i).getArrivingTime();
				minArrivingTimeProcess = allProcesses.get(i);
			}
		}
		return minArrivingTimeProcess;
	}

	public Process getNextProcessAfterCurrentProcess(ArrayList<Process> allProcesses, int startTime, int endTime) {
		for (int i = 0; i < allProcesses.size(); i++) {
			Process p = allProcesses.get(i);
			if (p.getArrivingTime() > startTime && p.getArrivingTime() <= endTime) {
				return p;
			}
		}
		return null;
	}

}
