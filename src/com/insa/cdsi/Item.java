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

    /**
     * Calcul le poid d'un Item ainsi que le ratio profit/poid.
     * @param mat Ligne a calculer le poid.
     * @param poids Liste des poids.
     */
    public void calcPoid(int[] mat, ArrayList<Integer> poids){
        for(int i = 0; i < mat.length; i++){
            if(mat[i]== 1){
                poid = poid + poids.get(i);
            }
        }
        ratio = (((double)(profit)) / ((double)(poid)));
    }

    @Override
    public String toString() {
        return "Item{" +
                "ID=" + ID +
                ", profit=" + profit +
                ", poid=" + poid +
                ", ratio=" + ratio +
                '}';
    }
}
