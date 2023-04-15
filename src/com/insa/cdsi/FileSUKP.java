package com.insa.cdsi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileSUKP {
    private int n; // Nombre d'element
    private int m; // Nombre d'item
    private int knapsack_size; // Taille maximale du sac
    private ArrayList<Item> items; // Liste des Items
    private ArrayList<Integer> poids; // Liste des poids
    private int[][] matrice; // Matrice des Relations

    public FileSUKP() throws IOException {
        items = new ArrayList<>();
        poids = new ArrayList<>();
        ReadFile();
    }

    /**
     * Lecture du fichier et assignation des valeurs du fichier dans des variables.
     */
    public void ReadFile() throws IOException {
        // Création d’un fileReader pour lire le fichier
        FileReader fileReader = new FileReader("SUKP_instances_60\\Instances of Set I\\sukp 300_285_0.15_0.85.txt");
        // Création d’un bufferedReader qui utilise le fileReader
        BufferedReader reader = new BufferedReader (fileReader);
        String line = "", lineTmp;

        try{
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
            matrice = new int[m][n];

            /***********Récuperation du profit des 85 items.**********/

            // On ignore les 2 lignes suivantes qui ne sont pas à utiliser.
            for(int i = 0; i <= 2; i++){ line = reader.readLine(); }

            // Découpage de la ligne avec l'espace comme délimiteur.
            words = line.split(" ", 0);

            // Passage de String a Integer et création des Items.
            for (int i = 0; i < words.length;i++) {
                items.add(new Item(Integer.parseInt(words[i]),i));
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

            // On ignore les 2 lignes suivantes qui ne sont pas à utiliser.
            for(int i = 0; i <= 1; i++){ line = reader.readLine(); }

            for(int i = 0; i < m; i++) {
                line = reader.readLine();
                words = line.split(" ", 0);

                for (int j = 0; j < n; j++) {
                    matrice[i][j] = Integer.parseInt(words[j]);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getKnapsack_size() {
        return knapsack_size;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Integer> getPoids() {
        return poids;
    }

    public int[][] getMatrice() {
        return matrice;
    }
}
