package com.insa.cdsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

    /**
     * Permet de mettre une colonne entière à 0 en fonction de l'indice donné.
     * @param matrice Matrice à modifier.
     * @param posUn Indice de la colonne à supprimer.
     * @return La matrice modifiée.
     **/
    public static int[][] colonneZero(int[][] matrice, int posUn){
        for(int i = 0; i < matrice.length; i++){
            matrice[i][posUn] = 0;
        }
        return matrice;
    }

    /**
     * Permet de supprimer une ligne d'une matrice en fonction de l'indice donné.
     * @param matrice Matrice à modifier.
     * @param rand Indice de la ligne à supprimer.
     * @return La matrice modifiée.
     **/
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
     * @param listItem List des Items a garder dans le sac.
     * @param nb ID (numéro) de l'Item.
     **/
    public static HashMap<Integer,ArrayList<Item>> selectItems(ArrayList<Item> items, int knapsack_size, HashMap<Integer,ArrayList<Item>> listItem, int nb){
        int knapsack = 0;
        int i        = items.size()-1;
        int profitTotal = 0;
        ArrayList<Item> listItem2 = new ArrayList<>();

        // On trie les items en fonction du ratio.
        items.sort(Comparator.comparing(s -> s.ratio));

        //Tant que la taille de notre sac est inferieur à la capacité maximale, on boucle et que i est positif.
        while( i >= 0 && knapsack + items.get(i).poid < knapsack_size){
            //On vérifie si le poid est différent de 0.
            if(items.get(i).poid != 0){
                knapsack = knapsack + items.get(i).poid; // On ajoute le poid de l'item i dans le sac.
                profitTotal = profitTotal + items.get(i).profit; // On cumule les profits.
                listItem2.add(items.get(i));
            }
            i--;
        }
        listItem.put(nb,listItem2);
        return listItem;
    }

    /**
     * Fonction permettant de trouver le résultat le plus optimisé
     * @param listItem Liste de tout les Items
     * @param nb Nombre de lancé
     * @return Le numéro du lancé ayant le plus faible ratio
     */
    public static int getMostOptimised(HashMap<Integer, ArrayList<Item>> listItem, int nb) {
        double ratioTotal[][] = new double[nb][2];
        double ratioMin;
        int numRatioMin = 0;

        for (int i = 0; i < nb; i++) {
            ratioTotal[i][1] = i;
            for (int j = 0; j < listItem.get(i).size(); j++) {
                ratioTotal[i][0] = ratioTotal[i][0] + listItem.get(i).get(j).ratio;
            }
        }
        ratioMin = ratioTotal[0][0];

        for (int i = 1; i < nb; i++) {
            if (ratioMin > ratioTotal[i][0]) {
                ratioMin = ratioTotal[i][0];
                numRatioMin = i;
            }
        }
        return numRatioMin;
    }

    public static void main(String[] args) throws IOException {
        int nb = 0;
        HashMap<Integer, ArrayList<Item>> listItem = new HashMap<>();

        while (nb < 10) {

            // Mise en place de la structure.
        FileSUKP file = new FileSUKP();

        int     m             = file.getM();
        int     n             = file.getN();
        int     knapsack_size = file.getKnapsack_size();

            int[][] matrice = file.getMatrice().clone();
            ArrayList<Item> Items = file.getItems();
            ArrayList<Integer> poids = file.getPoids();

            for (int i = 0; i < m; i++) {
                int rand = (int) (Math.random() * (m - i));
                //Calcul du poid de l'Item à la position rand.
                Items.get(rand).calcPoid(matrice[rand], poids);

                for (int j = 0; j < n; j++) {
                    //Si on trouve un 1 dans la ligne on met la colonne à 0.
                    if (matrice[rand][j] == 1) {
                        matrice = colonneZero(matrice, j);
                    }
                }
                deleteLine(rand, matrice);
            }
            listItem = selectItems(Items, knapsack_size, listItem, nb);
            nb++;
        }
        for(int i = 0; i < 10; i++){
            System.out.println("Resultat du lance " +(i+1) +": " +listItem.get(i));
        }
        System.out.println("\nLe lance le plus optimise est le suivant : "+listItem.get(getMostOptimised(listItem,nb)));
    }
}
