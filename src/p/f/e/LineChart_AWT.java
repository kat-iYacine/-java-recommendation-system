/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.f.e;

import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame {
 ArrayList<Double> h =new ArrayList<>();
 int s=0;
   public LineChart_AWT( String applicationTitle , String chartTitle,   ArrayList<Double>  g ) {
      super(applicationTitle);
      h=g;
      s=h.size()-1;
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Number of Schools",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset() {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      
       for (int i = h.size()-1; i >=0 ; i--) {
           String c=(s-i)+"";
            dataset.addValue(h.get(i) , "c" , c );
       }
     
      return dataset;
   }
   
   
}
