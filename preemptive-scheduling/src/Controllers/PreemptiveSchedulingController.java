package Controllers;

import java.util.ArrayList;
import Models.ScheduledProcess;
import Models.Process;

/* 
 Explanation about Preemptive Priority Scheduling 
In Preemptive Priority Scheduling, at the time of arrival of a process in the ready queue, its Priority is compared with the priority
of the other processes present in the ready queue as well as with the one which is being executed by the CPU at that point of time.
The One with the highest priority among all the available processes will be given the CPU next.
In the preemptive priority scheduling, the job which is being executed can be stopped at the arrival of a higher priority job.
Once all the jobs get available in the ready queue, the algorithm will behave as non-preemptive priority scheduling, which means the
 job scheduled will run till the completion and no preemption will be done.  	
 
 Note: In the PreemptiveShcedulingLogic, the greatest the priority number, the highest the priority. 
  
 */

public class PreemptiveSchedulingController {

	public static void main(String[] args) {

		InputController inputController = new InputController();
		PreemptiveSchedulingLogic preemptiveSchedulingLogic = new PreemptiveSchedulingLogic();
		ArrayList<Process> allProcesses = new ArrayList<Process>();
		ArrayList<ScheduledProcess> scheduledProcesses = new ArrayList<ScheduledProcess>();
		ArrayList<ScheduledProcess> complitionTimes = new ArrayList<ScheduledProcess>();

		allProcesses = inputController.getAllProcesses();
		System.out.println("Pr  AT  BT");
		for (Process process : allProcesses) {
			System.out.println(
					process.getProcessPriority() + "   " + process.getArrivingTime() + "   " + process.getBurstTime());
		}

		scheduledProcesses = preemptiveSchedulingLogic.calculateProcessSchedule(allProcesses);

		System.out.println("Process execution order : ");

		for (ScheduledProcess sp : scheduledProcesses) {
			System.out.print(sp.getProcessNumber() + "  ");

		}

		System.out.println("\nProcess execution time: ");
		for (ScheduledProcess sp : scheduledProcesses) {
			System.out.print(sp.getExeTime() + "  ");
		}

		// get the process completion time
		complitionTimes = preemptiveSchedulingLogic.getCompletionTimes(allProcesses, scheduledProcesses);

		System.out.println("\nCompletion time: ");
		for (ScheduledProcess times : complitionTimes) {
			System.out.print(times.getExeTime() + "  ");
		}

		// gjej kohen e qendrimit

	}

}
