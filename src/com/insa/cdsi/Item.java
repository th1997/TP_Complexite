package com.insa.cdsi;

import java.util.ArrayList;

public class Item {
    public int ID;
    public int profit;
    public int poid;
    public double ratio;

    public Item(int profit, int ID){
        this.profit = profit;
        poid = 0;
        ratio = 0;
        this.ID = ID;
    }

    public void calcPoid(int[] mat, ArrayList<Integer> poids){
        for(int i = 0; i < mat.length; i++){
            if(mat[i]== 1){
                poid = poid + poids.get(i);
            }
        }
        ratio = (((double)(profit)) / ((double)(poid)));
    }
}
