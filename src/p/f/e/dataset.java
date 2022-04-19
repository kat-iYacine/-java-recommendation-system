
package p.f.e;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author roott
 */


public class dataset {

    
    public static double[][] readCSV(String file) {
    double [][] matrix = new double[901][1683];
		
        String csvFile = file;
        char c=9;
        String line = "";
        String csvSplitBy = c+"";
        int i = 0;
  int x=0;
  int y=0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null && x<900) {

                String[] country = line.split(csvSplitBy);

               x=(Integer.parseInt(country[0]))-1;
               y=(Integer.parseInt(country[1]))-1;
                double note=Double.parseDouble(country[2]);
                
                matrix[x][y] = note;
                             
                     // System.out.println( (x+1) + " " + (y+1) +" " + matrix[x][y]);
                      
            }   

        } catch (Exception e) {
            e.printStackTrace();
        }       
        
        return matrix;

               
    }
    
}
