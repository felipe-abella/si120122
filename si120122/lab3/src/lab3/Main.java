package lab3;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * Implements the ColumnModel for the keyword table.
 */
class KeywordCountColumnModel extends DefaultTableColumnModel {

    /**
     * Creates the model.
     */
    public KeywordCountColumnModel() {
        TableColumn col = new TableColumn(0);
        col.setHeaderValue("Keyword");
        addColumn(col);

        TableColumn col2 = new TableColumn(1);
        col2.setHeaderValue("Quantidade");
        addColumn(col2);
    }
}

/**
 * Main class.
 */
public class Main extends javax.swing.JFrame implements AnalyzerListener {

    private KeywordCounter tableModel;
    private Analyzer analyzer;

    /**
     * Creates new form Main.
     */
    public Main() {
        initComponents();
        setLocationRelativeTo(null);

        tableModel = (KeywordCountTableModel) keywordsTable.getModel();

        analyzer = new Analyzer(tableModel);
        analyzer.addListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dirNameLabel = new javax.swing.JLabel();
        maxThreadLabel = new javax.swing.JLabel();
        dirNameField = new javax.swing.JTextField();
        maxThreadField = new javax.swing.JTextField();
        analyzeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        keywordsTable = new javax.swing.JTable();
        analyzeCountLabel = new javax.swing.JLabel();
        analyzeTimeLabel = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lab3");

        dirNameLabel.setText("Diretório para analisar:");

        maxThreadLabel.setText("Número máximo de Threads:");

        analyzeButton.setText("Analisar");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });

        keywordsTable.setModel(new KeywordCountTableModel());
        jScrollPane1.setViewportView(keywordsTable);
        keywordsTable.setColumnModel(new KeywordCountColumnModel());

        analyzeCountLabel.setText("Nenhum arquivo achado!");

        analyzeTimeLabel.setText("Nenhuma análise foi feita!");

        clearButton.setText("Limpar");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lab 3");
        jLabel1.setAlignmentX(0.5F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maxThreadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dirNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dirNameField)
                            .addComponent(maxThreadField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(analyzeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(analyzeCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(analyzeTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 400, Short.MAX_VALUE)
                        .addComponent(clearButton))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dirNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dirNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxThreadLabel)
                    .addComponent(maxThreadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analyzeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analyzeCountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analyzeTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private synchronized void showError(String errorMsg) {
        JDialog dialog = new JDialog(this, errorMsg, true);
        dialog.getContentPane().add(new JLabel(errorMsg));
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private synchronized void start() {
        String dirName = dirNameField.getText();
        int maxthread;

        if (maxThreadField.getText().isEmpty()) {
            maxthread = 0;
        } else {
            try {
                maxthread = Integer.parseInt(maxThreadField.getText());
            } catch (NumberFormatException ex) {
                maxthread = -1;
            }
        }
        if (maxthread < 0) {
            showError("Numero de threads inválido!");
            return;
        }

        try {
            analyzer.start(dirName, maxthread);
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
            return;
        }

        analyzeButton.setEnabled(false);
        clearButton.setEnabled(false);
    }

    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        start();
    }//GEN-LAST:event_analyzeButtonActionPerformed

    private synchronized void updateUI() {
        if (analyzer.getNfound() == 0) {
            analyzeCountLabel.setText("Nenhum arquivo achado!");
        } else {
            analyzeCountLabel.setText(String.format(
                    "%d arquivo(s) encontrados(s), %d processado(s)!",
                    analyzer.getNfound(), analyzer.getNanalyzed()));
        }

        if (analyzer.isRunning()) {
            analyzeTimeLabel.setText("Análise em andamento!");
        } else if (analyzer.getNdirfound() == 0) {
            analyzeTimeLabel.setText("Nenhuma análise foi feita!");
        } else {
            analyzeTimeLabel.setText(String.format(
                    "Ultima análise levou %d milisegundos!", analyzer.getElapsedTime()));
        }
    }

    private synchronized void clear() {
        tableModel.clear();
        analyzer.clear();
        updateUI();
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    /**
     * Called when the analyzer starts.
     */
    @Override
    public void analyzingStarted() {
    }

    /**
     * Called when the analyzer finishes.
     */
    @Override
    public synchronized void analyzingFinished() {
        updateUI();
        analyzeButton.setEnabled(true);
        clearButton.setEnabled(true);
    }

    /**
     * Called when the analyzer finds (and possibly analyzes) a new file.
     */
    @Override
    public synchronized void newFileFound() {
        updateUI();
    }

    /**
     * Starts the program.
     *
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
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JLabel analyzeCountLabel;
    private javax.swing.JLabel analyzeTimeLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField dirNameField;
    private javax.swing.JLabel dirNameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable keywordsTable;
    private javax.swing.JTextField maxThreadField;
    private javax.swing.JLabel maxThreadLabel;
    // End of variables declaration//GEN-END:variables
}
