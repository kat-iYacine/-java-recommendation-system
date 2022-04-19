package package1;

import java.util.ArrayList;
import java.util.List;

// Works with sim between user - user

public class BipartiGraphJaccardV2 {
	
	private static final int X = 10;
	private static final int Y = 10;
	
	private static double [][] matri  = {{2,1,0,3,4},
										  {1,1,3,0,2},
										  {2,3,2,3,0},
										  {5,1,3,1,4},
										  {1,1,0,0,4}};
	
	private static double [][] matrix = {    { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	                                         { 0, 6, 0, 0, 0, 0, 0, 0, 0, 0},
	                                    	 { 0, 0, 7, 0, 0, 0, 0, 0, 0, 5},
						 { 0, 0, 0, 4, 0, 0, 0, 0, 0, 0},
						 { 0, 0, 0, 0, 5, 0, 0, 0, 0, 0},
					         { 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                                                 { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
	                                    	 { 0, 0, 0, 0, 0, 0, 0, 5, 0, 0},
						 { 0, 0, 0, 0, 0, 0, 0, 0, 3, 0},
						 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},};
	
	private static double [][] similarityMatrix = new double [Y][Y];
	private static List <Integer> aNeighbours = new ArrayList<Integer>();
	private static List <Integer> bNeighbours = new ArrayList<Integer>();
	
	private static void calcuateSimilarity(int i, int j) {		
		
		double upper_part = 0;
		double lower_part = 0;
		int size = 0;	
		
		getNeighbours(i, j);
		
		if(aNeighbours.size() < bNeighbours.size() || aNeighbours.size() == bNeighbours.size())
			size = aNeighbours.size();
		else 
			size = bNeighbours.size();
		
		for(int s : aNeighbours) {
			if(bNeighbours.contains(s))
				upper_part++;
		}
		
		for(int k=0;k<X;k++) {
			if(aNeighbours.contains(k) || bNeighbours.contains(k))
				lower_part++;
		}
		
		if(i == j) {
			similarityMatrix[i][j] = 1;
			aNeighbours.clear();
			bNeighbours.clear();
		}else {
			aNeighbours.clear();
			bNeighbours.clear();
			similarityMatrix[i][j] = upper_part / lower_part;
		}	
	}
	
	private static void getNeighbours(int i, int j) {
		
		List <Integer> temp_list = new ArrayList<Integer>();
		
		for(int k=0;k<X;k++) {
			if(matrix[i][k] != 0) {
				aNeighbours.add(k);
			}
		}
		
//		for(int k=0;k<Y;k++) {
//			if(matrix[k][j] != 0) {
//				temp_list.add(k);
//			}
//		}
		
			for(int m=0;m<X;m++) {
				if(matrix[j][m] != 0 && !bNeighbours.contains(m)) {
					bNeighbours.add(m);
				}
			}			
	}
	
	private static void predictRating(int targetUser, int article) {
		
		double lowerPart = 0;
		double upperPart = 0;
		
		for(int i=0;i<Y;i++) {
			for(int j=0;j<Y;j++) {
					calcuateSimilarity(i, j);
			}
		}
		
		for(int i=0;i<X;i++) {
			if(targetUser != i && similarityMatrix[targetUser][i] != 0) {
				upperPart += matrix[i][article] * similarityMatrix[targetUser][i];
			}
		}
			
		for(int j=0;j<Y;j++) {
			if(similarityMatrix[targetUser][j] != 0 && targetUser != j) {
				lowerPart += similarityMatrix[targetUser][j];
			}
		}
		if(!Double.isNaN(upperPart / lowerPart))
			System.out.println("Note = " + (upperPart / lowerPart));
		else
			System.out.println("Note = 0.0");
	}

	public static void main(String[] args) {
		for (int i = 0; i < X; i++) {
         
           for (int j = 0; j < Y; j++) {
              
            if(matrix[i][j]==0){
                
           // matrix[i][j]=
           predictRating(i,j);
            //System.out.println("The Rating Expected For User"+i+" Item'"+j+"'is '"+matrix[i][j]+"'");
              
           }   
           }System.out.println("-----------------------------");
      }
	}
}