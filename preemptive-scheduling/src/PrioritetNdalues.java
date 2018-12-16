import java.util.ArrayList;



/* 
  	
  
 
 */

public class PrioritetNdalues {

	public static void main(String[] args) {

		InputController inputController = new InputController();
		ProcessScheduleCalculator processScheduleCalculator = new ProcessScheduleCalculator();
		ArrayList<Process> allProcesses = null;
		ArrayList<Process> processSchedule = null;

		// marr te dhenat nga tastjera
		allProcesses = inputController.getAllProcesses();

		// therras metoden qe ben schedulimet e proceseve ne baze te prioritetit :
		processSchedule = processScheduleCalculator.calculateProcessSchedule(allProcesses);

		// gjej kohen e pritjes

		// gjej kohen e qendrimit

	}

}
