package p.f.e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jfree.ui.RefineryUtilities;

public class Pearson {
    

	private static int X = 0;
	private static int Y = 0;
        public static double [][] matrix = new double[X][Y];
        public static double [][] matrix_test;
         public static double maxval=5;
         public static double degree=0;
 
    public static void mat(double [][] m,int x,int y) {
        matrix=m;
        X=x;
        Y=y;
             matrix_test=new double[X][Y];
             
        NewMatrix(matrix_test);
        
        for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                       if(matrix[i][j]==0){
                    matrix_test[i][j]= predictRating(i, j);
                       }
                }
            }   
         new Result_cf(matrix,matrix_test, X, Y,"p").setVisible(true);
        
    }

	
        
	
	private static double[] calculateMeanRatings(){
		
		double [] users_mean_ratings = new double[Y];	
		
		double rating_mean_user = 0;
		double rating_mean_candid = 0;
		double sum = 0;
		int rated_items=0;		
		
		//Calculate the mean rating for all users
		for(int i=0;i<Y;i++) {
			for(int j=0;j<X;j++) {
				if(matrix[i][j] != 0) {
					rated_items++;				
					sum += matrix[i][j];
				}
			}
			
			users_mean_ratings[i] = sum / rated_items;
//		System.out.println(users_mean_ratings[i]);
			rated_items = 0;
			sum = 0;
		}
		return users_mean_ratings;
	}
	
	private static List<Integer> filterSimilarUsers(int targetUser,int article){
		List<Integer> similarUsers = new ArrayList<Integer>();
		int count = 0;
		
		for(int i=0;i<Y;i++) {
			for(int j=0;j<X;j++) {
				if(i != targetUser)
					if(matrix[i][article] != 0 && matrix[i][j] != 0 && matrix[targetUser][j] != 0) {
						count++;
					}
			}
			if(count > 0) {
				similarUsers.add(i);
				count = 0;		
			}				
		}
		
//		for (Iterator iterator = similarUsers.iterator(); iterator.hasNext();) {
//			Integer integer = (Integer) iterator.next();
//			System.out.println(integer);
//		}
		
		return similarUsers;
	}
	
	private static List<Double> calculSimilarity(int targetUser,int article) {
		double [] users_mean_ratings = calculateMeanRatings();
		double up = 0;
		double down1 = 0;
		double down2= 0;
		double down_sqrt = 0;
		double sim = 0;
		List<Double> similarities = new ArrayList<Double>();
		List<Integer> similarUsers = filterSimilarUsers(targetUser, article);
		
		for(int i=0;i<similarUsers.size();i++) {
			for(int j=0;j<X;j++) {
				if(matrix[targetUser][j] != 0 && matrix[similarUsers.get(i)][j] != 0) {
					up += (matrix[targetUser][j] - users_mean_ratings[targetUser]) *  (matrix[similarUsers.get(i)][j] - users_mean_ratings[similarUsers.get(i)]);
					down1 += (matrix[targetUser][j] - users_mean_ratings[targetUser]) * (matrix[targetUser][j] - users_mean_ratings[targetUser]);
					down2 += (matrix[similarUsers.get(i)][j] - users_mean_ratings[similarUsers.get(i)]) * (matrix[similarUsers.get(i)][j] - users_mean_ratings[similarUsers.get(i)]);
				}			
			}	
			if(up / Math.sqrt(down1 * down2) ==1)
				similarities.add(up / Math.sqrt(down1 * down2));
		}		
		
//		for (Iterator iterator = similarities.iterator(); iterator.hasNext();) {
//			Double integer = (Double) iterator.next();
//			System.out.println(integer);
//		}
		
		return similarities;
	}
        
        private static void NewMatrix(double [][] matrix_test){
        
        
         for (int i = 0; i < X; i++) {
                    for (int j = 0; j < Y; j++) {
                       matrix_test[i][j]=0;
                    }}
   
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
      for (int i = 0; i < X; i++) {
         
           for (int j = 0; j < Y; j++) {
              
            if(matrix[i][j]==0){
               double pr=predictRating(i,j);
               if(pr==0.0){ sum=sum+maxval;}
               else{sum=sum+Math.abs(matrix_test[i][j]-pr);}
           
           count++;
             
            }   
           }
      }
      double val=sum/count;
      
      //if(s!=val){
          res.add(val);
          s=val;
      //}
      
     
      
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
	
	public static double predictRating(int targetUser,int article) {
		
		double [] userMeanRatings = calculateMeanRatings();
		List<Integer> similarUsers = filterSimilarUsers(targetUser, article);
		List<Double> similarities = calculSimilarity(targetUser, article);		
		double lowerPart = 0;
		double upperPart = 0;
		
		if(similarities.size() != 0) {
			for(int i=0;i<similarities.size();i++) {
				lowerPart+=similarities.get(i);
			}
			
			for(int i=0;i<similarities.size();i++) {
				upperPart += similarities.get(i) * (matrix[similarUsers.get(i)][article] - userMeanRatings[similarUsers.get(i)]);
			}
			
                        double div=(upperPart / lowerPart)+ userMeanRatings[targetUser];
                        
                           if(Double.isNaN(div)){//System.out.println("User " + targetUser + " gave article " + article + ":  0" );
                                                 return 0;}
                           else{//System.out.println("User " + targetUser + " gave article " + article + ":  " + div );
                           return div;}		
		}
                else {
			//System.out.println("User " + targetUser + " gave article " + article + ":  0");
                        return 0;
		}
	
	}
}