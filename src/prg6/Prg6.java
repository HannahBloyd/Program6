/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prg6;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author hanna
 */
public class Prg6 {


/**
 *
 * @author Hannah Bloyd
 */
    
    int CITI;
    int[][] adjacency;
    int bestCost=Integer.MAX_VALUE;
    ArrayList<Integer> tour;
    
    /**
     *
     * @param N number of cities
     */
    public Prg6(int N)
    {
        CITI=N;
        adjacency= new int[N][N];
        tour = new ArrayList<Integer>();
        
    }//constructor

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Prg6 a = new Prg6(12);
        a.populateMatrix();
        a.MST();
        
        
        
    }//main
    
    /**
     *
     */
    public void populateMatrix() { //CITI is the number of cities and it is a constant  
        File f = new File("tsp" + CITI + ".txt");
        try{
            Scanner input=new Scanner(f);
            
            int value, i, j;
            for (i = 0; i < CITI && input.hasNext(); i++) {
                for (j = i; j < CITI && input.hasNext(); j++) {
                    if (i == j) {
                        adjacency[i][j] = 0;
                    } else {
                        value = input.nextInt();
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    }//else
                }//forj
            }//fori
        }//try
        
        catch (Exception e){
            e.printStackTrace();
        }

}//populate Matrix
    
    /**
     *
     * @param tour array of complete tour
     * @return int cost of current tour
     */
    public int computeCost(ArrayList<Integer> tour){
        int totalCost=0;
        for(int i=0; i<tour.size()-1; i++)
            totalCost+=adjacency[tour.get(i)][tour.get(i+1)];
        
        if(tour.size()==CITI)
            totalCost+=adjacency[tour.get(tour.size()-1)][0];
        
        return totalCost;
        
    }//computeCost
    
    public void MST (){
        Stack pathStack = new Stack();
        int[] visitedCities = new int[CITI];
        
        //assume start city is city0
        visitedCities[0] = 1;
        pathStack.push(0);
        int closestCity = 0;
        boolean minFlag = false;
        int currentCity;
        System.out.println("0");
        
        while (!pathStack.isEmpty()){
            currentCity = (int) pathStack.peek();
            int min = Integer.MAX_VALUE;
            for (int x =1; x < CITI; x++){
                if (adjacency[x][currentCity] != 0 && visitedCities[x] != 1){
                    if (adjacency[x][currentCity] < min){
                        min = adjacency[x][currentCity];
                        closestCity = x;
                        minFlag = true;
                    }//if
                }//if
            }//for
            
            if (minFlag){
                visitedCities[closestCity] = 1;
                pathStack.push(closestCity);
                System.out.println(closestCity);
                minFlag = false;
                tour.add(closestCity);
                
            }//if
            pathStack.pop();
        }//while
        
        System.out.println("Cost: " + computeCost(tour));
    }//MST

}//class


