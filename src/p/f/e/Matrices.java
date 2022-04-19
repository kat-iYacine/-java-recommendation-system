/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.f.e;

/**
 *
 * @author YACIN
 */
public class Matrices {
    public static String file="/media/roott/A03EC6103EC5E000/Users/YACIN/Downloads/ml-100k/u1.base";
    	public static double [][] matrix10 = {
                                                { 5, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	                                        { 4, 4, 0, 0, 0, 0, 0, 0, 0, 0},
	                                    	{ 0, 0, 0, 0, 1, 0, 5, 0, 0, 0},
						{ 0, 0, 0, 0, 3, 0, 0, 0, 3, 2},
					        { 0, 0, 1, 0, 0, 5, 4, 0, 1, 0},
                                                { 0, 0, 5, 0, 0, 1, 0, 0, 0, 0},
                                                { 0, 0, 3, 4, 0, 0, 0, 0, 5, 3},
                                                { 0, 0, 2, 0, 0, 0, 0, 2, 0, 1},
                                                { 0, 0, 2, 0, 2, 0, 0, 3, 0, 2},
                                                { 0, 0, 1, 0, 0, 0, 0, 0, 1, 3},
                                                
                                                                                   };
	
	public static double [][] matrix5 = {   { 5, 2, 0, 0, 0},
	                                        { 4, 4, 5, 0, 0},
	                                    	{ 0, 0, 4, 4, 1},
						{ 0, 0, 0, 0, 3},
					        { 0, 0, 1, 0, 0}, 
                                                                 };
        
        
        public static double [][] matrixLens = dataset.readCSV(file);
}
