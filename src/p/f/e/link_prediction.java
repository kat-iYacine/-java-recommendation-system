package package1;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roott
 */
public class link_prediction {
    
              public static  double [][] graph= {
                                                 { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
	                                         { 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
	                                    	 { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
						 { 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
						 { 1, 1, 0, 0, 0, 0, 0, 0, 1, 0},
					         { 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                                                 { 0, 1, 1, 0, 0, 0, 0, 0, 1, 0},
						 { 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
						 { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
					         { 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                                                                };
              
              
                public static  double [][] graph1= {
                                                 { 0, 1, 1, 1, 0, 0 },
	                                         { 1, 0, 0, 0, 1, 0 },
	                                    	 { 1, 0, 0, 0, 1, 0 },
						 { 1, 0, 0, 0, 0, 1 },
						 { 0, 1, 1, 0, 0, 1 },
					         { 0, 0, 0, 1, 1, 0 },
                                              
                                                                                };
             public static int len=graph.length;   
             
             
             public static  void UpDate_graph(){
    
                   int marker=0;    
                 
                          for (int i = 0; i < len; i++) {
                              for (int j = 0; j < len; j++) {
                              
                                 if(j!=i && graph[i][j]==0){
                                  
                                while(marker<len && graph[i][j]==1 && graph[j][marker]==1){
                                    
                                    marker++;
                                }   
                                
                                if(marker!=len){
                                
                                  if(A_A_sim(i,j)){  graph[i][j]=2;}
                                    
                                }marker=0;
                                
                                 }
                                  
                              }
                                }
                                                    } 
             
             public static boolean A_A_sim(int x, int y){
            double sum=0;
              double sim=0;
              
                 for (int i = 0; i < len; i++) {
                     if (graph[x][i]==1 && graph[y][i]==1) {
                       
                         for (int j = 0; j < len; j++) {
                             sum=sum+graph[i][j];
                         }
                       
                         sim=sim+(1/Math.log(sum));
                         sum=0;
                     }
    
                 }
               
                 if(sim>=1){return true;}
                    else { return false;}
             }
             
    
     public static void main(String[] args) {
        // TODO code application logic here
       UpDate_graph();
         for (int i = 0; i < len; i++) {
             for (int j = 0; j <len; j++) {
                 System.out.print(graph[i][j]+" ");
             }System.out.println();
         }
     }
}