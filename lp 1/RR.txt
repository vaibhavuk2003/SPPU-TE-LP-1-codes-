import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] arrivalTime = new int[n];
        int[] completionTime = new int[n];

        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        int currentTime = 0;
        int completedProcesses = 0;
        StringBuilder ganttChart = new StringBuilder();

        while (completedProcesses < n) {
            boolean processExecuted = false;
            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0 && arrivalTime[i] <= currentTime) {
                    processExecuted = true;
                    
                    // Append process execution to Gantt chart
                    ganttChart.append("P").append(i + 1).append(" ");

                    if (remainingTime[i] > quantum) {
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        currentTime += remainingTime[i];
                        remainingTime[i] = 0;
                        completionTime[i] = currentTime; // Record completion time
                        waitingTime[i] = completionTime[i] - burstTime[i] - arrivalTime[i];
                        turnaroundTime[i] = completionTime[i] - arrivalTime[i];
                        completedProcesses++;
                    }
                }
            }

            // If no process was executed, CPU is idle
            if (!processExecuted) {
                ganttChart.append("idle ");
                currentTime++;
            }
        }

        // Output Gantt Chart
        System.out.println("Gantt Chart: " + ganttChart);

        // Calculate average waiting and turnaround times
        float totalWT = 0, totalTAT = 0;
        System.out.println("Process\tArrival\tBurst\tCompletion\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
            System.out.println("P" + (i + 1) + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }

        System.out.println("Average Waiting Time: " + (totalWT / n));
        System.out.println("Average Turnaround Time: " + (totalTAT / n));
        sc.close();
    }
}
