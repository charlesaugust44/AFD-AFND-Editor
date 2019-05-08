package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Machine;
import model.State;
import sun.misc.VM;

public class Main extends javax.swing.JFrame {

    public Color transitionColor;
    private Machine machine;
    private Drawer drawer;
    private JFileChooser chooser;

    public Main() {

        transitionColor = Color.black;

        try {
            machine = new Machine();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        drawer = new Drawer(machine, this);

        container.add(drawer);
        drawer.setBounds(0, 0, container.getWidth(), container.getHeight());

        drawer.init();
        drawer.start();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outsideMenu = new javax.swing.JPopupMenu();
        itemMenuAddState = new javax.swing.JMenuItem();
        stateMenu = new javax.swing.JPopupMenu();
        menuItemCreateTransition = new javax.swing.JMenuItem();
        itemMenuIsInitial = new javax.swing.JCheckBoxMenuItem();
        itemMenuIsFinal = new javax.swing.JCheckBoxMenuItem();
        itemMunuChangeName = new javax.swing.JMenuItem();
        itemMenuRemoveState = new javax.swing.JMenuItem();
        transitionMenu = new javax.swing.JPopupMenu();
        menuItemChangeSymbol = new javax.swing.JMenuItem();
        menuItemRemoveTransition = new javax.swing.JMenuItem();
        container = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemNew = new javax.swing.JMenuItem();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        menuSimulation = new javax.swing.JMenu();
        menuItemFastRun = new javax.swing.JMenuItem();

        itemMenuAddState.setText("Add State...");
        itemMenuAddState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuAddStateActionPerformed(evt);
            }
        });
        outsideMenu.add(itemMenuAddState);

        menuItemCreateTransition.setText("Add transition...");
        menuItemCreateTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCreateTransitionActionPerformed(evt);
            }
        });
        stateMenu.add(menuItemCreateTransition);

        itemMenuIsInitial.setText("Is Initial?");
        itemMenuIsInitial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuIsInitialActionPerformed(evt);
            }
        });
        stateMenu.add(itemMenuIsInitial);

        itemMenuIsFinal.setText("Is Final?");
        itemMenuIsFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuIsFinalActionPerformed(evt);
            }
        });
        stateMenu.add(itemMenuIsFinal);

        itemMunuChangeName.setText("Change name...");
        itemMunuChangeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMunuChangeNameActionPerformed(evt);
            }
        });
        stateMenu.add(itemMunuChangeName);

        itemMenuRemoveState.setText("Remove state...");
        itemMenuRemoveState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuRemoveStateActionPerformed(evt);
            }
        });
        stateMenu.add(itemMenuRemoveState);

        menuItemChangeSymbol.setText("Change symbol...");
        menuItemChangeSymbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemChangeSymbolActionPerformed(evt);
            }
        });
        transitionMenu.add(menuItemChangeSymbol);

        menuItemRemoveTransition.setText("Remove transition...");
        menuItemRemoveTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRemoveTransitionActionPerformed(evt);
            }
        });
        transitionMenu.add(menuItemRemoveTransition);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AFND & AFD Simulator");
        setResizable(false);

        container.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                containerComponentResized(evt);
            }
        });

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        menuItemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuItemNew.setText("New");
        menuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNewActionPerformed(evt);
            }
        });
        menuFile.add(menuItemNew);

        menuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpen.setText("Open...");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });
        menuFile.add(menuItemOpen);

        menuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSave.setText("Save");
        menuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuItemSave);

        jMenuBar1.add(menuFile);

        menuSimulation.setText("Simulation");

        menuItemFastRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuItemFastRun.setText("Fast Run");
        menuItemFastRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFastRunActionPerformed(evt);
            }
        });
        menuSimulation.add(menuItemFastRun);

        jMenuBar1.add(menuSimulation);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JFlap Files", "jff");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showDialog(this, "Open") == JFileChooser.APPROVE_OPTION)

            try {
                machine = new Machine(chooser.getSelectedFile());
                drawer.setMachine(machine);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error Loading the file", "File Loading error!", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }//GEN-LAST:event_menuItemOpenActionPerformed

    private void menuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveActionPerformed
        if (machine.file == null) {
            boolean wantToReplace = false;
            do {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JFlap Files", "jff");
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Select a place to save");
                chooser.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop/newMachine.jff"));
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();

                    if (file.exists()) {
                        int r = JOptionPane.showConfirmDialog(this, "Replace the file `" + file.getName() + "`?");
                        if (r == JOptionPane.OK_OPTION) {
                            machine.file = file;
                            machine.save();
                            wantToReplace = true;
                        }
                    } else {
                        machine.file = file;
                        machine.save();
                        wantToReplace = true;
                    }
                } else
                    wantToReplace = true;
            } while (!wantToReplace);
        } else
            machine.save();
    }//GEN-LAST:event_menuItemSaveActionPerformed

    private void menuItemFastRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemFastRunActionPerformed
        String word = JOptionPane.showInputDialog(this, "Enter the word to simulate:");

        boolean passed = machine.simulateFiniteAutomaton(word);

        if (passed)
            JOptionPane.showMessageDialog(this, "Word `" + word + "` passed!", "Result", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Word `" + word + "` failed!", "Result", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_menuItemFastRunActionPerformed

    private void containerComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_containerComponentResized
        drawer.setBounds(0, 40, container.getWidth(), container.getHeight());

    }//GEN-LAST:event_containerComponentResized

    private void itemMenuIsInitialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuIsInitialActionPerformed
        JCheckBoxMenuItem item = (JCheckBoxMenuItem) evt.getSource();
        State oldInitial = machine.getInitialState();

        if (oldInitial != null)
            oldInitial.isInitial = false;
        drawer.selected.isInitial = item.isSelected();
        drawer.repaint();
    }//GEN-LAST:event_itemMenuIsInitialActionPerformed

    private void itemMenuIsFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuIsFinalActionPerformed
        JCheckBoxMenuItem item = (JCheckBoxMenuItem) evt.getSource();
        drawer.selected.isFinal = item.isSelected();
        drawer.repaint();
    }//GEN-LAST:event_itemMenuIsFinalActionPerformed

    private void itemMunuChangeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMunuChangeNameActionPerformed
        String newName = JOptionPane.showInputDialog(this, "Change name:", drawer.selected.name);
        if (!newName.isEmpty()) {
            drawer.selected.name = newName;
            drawer.repaint();
        }
    }//GEN-LAST:event_itemMunuChangeNameActionPerformed

    private void itemMenuRemoveStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuRemoveStateActionPerformed
        int r = JOptionPane.showConfirmDialog(this, "Do you want to delete the state `" + drawer.selected.name + "`?");
        if (r == JOptionPane.OK_OPTION) {
            machine.removeState(drawer.selected);
            drawer.repaint();
        }
    }//GEN-LAST:event_itemMenuRemoveStateActionPerformed

    private void itemMenuAddStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuAddStateActionPerformed
        String name = JOptionPane.showInputDialog(this, "Type the state name:", "q" + machine.currentId);
        drawer.addState(name);
    }//GEN-LAST:event_itemMenuAddStateActionPerformed

    private void menuItemCreateTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCreateTransitionActionPerformed
        drawer.startTransitionAdd();
    }//GEN-LAST:event_menuItemCreateTransitionActionPerformed

    private void menuItemChangeSymbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemChangeSymbolActionPerformed
        String newSymbol = JOptionPane.showInputDialog(this, "Change symbol:", drawer.selectedTransition.read);
        char r;
        if (newSymbol.isEmpty())
            r = 0;
        else
            r = newSymbol.charAt(0);
        drawer.selectedTransition.read = r;
        drawer.repaint();

    }//GEN-LAST:event_menuItemChangeSymbolActionPerformed

    private void menuItemRemoveTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRemoveTransitionActionPerformed
        char read = drawer.selectedTransition.read;
        String t = drawer.selectedTransition.from.name + "→" + ((read == 0) ? "λ" : read) + "→" + machine.getStateById(drawer.selectedTransition.to).name;
        int r = JOptionPane.showConfirmDialog(this, "Do you want to delete the transition `" + t + "`?");
        if (r == JOptionPane.OK_OPTION) {
            drawer.selectedTransition.remove();
            drawer.repaint();
        }
    }//GEN-LAST:event_menuItemRemoveTransitionActionPerformed

    private void menuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNewActionPerformed
        machine = new Machine();
        drawer.setMachine(machine);
    }//GEN-LAST:event_menuItemNewActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                if ("Metal".equals(info.getName()) || "Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JMenuItem itemMenuAddState;
    private javax.swing.JCheckBoxMenuItem itemMenuIsFinal;
    private javax.swing.JCheckBoxMenuItem itemMenuIsInitial;
    private javax.swing.JMenuItem itemMenuRemoveState;
    private javax.swing.JMenuItem itemMunuChangeName;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuItemChangeSymbol;
    private javax.swing.JMenuItem menuItemCreateTransition;
    private javax.swing.JMenuItem menuItemFastRun;
    private javax.swing.JMenuItem menuItemNew;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JMenuItem menuItemRemoveTransition;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenu menuSimulation;
    private javax.swing.JPopupMenu outsideMenu;
    private javax.swing.JPopupMenu stateMenu;
    private javax.swing.JPopupMenu transitionMenu;
    // End of variables declaration//GEN-END:variables

    public JPopupMenu getOutsideMenu() {
        return outsideMenu;
    }

    public JPopupMenu getStateMenu() {
        return stateMenu;
    }

    public JPopupMenu getTransitionMenu() {
        return transitionMenu;
    }

    public JCheckBoxMenuItem getItemMenuIsFinal() {
        return itemMenuIsFinal;
    }

    public JCheckBoxMenuItem getItemMenuIsInitial() {
        return itemMenuIsInitial;
    }

}
