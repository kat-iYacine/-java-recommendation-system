package p.f.e;

import java.util.ArrayList;
import java.util.List;

public class BipartiGraphAdamic {
	
	private static int X = 0;
	private static int Y = 0;
        public static double [][] matrix = new double[X][Y];
	
	private static List <Integer> aNeighbours = new ArrayList<Integer>();
	private static List <Integer> bNeighbours = new ArrayList<Integer>();
	private static List <Integer> intersection = new ArrayList<Integer>();
	private static double [][] similarityMatrix = new double [Y][Y];
        
	private static void getNeighbours(int i, int j) {
		
		List <Integer> temp_list = new ArrayList<Integer>();
		
		for(int k=0;k<X;k++) {
			if(matrix[i][k] != 0) {
				aNeighbours.add(k);
			}
		}
		
		for(int k=0;k<Y;k++) {
			if(matrix[k][j] != 0) {
				temp_list.add(k);
			}
		}
		
		for(int s : temp_list) {
			for(int k=0;k<X;k++) {
				if(matrix[s][k] != 0 && !bNeighbours.contains(k)) {
					bNeighbours.add(k);
				}
			}
		}		
		
		for(int s : aNeighbours) {
			if(bNeighbours.contains(s)) {
				intersection.add(s);
			}		
		}
	}
	
	private static void calculSim(int i,int j) {
		
		double numberOfNeighbours = 0;
		double sum = 0;
		
		getNeighbours(i, j);
		
		for(int s : intersection) {
			for(int k=0;k<Y;k++) {
				if(matrix[k][s] != 0) {
					numberOfNeighbours ++;
				}
			}
			
			sum += (1 / Math.log(numberOfNeighbours));
		}
	}


}
