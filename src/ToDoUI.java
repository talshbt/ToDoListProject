/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author talshbt
 */

 
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



 
public class ToDoUI extends JFrame {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   
   
   ToDoUI(){
       prepareGUI();
   }
   
   
    private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      
      
      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  


    }
    
    
       private void showButtonDemo(){
            headerLabel.setText("Control in action: Button"); 


           JButton okButton = new JButton("OK");        
           JButton javaButton = new JButton("Submit");
           JButton cancelButton = new JButton("Cancel");
           cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);   

           controlPanel.add(okButton);
           controlPanel.add(javaButton);
           controlPanel.add(cancelButton);       

           mainFrame.setVisible(true); 

       }

   

   public static void main(String args[]) {
      ToDoUI  swingControlDemo = new ToDoUI();      
      swingControlDemo.showButtonDemo();
   }
     
        
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


//
//   private JFrame mainFrame;
//   private JLabel headerLabel;
//   private JLabel statusLabel;
//   private JPanel controlPanel;
//
//        
//        
//    public ToDoUI() {
//         prepareGUI();
//    }
//
//   
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
//    private void initComponents() {
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 400, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 300, Short.MAX_VALUE)
//        );
//
//        pack();
//    }// </editor-fold>                        
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//
//        ToDoUI  swingControlDemo = new ToDoUI();      
//      swingControlDemo.showButtonDemo();
//
//    }
//    
//     private void prepareGUI(){
//      mainFrame = new JFrame("Java Swing Examples");
//      mainFrame.setSize(400,400);
//      mainFrame.setLayout(new GridLayout(3, 1));
//      
//      mainFrame.addWindowListener(new WindowAdapter() {
//         public void windowClosing(WindowEvent windowEvent){
//            System.exit(0);
//         }        
//      });    
//      headerLabel = new JLabel("", JLabel.CENTER);        
//      statusLabel = new JLabel("",JLabel.CENTER);    
//      statusLabel.setSize(350,100);
//
//      controlPanel = new JPanel();
//      controlPanel.setLayout(new FlowLayout());
//
//      mainFrame.add(headerLabel);
//      mainFrame.add(controlPanel);
//      mainFrame.add(statusLabel);
//      mainFrame.setVisible(true);  
//   }
//    
//    
//    private static ImageIcon createImageIcon(String path, String description) {
//      java.net.URL imgURL = ToDoUI.class.getResource(path);
//      if (imgURL != null) {
//         return new ImageIcon(imgURL, description);
//      } else {            
//         System.err.println("Couldn't find file: " + path);
//         return null;
//      }
//   }   
//    
//    
//      private void showButtonDemo(){
//      headerLabel.setText("Control in action: Button"); 
//
//      //resources folder should be inside SWING folder.
//      ImageIcon icon = createImageIcon("/resources/java_icon.png","Java");
//
//      JButton okButton = new JButton("OK");        
//      JButton javaButton = new JButton("Submit", icon);
//      JButton cancelButton = new JButton("Cancel", icon);
//      cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);   
//
//      okButton.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            statusLabel.setText("Ok Button clicked.");
//         }          
//      });
//      javaButton.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            statusLabel.setText("Submit Button clicked.");
//         }
//      });
//      cancelButton.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            statusLabel.setText("Cancel Button clicked.");
//         }
//      });
//      controlPanel.add(okButton);
//      controlPanel.add(javaButton);
//      controlPanel.add(cancelButton);       
//
//      mainFrame.setVisible(true);  
//   }