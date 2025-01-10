package OptimalPageReplacement;
import java.util.*;

public class OptimalPageReplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Frames: ");
        int numberofframes = sc.nextInt();

        System.out.println("Enter Number Of Pages: ");
        int pages = sc.nextInt();

        int ReferenceString[] = new int[pages];
        System.out.println("Enter Pages in Reference string:");
        for (int i = 0; i < pages; i++) {

            ReferenceString[i] = sc.nextInt();
        }

        List<Integer> Frames = new ArrayList<>(numberofframes);
        int hit = 0;
        int miss = 0;

        for (int i = 0; i < pages; i++) {

            boolean isHit = false;

            if (Frames.contains(ReferenceString[i])) {
                hit++;
                isHit = true;
            }
            if (isHit == false) {
                miss++;

                if (Frames.size() == numberofframes) {

                    int farthestindex = -1;
                    int pagetoreplace = -1;

                    for (int page : Frames) {
                        int nextuse = Integer.MAX_VALUE;

                        for (int j = 0; j < pages; j++) {

                            if (ReferenceString[j] == page) {
                                nextuse = j;
                                break;
                            }
                        }

                        if (nextuse == Integer.MAX_VALUE) {
                            pagetoreplace = page;
                            break;
                        }

                        if (nextuse > farthestindex) {
                            farthestindex = nextuse;
                            pagetoreplace = page;
                        }

                    }
                    Frames.remove(Integer.valueOf(pagetoreplace));

                }
                Frames.add(ReferenceString[i]);
            }

            System.out.println(Frames);

        }

        System.out.println("Number of hits: " + hit);
        System.out.println("Number Of Page faults:" + miss);
        sc.close();

	}

}
