/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.f.e;

import java.util.ArrayList;
import java.util.List;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author YACIN
 */
public class cos {
    
           private static int AxeX = 0;
	   private static int AxeY = 0;
           public static double [][] matrix = new double[AxeX][AxeY];
           public static double [][] matrix_test;

    public static void mat(double [][] m,int x,int y) {
        matrix=m;
          AxeX=x;
          AxeY=y;
         matrix_test= new double[AxeX][AxeY];
        
        NewMatrix(matrix_test);
        
       for (int i = 0; i < AxeX; i++) {
         
           for (int j = 0; j < AxeY; j++) {
              
            if(matrix[i][j]==0){
                
           matrix_test[i][j]=predict_rating(i,j);
            System.out.println("The Rating Expected For User"+i+" Item'"+j+"'is '"+matrix_test[i][j]+"'");
              
           }   
           }System.out.println("-----------------------------");
      } 
        
        new Result_cf(matrix,matrix_test, AxeX, AxeY,"c").setVisible(true);
        
        
        
    }
          
	   public static double degree=0;
           
           public static  int [][] adjMatrix=new int[AxeY][AxeY];
          
           public static  double [][] c=new double[AxeX][AxeY];
           public static double maxval=5;
           
	private static double[] calculateMeanRatings(){
		
		double [] users_mean_ratings = new double[AxeX];	
		
		double rating_mean_user = 0;
		double rating_mean_candid = 0;
		double sum = 0;
		int rated_items=0;		
		
		//Calculate the mean rating for all users
		for(int j=0;j<AxeX;j++) {
			for(int i=0;i<AxeY;i++) {
				if(matrix[j][i] != 0) {
					rated_items++;				
					sum += matrix[j][i];
				}
			}
			
			users_mean_ratings[j] = sum / rated_items;
//			System.out.println(users_mean_ratings[j]);
			rated_items = 0;
			sum = 0;
		}
   
                
		return users_mean_ratings;
	}
        
        public static   void MAE(double [][] m,double [][]mt){
            matrix=mt;
            matrix_test=m;
            
           /* c=matrix_test;
            matrix_test=matrix;
            matrix=c;*/
            
          ArrayList <Double> res= new ArrayList<>();
            double sum=0;
            int count=0;
            double s=2;
      for(double k=0;k<=1;k=k+0.01){
     degree=k;
        // System.out.println("degree="+degree);
      for (int i = 0; i < AxeX; i++) {
         
           for (int j = 0; j < AxeY; j++) {
              
            if(matrix[i][j]==0){
               double pr=predict_rating(i,j);
               if(pr==0.0){ sum=sum+maxval;}
               else{sum=sum+Math.abs(matrix_test[i][j]-pr);}
           
           count++;
             
            }   
           }
      }
      double val=sum/count;
      
      if(s!=val){
          res.add(val);
          s=val;
      }
      
     
      
       sum=0;
       count=0;
     
   }
      
       System.out.println("The Values Of MAE "+res);
      
    LineChart_AWT chart = new LineChart_AWT(
         "neighbors size Vs M A E" ,
         "neighbors size vs M A E",res);

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
      }
        private static void NewMatrix(double [][] matrix_test){
        
        
         for (int i = 0; i < AxeX; i++) {
                    for (int j = 0; j < AxeY; j++) {
                       matrix_test[i][j]=0;
                    }}
   
        }
	private static List<Double> sim(int target_user) {
            
		List<Double> similarity= new ArrayList<Double>();
                
               double upper_part=0;
               double lower_part=0;
               double value; 
               int user; 
               
               double x=0;
               double y=0;
               
               
              for (int p = 0; p < AxeX; p++) {
                  user=p;
                 for (int i = 0; i < AxeY; i++) {
                   
               upper_part=upper_part+
               (matrix[target_user][i])*
               (matrix[user][i]);  
              
                    }
 
               
                for (int j = 0; j < AxeY; j++) {
                    
                    double squre=(matrix[target_user][j]);
                           squre=squre*squre;
               x=x+squre;
                    squre=(matrix[user][j]);
                     squre=squre*squre;
               y=y+squre;
            }
               lower_part=Math.sqrt(x*y);
              if(lower_part==0){value=9;}
              else{ value=upper_part/lower_part;}
              
      
               similarity.add(value);
               
                upper_part=0;
                lower_part=0;
                value=0; 
                x=0;
                y=0;
               
            }               
		return similarity;
	}
	private static List<Double> sim_user(int target_user, int user) {
            
		List<Double> similarity= new ArrayList<Double>();
                
               double upper_part=0;
               double lower_part=0;
               double value; 
               
               
               double x=0;
               double y=0;
               
               
             /* for (int p = 0; p < AxeX; p++) {
                  user=p;*/
                 for (int i = 0; i < AxeY; i++) {
                   
               upper_part=upper_part+
               (matrix[target_user][i])*
               (matrix[user][i]);  
              
                    }
 
               
                for (int j = 0; j < AxeY; j++) {
                    
                    double squre=(matrix[target_user][j]);
                           squre=squre*squre;
               x=x+squre;
                    squre=(matrix[user][j]);
                     squre=squre*squre;
               y=y+squre;
            }
               lower_part=Math.sqrt(x*y);
              if(lower_part==0){value=9;}
              else{ value=upper_part/lower_part;}
              
      
               similarity.add(value);
               
                upper_part=0;
                lower_part=0;
                value=0; 
                x=0;
                y=0;
               
            //}               
		return similarity;
	}
        private static double predict_rating(int id,int id_item) { 
          
          List <Integer> list_id= new ArrayList<>();
          List <Double> list_id_item= new ArrayList<>();
          for (int i = 0; i <AxeX; i++) {
              if(i!=id && matrix[i][id_item]!=0){
                 list_id.add(i);
                 list_id_item.add(matrix[i][id_item]);
              }
          }
                                
        //second stupe filtring users
        
        List <Integer> list_idf= new ArrayList<>();
        List <Double> list_id_itemf= new ArrayList<>();
        int p=0;
        
          for (int i = 0; i < list_id.size(); i++) {
             
            while(p <AxeY && !(matrix[id][p]!=0 && matrix[list_id.get(i)][p]!=0) ){ p++;}
      
              if(p!=(AxeY)){                  
              list_idf.add(list_id.get(i));
              list_id_itemf.add(list_id_item.get(i));
              }p=0;
          }
          
         
                   
         // calculate 
         
         double a=0;
         double b=0;
         double v;
         
          for (int i = 0; i <list_idf.size(); i++) {
           if(sim(id).get(list_idf.get(i))>degree){
              
                  a=a+(sim(id).get(list_idf.get(i))*list_id_itemf.get(i));
                  b=b+sim(id).get(list_idf.get(i));
                 
           }
          }
         
                  v=a/b;
                  
                  if(Double.isNaN(v)){return 0;}
                  else{
                        return v;
                  }
                
	}
}
