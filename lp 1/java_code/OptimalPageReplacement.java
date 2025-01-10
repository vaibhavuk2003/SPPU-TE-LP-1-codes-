package com.soham;// import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of pages");
        int n = sc.nextInt();
        System.out.println("Enter the size of the frame");
        int nf = sc.nextInt();
        int[] pagerefrencestring = new int[n];
        System.out.println("Enter the page refrence string");
        for(int i=0;i<n;i++){
            pagerefrencestring[i] = sc.nextInt();
        }

        HashSet<Integer> mem = new HashSet<>(nf);
        int pf=0;
        int ph=0;
        for(int i=0;i<n;i++){
            int current = pagerefrencestring[i];
            if(!(mem.contains(current))){
                if(mem.size() < nf){
                    mem.add(current);
                }
                else{
                    int farthest=i;
                    int pagetoreplace=-1;
                    for(int page : mem){
                        int nextuse = findnextuse(pagerefrencestring,i+1,page);
                        if(nextuse > farthest){
                            farthest = nextuse;
                            pagetoreplace = page;
                        }
                    }
                    mem.remove(pagetoreplace);
                    mem.add(current);
                }
                pf++;
            }else{
                ph++;
            }

            System.out.print("frames : ");
            for(int p : mem){
                System.out.print(p+" ");
            }
            System.out.println();
        }
        System.out.println("page faults: "+pf);
        System.out.println("page hits: "+ph);
        sc.close();
    }

    private static int findnextuse(int[] ref,int start,int page){
        for(int i=start;i<ref.length;i++){
            if(ref[i]==page){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    // Sample input: 20 3 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
}