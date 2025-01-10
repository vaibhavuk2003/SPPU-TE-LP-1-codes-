package RoundRobinandPriority;
import java.util.Scanner;

//Round Robin Scheduling Class
class RoundRobin {
 public void execute() {
     Scanner sc = new Scanner(System.in);

     System.out.println("Enter Number of Processes");
     int number = sc.nextInt();

     int[] burstTime = new int[number];
     int[] remainingTime = new int[number];
     int[] waitingTime = new int[number];
     int[] turnaroundTime = new int[number];
     int[] arrivalTime = new int[number];

     System.out.print("Enter Time Quantum: ");
     int quantum = sc.nextInt();

     for (int i = 0; i < number; i++) {
         System.out.print("Enter arrival time for P" + (i + 1) + ": ");
         arrivalTime[i] = sc.nextInt();

         System.out.print("Enter burst time for P" + (i + 1) + ": ");
         burstTime[i] = sc.nextInt();

         remainingTime[i] = burstTime[i];
     }

     int currentTime = 0;
     int completedProcesses = 0;
     String ganttChart = "";

     while (completedProcesses < number) {
         boolean found = false;

         for (int i = 0; i < number; i++) {
             if (remainingTime[i] > 0 && arrivalTime[i] <= currentTime) {
                 found = true;

                 if (remainingTime[i] > quantum) {
                     remainingTime[i] -= quantum;
                     currentTime += quantum;
                     ganttChart += " P" + (i + 1) + " ";
                 } else {
                     currentTime += remainingTime[i];
                     ganttChart += " P" + (i + 1) + " ";
                     waitingTime[i] = currentTime - burstTime[i] - arrivalTime[i];
                     turnaroundTime[i] = waitingTime[i] + burstTime[i];
                     remainingTime[i] = 0;
                     completedProcesses++;
                 }
             }
         }
         if (!found) {
             currentTime++;
             ganttChart += " IDLE ";
         }
     }

     System.out.println("Gantt Chart: " + ganttChart);

     int averageWaiting = 0;
     int averageTurnaround = 0;
     for (int i = 0; i < number; i++) {
         averageWaiting += waitingTime[i];
         averageTurnaround += turnaroundTime[i];
     }

     System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
     for (int i = 0; i < number; i++) {
         System.out.println("P" + (i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i]
                 + "\t\t" + turnaroundTime[i]);
     }
     System.out.println("Average Waiting Time: " + (averageWaiting / number));
     System.out.println("Average Turnaround Time: " + (averageTurnaround / number));
 }
}

//Priority Scheduling Class
class Priority {
 public void execute() {
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter number of processes: ");
     int number = sc.nextInt();

     int[] arrivalTime = new int[number];
     int[] burstTime = new int[number];
     int[] turnaroundTime = new int[number];
     int[] waitingTime = new int[number];
     int[] remainingTime = new int[number];
     int[] priority = new int[number];
     int currentTime = 0;
     int completedProcesses = 0;
     String ganttChart = "";
     int totalTurnaround = 0;
     int totalWaiting = 0;

     System.out.print("Enter Time Quantum: ");
     int quantum = sc.nextInt(); // Get the time quantum for preemptive scheduling

     // Input process details
     for (int i = 0; i < number; i++) {
         System.out.println("Enter Arrival time of P" + (i + 1) + ": ");
         arrivalTime[i] = sc.nextInt();
         System.out.println("Enter Burst time for P" + (i + 1) + ": ");
         burstTime[i] = sc.nextInt();
         remainingTime[i] = burstTime[i];
         System.out.println("Enter Priority of P" + (i + 1) + ": ");
         priority[i] = sc.nextInt();
     }

     while (completedProcesses < number) {
         boolean processFound = false; // Flag to check if a process is found to run
         int highestPriority = -1;
         // Iterate through all processes

         for (int j = 0; j < number; j++) {
             if (arrivalTime[j] <= currentTime && remainingTime[j] > 0) {
                 if (highestPriority == -1 || priority[j] > priority[highestPriority]) {
                     highestPriority = j;
                     processFound = true;
                 }
             }
         }
         if (processFound) {
             if (remainingTime[highestPriority] > 0 && arrivalTime[highestPriority] <= currentTime) {
                 // If the remaining time is greater than the quantum, execute for quantum time
                 if (remainingTime[highestPriority] > quantum) {
                     remainingTime[highestPriority] -= quantum;
                     currentTime += quantum;
                     ganttChart += " P" + (highestPriority + 1) + " ";
                 } else {
                     // Process completes execution if remaining time is less than or equal to quantum
                     currentTime += remainingTime[highestPriority];
                     ganttChart += " P" + (highestPriority + 1) + " ";

                     // Calculate waiting time and turnaround time
                     waitingTime[highestPriority] = currentTime - burstTime[highestPriority] - arrivalTime[highestPriority];
                     turnaroundTime[highestPriority] = waitingTime[highestPriority] + burstTime[highestPriority];
                     remainingTime[highestPriority] = 0; // Process is completed
                     completedProcesses++; // Increment the completed process count
                 }
             }
         }

         // If no process was found to run, increment time and add IDLE in Gantt chart
         if (!processFound) {
             currentTime++;
             ganttChart += " IDLE ";
         }
     }

     // Display Gantt Chart
     System.out.println("Gantt Chart: " + ganttChart);

     // Display the table of process details
     System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
     for (int i = 0; i < number; i++) {
         totalWaiting += waitingTime[i];
         totalTurnaround += turnaroundTime[i];
         System.out.println("P" + (i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i]
                 + "\t\t" + turnaroundTime[i]);
     }

     // Display average waiting time and average turnaround time
     System.out.println("Average Waiting Time: " + (totalWaiting / (float) number));
     System.out.println("Average Turnaround Time: " + (totalTurnaround / (float) number));
 }
}
public class RoundRobinandPriority {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nCPU Scheduling Algorithms:");
            System.out.println("1. Round Robin Scheduling");
            System.out.println("2. Priority Scheduling");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    RoundRobin rrScheduler = new RoundRobin();
                    rrScheduler.execute();
                    break;
                case 2:
                    Priority priorityScheduler = new Priority();
                    priorityScheduler.execute();
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
	}

}
