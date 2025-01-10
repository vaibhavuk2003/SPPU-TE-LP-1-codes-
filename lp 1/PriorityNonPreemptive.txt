import java.util.Scanner;

class PriorityProcess {
    int pid, burstTime, priority, waitingTime, turnaroundTime, arrivalTime, completionTime;

    public PriorityProcess(int pid, int burstTime, int priority, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
    }
}

public class PriorityNonPreemption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        PriorityProcess[] processes = new PriorityProcess[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time, priority, and arrival time for process " + (i + 1) + ": ");
            int bt = sc.nextInt();
            int priority = sc.nextInt();
            int arrival = sc.nextInt();
            processes[i] = new PriorityProcess(i + 1, bt, priority, arrival);
        }

        // Sort processes by arrival time first, then by priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime || 
                   (processes[j].arrivalTime == processes[j + 1].arrivalTime && processes[j].priority > processes[j + 1].priority)) {
                    PriorityProcess temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int currentTime = 0;
        int totalWT = 0, totalTAT = 0;

        for (PriorityProcess p : processes) {
            // Update current time based on process arrival
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.waitingTime = currentTime - p.arrivalTime;
            currentTime += p.burstTime;
            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // Print Gantt Chart
        System.out.println("Gantt Chart: ");
        for (PriorityProcess p : processes) {
            System.out.print("P" + p.pid + " ");
        }
        System.out.println("\n");

        // Print process details
        System.out.println("Process\tBurst\tPriority\tArrival\tWaiting\tTurnaround\tCompletion");
        for (PriorityProcess p : processes) {
            System.out.println("P" + p.pid + "\t" + p.burstTime + "\t" + p.priority + "\t\t" + p.arrivalTime + "\t" + p.waitingTime + "\t" + p.turnaroundTime + "\t\t" + p.completionTime);
        }

        System.out.println("Average Waiting Time: " + (totalWT / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));

        sc.close();
    }
}
