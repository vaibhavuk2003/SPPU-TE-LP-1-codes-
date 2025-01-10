package PriorityandSJF;
import java.util.Scanner;

//Priority Scheduling Class
class PriorityScheduling {
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
         int highestPriority = -1;
         boolean processFound = false;

         for (int i = 0; i < number; i++) {
             if (arrivalTime[i] <= currentTime && remainingTime[i] > 0) {
                 if (highestPriority == -1 || priority[i] > priority[highestPriority]) {
                     highestPriority = i;
                     processFound = true;
                 }
             }
         }

         if (processFound) {
             currentTime += burstTime[highestPriority];
             remainingTime[highestPriority] = 0;
             completedProcesses++;
             turnaroundTime[highestPriority] = currentTime - arrivalTime[highestPriority];
             waitingTime[highestPriority] = turnaroundTime[highestPriority] - burstTime[highestPriority];
             ganttChart += "P" + (highestPriority + 1) + " ";
         } else {
             currentTime++;
             ganttChart += "IDLE ";
         }
     }

     System.out.println("Gantt Chart: " + ganttChart);
     System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
     for (int i = 0; i < number; i++) {
         totalWaiting += waitingTime[i];
         totalTurnaround += turnaroundTime[i];
         System.out.println("P" + (i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
     }
     System.out.println("Average Waiting Time: " + (totalWaiting / (float) number));
     System.out.println("Average Turnaround Time: " + (totalTurnaround / (float) number));
 }
}

//Shortest Job First Scheduling Class
class ShortestJobFirst {
 public void execute() {
     Scanner sc = new Scanner(System.in);
     System.out.print("Enter number of processes: ");
     int number = sc.nextInt();

     int[] arrivalTime = new int[number];
     int[] burstTime = new int[number];
     int[] waitingTime = new int[number];
     int[] turnaroundTime = new int[number];
     int[] remainingTime = new int[number];
     int currentTime = 0;
     int completedProcesses = 0;
     String ganttChart = "";
     int totalWaiting = 0;
     int totalTurnaround = 0;

     for (int i = 0; i < number; i++) {
         System.out.print("Enter Arrival Time for P" + (i + 1) + ": ");
         arrivalTime[i] = sc.nextInt();
         System.out.print("Enter Burst Time for P" + (i + 1) + ": ");
         burstTime[i] = sc.nextInt();
         remainingTime[i] = burstTime[i];
     }

     while (completedProcesses < number) {
         int shortest = -1;
         boolean processFound = false;

         for (int i = 0; i < number; i++) {
             if (arrivalTime[i] <= currentTime && remainingTime[i] > 0) {
                 if (shortest == -1 || burstTime[i] < burstTime[shortest]) {
                     shortest = i;
                     processFound = true;
                 }
             }
         }

         if (processFound) {
             currentTime += burstTime[shortest];
             remainingTime[shortest] = 0;
             completedProcesses++;
             turnaroundTime[shortest] = currentTime - arrivalTime[shortest];
             waitingTime[shortest] = turnaroundTime[shortest] - burstTime[shortest];
             ganttChart += "P" + (shortest + 1) + " ";
         } else {
             currentTime++;
             ganttChart += "IDLE ";
         }
     }

     System.out.println("Gantt Chart: " + ganttChart);
     System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
     for (int i = 0; i < number; i++) {
         totalWaiting += waitingTime[i];
         totalTurnaround += turnaroundTime[i];
         System.out.println("P" + (i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
     }
     System.out.println("Average Waiting Time: " + (totalWaiting / (float) number));
     System.out.println("Average Turnaround Time: " + (totalTurnaround / (float) number));
 }
}

public class PriorityandSJF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nCPU Scheduling Algorithms:");
            System.out.println("1. Priority Scheduling");
            System.out.println("2. Shortest Job First (SJF)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    PriorityScheduling priorityScheduling = new PriorityScheduling();
                    priorityScheduling.execute();
                    break;
                case 2:
                    ShortestJobFirst sjf = new ShortestJobFirst();
                    sjf.execute();
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
