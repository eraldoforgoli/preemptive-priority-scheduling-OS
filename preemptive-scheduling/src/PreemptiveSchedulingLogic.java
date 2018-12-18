import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PreemptiveSchedulingLogic {

	private ArrayList<ScheduledProcess> scheduledProcesses;
	int currentTime;
	int exeTime;
	Process nextHigherPriorityProcess;
	int totatExecutionTime;

	public PreemptiveSchedulingLogic() {
		scheduledProcesses = new ArrayList<ScheduledProcess>();
		currentTime = 0;
		exeTime = 0;
		totatExecutionTime = 0;
		nextHigherPriorityProcess = null;
	}

	public ArrayList<ScheduledProcess> calculateProcessSchedule(ArrayList<Process> allProcesses) {

		// get the first arriving process
		Process process = this.getFirstArrivingProcess(allProcesses);

		Process nextProcess = this.getNextProcess(allProcesses, process.getArrivingTime());


		while (allProcesses.size() > 0 && nextProcess != null) {

			nextHigherPriorityProcess = this.getNextHigherPriorityProcess(allProcesses, process);

			exeTime = nextHigherPriorityProcess.getArrivingTime() - currentTime;
			totatExecutionTime +=exeTime;

			if (currentTime == nextHigherPriorityProcess.getArrivingTime()) {
				process = nextHigherPriorityProcess;
			}

			if (exeTime >= process.getBurstTime()) {
				totatExecutionTime += process.getBurstTime();
				ScheduledProcess scheduledProceess = new ScheduledProcess(process.getProcessNumber(), totatExecutionTime);
				scheduledProcesses.add(scheduledProceess);
				allProcesses.remove(process);

				// process = this.getNextProcess(allProcesses, process.getProcessNumber());
				process = this.getNextProcessAfterCurrentProcess(allProcesses, process.getArrivingTime(),
						nextHigherPriorityProcess.getArrivingTime());

			} else {
				process.setBurstTime(process.getBurstTime() - exeTime);
		
				scheduledProcesses.add(new ScheduledProcess(process.getProcessNumber(), totatExecutionTime));

				// get the next process to execute
				process = this.getNextHigherPriorityProcess(allProcesses, process);
				// process = this.getNextProcess(allProcesses, process.getArrivingTime());
				
				if (process == null)
					break;
			}
			
			/* Behavior same as non-preemptive scheduling */
			if (process.getProcessPriority() == this.getMaximumPriorityProcess(allProcesses).getProcessPriority()) {  
				scheduledProcesses = this.processEachMaximum(allProcesses, scheduledProcesses, exeTime);
				break;
			}

			currentTime += exeTime;

		}
		return scheduledProcesses;
	}

	public Process getNextProcess(ArrayList<Process> allProcesses, int position) {
		if (position + 1 <= allProcesses.size() - 1)
			return allProcesses.get(position + 1);
		return null;
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
		return process;
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

	public ArrayList<ScheduledProcess> processEachMaximum(ArrayList<Process> allProcesses,
			ArrayList<ScheduledProcess> scheduledProcesses, int exeTime) {

		while (allProcesses.size() > 0) {
			Process maxProcess = this.getMaximumPriorityProcess(allProcesses);
			exeTime += maxProcess.getBurstTime();
			scheduledProcesses.add(new ScheduledProcess(maxProcess.getProcessNumber(), exeTime));
			allProcesses.remove(maxProcess);
		}
		return scheduledProcesses;

	}
	
	public ArrayList<ScheduledProcess> getCompletionTimes(ArrayList<Process> allProcesses, ArrayList<ScheduledProcess> scheduledProcesses){
		
		 ArrayList<ScheduledProcess> scheduledProcessTime = new ArrayList<ScheduledProcess>();
		 
		for(Process p: allProcesses) {
			ScheduledProcess sp = null;
			for(ScheduledProcess scheduledProcess : scheduledProcesses) {
				if(scheduledProcess.getProcessNumber() == p.getProcessNumber()) {
					sp = scheduledProcess;
				}
			}
			scheduledProcessTime.add(sp);
		}
		return scheduledProcessTime;
		
	}
}
