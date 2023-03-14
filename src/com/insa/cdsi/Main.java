package com.insa.cdsi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // Création d’un fileReader pour lire le fichier
        FileReader fileReader = new FileReader("SUKP_instances_60\\Instances of Set I\\sukp 85_100_0.10_0.75.txt");
        // Création d’un bufferedReader qui utilise le fileReader
        BufferedReader reader = new BufferedReader (fileReader);
        String line = "", lineTmp;
        int m,n,knapsack_size;
        ArrayList<Item> Items = new ArrayList<>();
        ArrayList<Integer> poids = new ArrayList<>();
        int[][] matrice;

        try {
            // On ignore les 2 premieres lignes vide du fichier.
            for(int i = 0; i <= 2; i++){ line = reader.readLine(); }
            String[] words;

            /***********Récuperation de m, n et de la taille du sac.**********/

            // Remplacer chaque nombre non numérique par un espace
            lineTmp = line.replaceAll("[^\\d]", " ");
            // Supprimer les espaces de début et de fin
            lineTmp = lineTmp.trim();
            // Remplacer les espaces consécutifs par un seul espace.
            lineTmp = lineTmp.replaceAll(" +", " ");
            // Découpage de la ligne avec l'espace comme délimiteur.
            words = lineTmp.split(" ", 0);
            // Passage de String a Integer et récuperation des valeurs.
            m = Integer.parseInt(words[0]);
            n = Integer.parseInt(words[1]);
            knapsack_size = Integer.parseInt(words[2]);

            System.out.println("Nombre d'items : " +m +"\nNombre d'elements : " +n +"\nTaille du sac : " +knapsack_size);

            /***********Récuperation du profit des 85 items.**********/

            // On ignore les 2 lignes suivantes qui ne sont pas à utiliser.
            for(int i = 0; i <= 2; i++){ line = reader.readLine(); }

            // Découpage de la ligne avec l'espace comme délimiteur.
            words = line.split(" ", 0);

            // Passage de String a Integer et création des Items.
            for (String s : words) {
                Items.add(new Item(Integer.parseInt(s)));
            }

            /***********Récuperation du poids des 100 éléments.**********/

            // On ignore les 2 lignes suivantes qui ne sont pas à utiliser.
            for(int i = 0; i <= 2; i++){ line = reader.readLine(); }

            // Découpage de la ligne avec l'espace comme délimiteur.
            words = line.split(" ", 0);

            // Passage de String a Integer et ajouts des poids.
            for (String s : words) {
                poids.add(Integer.parseInt(s));
            }

            /***********Récuperation de la matrice.**********/

            matrice = new int[m][n];

            // On ignore les 2 lignes suivantes qui ne sont pas à utiliser.
            for(int i = 0; i <= 1; i++){ line = reader.readLine(); }

            for(int i = 0; i < m; i++) {
                line = reader.readLine();
                words = line.split(" ", 0);

                for(int j = 0; j < n; j++) {
                    matrice[i][j] = Integer.parseInt(words[j]);
                }
            }

            for(int i = 0; i < m; i++){
                Items.get(i).calcPoid(matrice[i],poids);
                double ratio = Math.round(1000.0*((double)(Items.get(i).profit))/((double)Items.get(i).poid))/1000.0;
                System.out.println("Item " +(i+1) +": poid total : " +Items.get(i).poid +", profit : " +Items.get(i).profit +", ratio : " +ratio);
            }
        }
        catch (IOException e) {
                e.printStackTrace();
        }
        reader.close();
    }
}
