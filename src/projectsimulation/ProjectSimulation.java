/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsimulation;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author mohamed hussein
 */
public class ProjectSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n, i = 0;
        System.out.print("Please Enter the number of DEMAND FOR ACE DRILL");
        n = input.nextInt();
        int demand1[] = new int[n];
        for (i = 0; i < n; i++) {
            demand1[i] = i;
        }
        int j = 0, sum = 0;
        int frequency1[] = new int[n];
        System.out.println("EnterFREQUENCY:");
        for (j = 0; j < n; j++) {
            frequency1[j] = input.nextInt();
            sum += frequency1[j];
        }
        System.out.println("The sum are " + sum);
        System.out.println("\n");

        float probability1[] = new float[frequency1.length];
        PROBABILITY(probability1, frequency1, sum);
        float cumlative1[] = new float[frequency1.length];
        Cummulative(cumlative1, probability1);
        int start_interval1[] = new int[frequency1.length];
        int second_interval1[] = new int[frequency1.length];
        interval(start_interval1, second_interval1, cumlative1);
        System.out.print("DEMAND" + " " + "FREQUENCY(DAYS)" + " " + "PROBABILITY" + " " + "CUMULATIVE" + " " + "INTERVAL OF RANDOM NUMBERS" + "\n");

        for (i = 0; i < n; i++) {
            System.out.print(demand1[i] + "\t" + frequency1[i] + "\t" + "\t" + probability1[i] + "\t" + "\t" + cumlative1[i] + "\t" + "\t" + start_interval1[i] + "  to  " + second_interval1[i] + "\n");
        }
        int x;
        System.out.print("Please Enter the number of Lead time (Days)");
        x = input.nextInt();
        int leadtime_table2[] = new int[x];
        for (i = 0; i < x; i++) {
            leadtime_table2[i] = i + 1;
        }
        int sum1 = 0;
        int frequncy2[] = new int[x];
        System.out.println("EnterFREQUENCY:");
        for (j = 0; j < x; j++) {
            frequncy2[j] = input.nextInt();
            sum1 += frequncy2[j];
        }
        System.out.println("The sum are " + sum1);
        float probability2[] = new float[frequncy2.length];
        PROBABILITY(probability2, frequncy2, sum1);
        float cumulative2[] = new float[frequncy2.length];
        Cummulative(cumulative2, probability2);
        int start_interval2[] = new int[frequncy2.length];
        int second_interval2[] = new int[frequncy2.length];
        interval(start_interval2, second_interval2, cumulative2);
        System.out.print("LeadTime" + " " + "FREQUENCY(DAYS)" + " " + "PROBABILITY" + " " + "CUMULATIVE" + " " + "INTERVAL OF RANDOM NUMBERS" + "\n");

        for (i = 0; i < x; i++) {
            System.out.print(leadtime_table2[i] + "\t" + frequncy2[i] + "\t" + "\t" + probability2[i] + "\t" + "\t" + cumulative2[i] + "\t" + "\t" + start_interval2[i] + "  to  " + second_interval2[i] + "\n");
        }

        int ORDER_QUANTITY, REORDER_POINT, days;
        System.out.print("Please Enter ORDER QUANTITY");
        ORDER_QUANTITY = input.nextInt();
        System.out.print("Please Enter REORDER_POINT");
        REORDER_POINT = input.nextInt();
        System.out.print("Please Enter  simulated for a n day period");
        days = input.nextInt();
        int UNITS_RECEIVED[] = new int[days];
        int BEGINNING[] = new int[days];
        int ENDING[] = new int[days];
        int LOST[] = new int[days];
        String ORDER[] = new String[days];
        int RANDOM[] = new int[days];
        int LEADTIME[] = new int[days];
        int leadtime[] = new int[days];
        String comparison[] = new String[days];

        int Days[] = new int[days];
        for (i = 0; i < days; i++) {
            Days[i] = i+1;
        }
         for (i = 0; i < days; i++) {
            comparison[i] = "false";
        }
        int RAN[] = new int[days];
        System.out.println("Enter the first RAN ");
        for (i = 0; i < days; i++) {
            RAN[i] = input.nextInt();
        }
        int RAN2[] = new int[days];
        System.out.println("Enter the second RAN ");
        for (i = 0; i < days; i++) {
            RAN2[i] = input.nextInt();
        }

        int demand[] = new int[days];
        Demand(start_interval1, second_interval1, RAN, demand1, demand);
        Demand(start_interval2, second_interval2, RAN2, leadtime_table2, leadtime);
        simulation(UNITS_RECEIVED,BEGINNING,demand,ENDING,LOST,ORDER, RANDOM,LEADTIME,ORDER_QUANTITY,REORDER_POINT,RAN2,leadtime);
        System.out.println("The  days are " + Arrays.toString(Days));
        System.out.println("The  UNITS_RECEIVED are " + Arrays.toString(UNITS_RECEIVED));
        System.out.println("The  BEGINNING are " + Arrays.toString(BEGINNING));
        System.out.println("The  RAN are " + Arrays.toString(RAN));
        System.out.println("The demand  are " + Arrays.toString(demand));
        System.out.println("The  ENDING are " + Arrays.toString(ENDING));
        System.out.println("The  LOST are " + Arrays.toString(LOST));
        System.out.println("The  ORDER are " + Arrays.toString(ORDER));
        System.out.println("The  RANDOM are " + Arrays.toString(RANDOM));
        System.out.println("The  LEADTIME are " + Arrays.toString(LEADTIME));

    }

    public static void simulation(int unitReci[], int begin[], int Demand[], int ending[], int lost[],
             String order[], int RANDOM[], int LEADTIME[], int orderQunatity, int reorderPoint, int RAN2[],int leadTime[] ) {
        unitReci[0] = 0;
        begin[0] = orderQunatity;
        int j;
        for (int i = 0; i < unitReci.length; i++) {
            if(i != 0)
                begin[i] = unitReci[i] + ending[i-1];
            ending[i] = begin[i] - Demand[i];
            if (ending[i] < reorderPoint) {
                if (ending[i] < 0) {
                    lost[i] = ending[i] * -1;
                    ending[i] = 0;
                }
                for(j=i;j>=0;j--)
                    if(LEADTIME[j] != 0)
                        break;
                if( j>=0 &&(leadTime[j] + j+ 1) > i){
                    order[i] = "NO";
                    continue;
                }
                order[i] = "YES";
                LEADTIME[i] = leadTime[i];
                RANDOM [i] = RAN2[i];
                if (leadTime[i] + 1 + i < unitReci.length) {
                    unitReci[leadTime[i] + i+ 1] = orderQunatity;
                }
            } else {
                order[i] = "NO";
                lost[i] = 0;
            }
        }
    }

    public static void PROBABILITY(float arr1[], int arr[], int sum) {
        int i;
        for (i = 0; i < arr.length; i++) {
            arr1[i] = (float) arr[i] / sum;
        }
    }

    public static void Cummulative(float arr1[], float arr[]) {
        arr1[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr1[i] = arr[i] + arr1[i - 1];
        }
    }

    public static void interval(int inter1[], int inter2[], float cumm[]) {
        inter1[0] = 1;
        inter2[0] = (int) (cumm[0] * 100);
        for (int i = 1; i < cumm.length; i++) {

            inter1[i] = inter2[i - 1] + 1;
            inter2[i] = (int) (cumm[i] * 100);
        }
    }

    public static void Demand(int inter1[], int inter2[], int ran[], int dem[], int mand[]) {

        for (int i = 0; i < ran.length; i++) {
            for (int j = 0; j < inter1.length; j++) {
                if (ran[i] >= inter1[j] && ran[i] <= inter2[j]) {
                    mand[i] = dem[j];
                    break;
                }
            }
        }
    }
}
