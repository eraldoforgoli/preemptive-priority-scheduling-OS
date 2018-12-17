import java.util.ArrayList;
import java.util.Scanner;

public class InputController {

	private ArrayList<Process> allProcesses;
	private int processNumber;
	private int processPriority;
	private int processArrivingTime;
	private int processBurstTime;
	private Scanner in;

	// konstruktor
	public InputController() {
		allProcesses = new ArrayList<Process>();
		processNumber = 0;
		processPriority = 0;
		processArrivingTime = 0;
		processBurstTime = 0;
		in = new Scanner(System.in);
	}

	public ArrayList<Process> getAllProcesses() {

		System.out.println("Start entering your processes: ");

		do {

			System.out.println("Enter the process Priority:  ");

			while (!in.hasNextInt()) {
				System.out.println("Wrong input, enter the Priority again as integer!");
				in.next();
			}
			processPriority = in.nextInt();

			System.out.println("Enter the process Arriving Time:   ");
			while (!in.hasNextInt()) {
				System.out.println("Wrong input, enter the Arriving Time again as integer!");
				in.next();
			}
			processArrivingTime = in.nextInt();

			System.out.println("Enter the process Burst Time:  ");
			while (!in.hasNextInt()) {
				in.next();
				System.out.println("Wrong input, enter the Burst Time again as integer!");
			}
			processBurstTime = in.nextInt();

			allProcesses.add(new Process(++processNumber, processPriority, processArrivingTime, processBurstTime));
			System.out.println("Do You want to finish entering the processes? (Y/N)");

		}

		while (!(in.nextLine().equals("Y") || in.nextLine().equals("y")));

		return allProcesses;

	}

}
