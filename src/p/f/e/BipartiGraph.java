package p.f.e;

import java.util.HashMap;
import java.util.Map;
public class BipartiGraph {
	
           private static int X = 0;
	   private static int Y = 0;
          public static double [][] matrix ;
           public static double [][] matrix_test;
	
	//private static double [][] matrix = CSVreader.readCSV();
           private static double [][] recommendationPower ;
           
           
            public static void mat(double [][] m,int x,int y) {
        matrix=m;
              
             X=x;
             Y=y;
                System.out.println(X+"/"+Y);
                System.out.println(matrix[1][2]);
         matrix_test= new double[X][Y];
         recommendationPower = new double [Y][Y];
        
        NewMatrix(matrix_test);
        
       for (int i = 0; i < X; i++) {
         
           for (int j = 0; j < Y; j++) {
              
            if(matrix[i][j]==0){
                
           matrix_test[i][j]=calculateRating(i,j);
            System.out.println("The Rating Expected For User"+i+" Item'"+j+"'is '"+matrix_test[i][j]+"'");
              
           }   
           }System.out.println("-----------------------------");
      } 
        
        new Result_cf(matrix,matrix_test, X, Y,"pr").setVisible(true);
        
        
        
    }
           
	
	
	
	private static Map<Integer,Double> productRatingSum = new HashMap<Integer,Double>();
	private static Map<Integer,Double> userRatingSum = new HashMap<Integer,Double>();	
	
	private static void calculateRatingSum() {
		
		double sum = 0;
		
		//Calculate the sum of all ratings a user gave to items
		for(int i=0;i<Y;i++) {
			for(int j=0;j<X;j++) {
				sum += matrix[i][j];
			}
			userRatingSum.put(i, sum);
			sum = 0;
		}
		
		//Calculate the sum of ratings that have been given to an item
		for(int j=0;j<X;j++) {
			for(int i=0;i<Y;i++) {
				sum += matrix[i][j];
			}
			productRatingSum.put(j, sum);
			sum = 0;
		}
		
//		for (Double value: userRatingSum.values()) {
//        System.out.println(value);
//		}
	}
	 private static void NewMatrix(double [][] matrix_test){
        
        
         for (int i = 0; i < X; i++) {
                    for (int j = 0; j < Y; j++) {
                       matrix_test[i][j]=0;
                    }}
   
        }
	private static void calculateSimilarities() {
		double sum = 0;
		
		calculateRatingSum();
		
		for(int i=0;i<Y;i++) {
			for(int j=0;j<Y;j++) {
				for(int k=0;k<X;k++) {
					sum += (matrix[i][k] * matrix[j][k]) / productRatingSum.get(k);
				}
				recommendationPower[i][j] = sum / userRatingSum.get(i);
				sum = 0;
			}
		}
		
//		for(int i=0;i<3;i++) {
//			for(int j=0;j<3;j++) {
//				System.out.print(recommendationPower[i][j] + " ");
//			}	
//			System.out.println("\n");
//		}
	}
	
	private static double calculateRating(int targetUser,int item) {
		double sum1 = 0;
		double sum2 = 0;
		          System.out.println("hi");
		calculateSimilarities();
		
		for(int j=0;j<Y;j++) {
			if(j != targetUser) {
				sum1 += recommendationPower[targetUser][j] * matrix[j][item];
				sum2 += recommendationPower[targetUser][j];
			}
		}		
                double v=(sum1 / sum2);
		System.out.println("Note = " + v);
                return v;
	}


	
}
