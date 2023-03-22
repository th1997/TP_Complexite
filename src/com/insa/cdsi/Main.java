package com.insa.cdsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    /**
     *
    */
    public static void colonneZero(int[][] matrice1, int posUn){
        for(int i = 0; i < matrice1.length; i++){
            matrice1[i][posUn] = 0;
        }
    }
    public static int[][] deleteLine(int rand, int[][] matrice){
        int[][] matrice2 = new int[matrice.length-1][matrice[0].length];

        for(int i = 0; i < rand ; i++){
            System.arraycopy(matrice[i], 0, matrice2[i], 0, matrice[0].length);
        }
        for(int i = rand+1; i < matrice.length ; i++){
            System.arraycopy(matrice[i], 0, matrice2[i - 1], 0, matrice[0].length);
        }
        return matrice2;
    }

    /**
     * Sélectionne les items répondants aux conditions et les affichent.
     * @param items Liste des Items.
     * @param knapsack_size Taille maximale du sac.
     * */
    public static void selectItems(ArrayList<Item> items, int knapsack_size){
        int knapsack = 0;
        int i = items.size()-1;
        items.sort(Comparator.comparing(s -> s.ratio));
        while(knapsack + items.get(i).poid < knapsack_size && i >= 0){
            if(items.get(i).poid != 0){
                knapsack = knapsack + items.get(i).poid;
                System.out.println("Item " +items.get(i).ID +": poid total : " +items.get(i).poid +", profit : " +items.get(i).profit +", ratio : " +items.get(i).ratio);
            }
            i--;
        }
        System.out.println(knapsack_size +" : " +knapsack);
    }

    public static void main(String[] args) throws IOException {
        // Mise en place de la structure.
        FileSUKP file = new FileSUKP();

        int m = file.getM();
        int n = file.getN();
        int knapsack_size = file.getKnapsack_size();
        int[][] matrice = file.getMatrice();
        ArrayList<Item> Items = file.getItems();
        ArrayList<Integer> poids = file.getPoids();
        ArrayList<Double> ratios = new ArrayList<>();

        for(int i = 0; i < matrice.length; i++){
            int rand = (int)(Math.random() * (matrice.length-i));
            Items.get(rand).calcPoid(matrice[rand],poids);
            for(int j =0; j < n;j++){
                if(matrice[rand][j] == 1){
                    colonneZero(matrice, j);
                }
            }
            matrice = deleteLine(rand,matrice);
        }

        selectItems(Items,knapsack_size);

        /*System.out.println("*******************************************");
        for(int i = 0; i < m; i++){
            System.out.println("Item " +Items.get(i).ID +": poid total : " +Items.get(i).poid +", profit : " +Items.get(i).profit +", ratio : " +Items.get(i).ratio);
        }*/
    }
}
