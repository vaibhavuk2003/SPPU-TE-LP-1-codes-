import java.util.Scanner;

class SJFNProcess {
    int pid, burstTime, arrivalTime, waitingTime, turnaroundTime, completionTime;
    boolean isCompleted;

    public SJFNProcess(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.isCompleted = false;
    }
}

public class SJFNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        SJFNProcess[] processes = new SJFNProcess[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes[i] = new SJFNProcess(i + 1, bt, at);
        }

        int totalTime = 0, completed = 0, totalWT = 0, totalTAT = 0;
        StringBuilder ganttChart = new StringBuilder();
        //StringBuilder is used in Java to efficiently build or modify strings, especially within loops
        while (completed < n) {
            int idx = -1;
            int minBurst = Integer.MAX_VALUE;

            // Find process with the shortest burst time among those that have arrived and not completed
            for (int i = 0; i < n; i++) {
                if (!processes[i].isCompleted && processes[i].arrivalTime <= totalTime && processes[i].burstTime < minBurst) {
                    minBurst = processes[i].burstTime;
                    idx = i;
                }
            }

            // If no process has arrived yet, move time forward to the next process arrival
            if (idx == -1) {
                int earliestArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!processes[i].isCompleted && processes[i].arrivalTime < earliestArrival) {
                        earliestArrival = processes[i].arrivalTime;
                    }
                }
                totalTime = earliestArrival;
                continue;
            }

            // Process the selected process
            SJFNProcess p = processes[idx];
            p.waitingTime = totalTime - p.arrivalTime;
            totalTime += p.burstTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            p.completionTime = totalTime;
            p.isCompleted = true;

            // Update totals and Gantt Chart
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
            ganttChart.append("P").append(p.pid).append(" ");
            completed++;
        }

        // Print Gantt Chart
        System.out.println("Gantt Chart: ");
        System.out.println(ganttChart.toString().trim() + "\n");

        // Print process details
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround\tCompletion");
        for (SJFNProcess p : processes) {
            System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" + p.waitingTime + "\t" + p.turnaroundTime + "\t\t" + p.completionTime);
        }

        System.out.println("Average Waiting Time: " + (totalWT / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));

        sc.close();
    }
}
