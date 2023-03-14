package com.insa.cdsi;

import java.util.ArrayList;

public class Item {
    public int profit;
    public int poid;

    public Item(int profit){
        this.profit = profit;
        poid = 0;
    }

    public void calcPoid(int[] mat, ArrayList<Integer> poids){
        for(int i = 0; i < mat.length; i++){
            if(mat[i] == 1){
                poid = poid + poids.get(i);
            }
        }
    }
}
