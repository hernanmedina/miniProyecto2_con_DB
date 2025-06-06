/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

/**
 *
 * @author Hernan Medina
 */
public class RegistroReservaGUI extends javax.swing.JFrame {

    /**
     * Creates new form ListarReservasGUI
     */
    public RegistroReservaGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo_listarReserva = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComb_profesor = new javax.swing.JComboBox();
        jComb_equipo = new javax.swing.JComboBox();
        jTf_Reserva_codigo = new javax.swing.JTextField();
        jbtn_Registra_reserva = new javax.swing.JButton();
        jbtn_Listar_Reserva = new javax.swing.JButton();
        jbtn_eliminar_reserva = new javax.swing.JButton();
        jbtn_modificar_reserva = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_reservas = new javax.swing.JTable();
        jbtn_buscar_reserva = new javax.swing.JButton();
        jtf_Fecha_reserva = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtn_recoger_EQ = new javax.swing.JButton();
        jbtn_entregar_EQ = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listar Reservas");

        titulo_listarReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_listarReserva.setText("Lista de Equipos Reservados");

        jLabel1.setText("Codigo Reserva:");

        jComb_equipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComb_equipoActionPerformed(evt);
            }
        });

        jTf_Reserva_codigo.setActionCommand("null");
        jTf_Reserva_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTf_Reserva_codigoActionPerformed(evt);
            }
        });

        jbtn_Registra_reserva.setText("Registrar");

        jbtn_Listar_Reserva.setText("Listar");
        jbtn_Listar_Reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Listar_ReservaActionPerformed(evt);
            }
        });

        jbtn_eliminar_reserva.setText("Eliminar");

        jbtn_modificar_reserva.setText("Modificar");
        jbtn_modificar_reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_modificar_reservaActionPerformed(evt);
            }
        });

        jLabel2.setText("Profesor:");

        jLabel3.setText("Equipo:");

        jTable_reservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable_reservas);

        jbtn_buscar_reserva.setText("Buscar");

        jLabel4.setText("Fecha Reserva:");

        jbtn_recoger_EQ.setText("Recoger EQ");

        jbtn_entregar_EQ.setText("Entregar EQ");

        jLabel5.setText("Formato: 2025-05-28 15:00:00 ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(titulo_listarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTf_Reserva_codigo)
                                    .addComponent(jComb_profesor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComb_equipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtf_Fecha_reserva)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtn_Registra_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jbtn_modificar_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_buscar_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Listar_Reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_eliminar_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_recoger_EQ, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jbtn_entregar_EQ, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(titulo_listarReserva)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTf_Reserva_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComb_profesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComb_equipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_Fecha_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbtn_recoger_EQ)
                                .addComponent(jbtn_entregar_EQ))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbtn_Registra_reserva)
                                .addComponent(jbtn_eliminar_reserva)
                                .addComponent(jbtn_modificar_reserva)
                                .addComponent(jbtn_Listar_Reserva)
                                .addComponent(jbtn_buscar_reserva))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTf_Reserva_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTf_Reserva_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTf_Reserva_codigoActionPerformed

    private void jbtn_Listar_ReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Listar_ReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_Listar_ReservaActionPerformed

    private void jbtn_modificar_reservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_modificar_reservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_modificar_reservaActionPerformed

    private void jComb_equipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComb_equipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComb_equipoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroReservaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox jComb_equipo;
    public javax.swing.JComboBox jComb_profesor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable_reservas;
    public javax.swing.JTextField jTf_Reserva_codigo;
    public javax.swing.JButton jbtn_Listar_Reserva;
    public javax.swing.JButton jbtn_Registra_reserva;
    public javax.swing.JButton jbtn_buscar_reserva;
    public javax.swing.JButton jbtn_eliminar_reserva;
    public javax.swing.JButton jbtn_entregar_EQ;
    public javax.swing.JButton jbtn_modificar_reserva;
    public javax.swing.JButton jbtn_recoger_EQ;
    public javax.swing.JTextField jtf_Fecha_reserva;
    private javax.swing.JLabel titulo_listarReserva;
    // End of variables declaration//GEN-END:variables
}
