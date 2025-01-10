package FIFOPageReplacement;
import java.util.Scanner;

public class FIFOPageReplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Frames: ");
        int numberofframes = sc.nextInt();

        System.out.println("Enter number of pages:");
        int pages = sc.nextInt();
        int[] ReferenceString = new int[pages];

        System.out.println("Enter Page Reference String: ");
        for (int i = 0; i < pages; i++) {
            ReferenceString[i] = sc.nextInt();

        }
        int hit = 0;
        int miss = 0;
        int currentindex = 0;

        int[] Frames = new int[numberofframes];
        for(int i=0;i<numberofframes;i++){
            Frames[i]=-1;
        }

        for (int i = 0; i < pages; i++) {
            boolean isHIT=false;

            for (int j = 0; j < numberofframes; j++) {

                if (ReferenceString[i] == Frames[j]) {
                    hit++;
                    isHIT=true;
                    break;
                } 
            }
            if(isHIT==false){
                Frames[currentindex]=ReferenceString[i];
                currentindex=(currentindex+1)%numberofframes;
                miss++;
            }

            System.out.println("Frames: ");

            for(int j=0;j<numberofframes;j++){
                if(Frames[j]!=-1){
                    System.out.print(Frames[j]+" ");

                }
                else{
                    System.out.print("-");
                }
            }
            System.out.println("\n");
         

        }

        System.out.println("Number Of Hits: " + hit);
        System.out.println("Number of Page Faults: " + miss);
        sc.close();
	}

}
