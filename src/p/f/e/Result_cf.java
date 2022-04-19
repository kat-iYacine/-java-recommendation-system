/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.f.e;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roott
 */
public class Result_cf extends javax.swing.JFrame {

    
    double [][]matrix;
    double [][]matrix_test;
    double [][]matrix_total;
    int X,Y;
    String s="";
     Object columnNames;
     DefaultTableModel dtm = new DefaultTableModel();
    
    public Result_cf(double [][] m,double[][] mt,int x,int y,String s1) {
         X=x;
         Y=y;
        
        matrix=new double[x][y];
        matrix_test=new double[x][y];
        matrix_total=new double[x][y];
        
        matrix=m;
        matrix_test=mt;
        
       s=s1;
        
        initComponents();
         total();
         set_tab();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        mae = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tab);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 318));

        mae.setText("MAE GRAPH");
        mae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maeActionPerformed(evt);
            }
        });
        getContentPane().add(mae, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 147, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/a.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maeActionPerformed
        // TODO add your handling code here:
        if(s.equals("c")){ cos c=new cos();
        c.MAE(matrix,matrix_test);}
         if(s.equals("p")){ Pearson p=new Pearson();
        p.MAE(matrix,matrix_test);
         }
       
    }//GEN-LAST:event_maeActionPerformed

    private void set_tab(){
        
         dtm.addColumn("user");
         dtm.addColumn("item");
         dtm.addColumn("raiting");
          
           
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                
                
               Object[] rowData = new String[]{i+"",j+"",matrix_total[i][j]+""};
               dtm.addRow(rowData);
               
                
            }
        }
          tab.setModel(dtm);
    }
    private void total(){
    
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if(matrix[i][j]!=0){
                matrix_total[i][j]=matrix[i][j];
                
                }
                else{matrix_total[i][j]=matrix_test[i][j];}
                
            }
        }
    
    
    
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mae;
    private javax.swing.JTable tab;
    // End of variables declaration//GEN-END:variables
}
