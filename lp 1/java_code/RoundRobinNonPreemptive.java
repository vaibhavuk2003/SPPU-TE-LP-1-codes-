import java.util.Scanner;

public class RoundRobinNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        
        // Arrays for arrival time, burst time, and additional calculations
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        
        // Input time quantum
        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();
        
        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        int time = 0, completed = 0;
        String ganttChart = "";

        // Execute until all processes are completed
        while (completed < n) {
            boolean processExecuted = false;
            
            for (int i = 0; i < n; i++) {
                // Check if process has arrived and has remaining time
                if (arrivalTime[i] <= time && remainingTime[i] > 0) {
                    if (remainingTime[i] > quantum) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                        ganttChart += "P" + (i + 1) + " ";
                    } else {
                        // Process finishes its remaining time
                        time += remainingTime[i];
                        remainingTime[i] = 0;
                        completionTime[i] = time;  // Set completion time
                        ganttChart += "P" + (i + 1) + " ";
                        completed++;
                        waitingTime[i] = completionTime[i] - burstTime[i] - arrivalTime[i];
                        turnaroundTime[i] = completionTime[i] - arrivalTime[i];
                    }
                    processExecuted = true;
                }
            }

            // If no process was executed, increase time to next arrival
            if (!processExecuted) {
                ganttChart += "idle ";
                time++;
            }
        }

        // Print Gantt Chart
        System.out.println("Gantt Chart: " + ganttChart);

        // Calculate average waiting time and turnaround time
        int totalWT = 0, totalTAT = 0;
        System.out.println("Process\tArrival\tBurst\tCompletion\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
            System.out.println("P" + (i + 1) + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }

        System.out.println("Average Waiting Time: " + (totalWT / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));
        
        sc.close();
    }
}
