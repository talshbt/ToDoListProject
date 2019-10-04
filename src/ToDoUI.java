/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author talshbt
 */

 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;





class Constants {
    
        public static void getList(String[] list){
            LIST_DATA = list;
        }

	public static final String NIMBUS_LF = "Nimbus";
      
	public static String[] LIST_DATA = null;

	public static final int NEW_ELEMENT_IDX = 0;

}


@SuppressWarnings("serial")
class SwingJList<T> extends JList<T> {

	public SwingJList(List<T> listData) {

		super(new DefaultListModel<T>());
		for(T element: listData) {
			addElement(element);
		}


		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public void addElement(T element) {
		((DefaultListModel<T>) getModel()).add(Constants.NEW_ELEMENT_IDX,
				element);
	}

	public void removeElement(Object element) {
		((DefaultListModel<T>) getModel()).removeElement(element);
	}

}
 
public class ToDoUI extends JFrame {
    ToDoUIController hce = new ToDoUIController();
    JFrame mainFrame;
    JPanel headerPanel,headerPanel2, footerPanel, middlePanel;
    JPanel p1,p2,p3;
    GridLayout gridLayout;
    JButton addButton, deleteButton, editButton; 
    SwingJList<String> swingJList;
    JScrollPane buttomPanel;
    ToDoUIController uiController;
    
    public void createTopPanel(JPanel topPannel){
        final JTextField nameField = new JTextField(14);

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        
        addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                                
                       
                        if (name != null && !"".equals(name)) {
                            String note1 = name;
                            uiController.addNewNote(note1);
                            refreshList();
                        } else {
                                JOptionPane.showMessageDialog(null,
                                                "Employee name is empty", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                }
        });
        
//        deleteButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				DefaultListModel model = (DefaultListModel) swingJList.getModel();
//				int selectedIndex = swingJList.getModel().getSize() - swingJList.getSelectedIndex() -1;
//				if (selectedIndex != -1) {
//                    uiController.deleteItem(selectedIndex);
//                    refreshList();
//
//				}
				
				
				
				
//				    int[] selectedIx = swingJList.getSelectedIndices();
//				    Integer firstSelIx = swingJList.getModel().getSize()- swingJList.getSelectedIndex() -1 ;
//				    System.out.println("index id = " + firstSelIx);
//                    uiController.deleteItem(firstSelIx);
//                    createGui();

					
				
//			}
//		});
//        
        deleteButton.addActionListener(new ActionListener() {  
            
            public void actionPerformed(ActionEvent e) {   
                String data = "";  
                if (swingJList.getSelectedIndex() != -1) {                       
                    data = "OS Type Selected: " + swingJList.getSelectedValue();
                    Integer ind = swingJList.getSelectedIndex();
                    nameField.setText(ind.toString());  
                }  
//                if(swingJList.getSelectedIndex() != -1){  
//                    data += ", Oprating System Selected: ";  
//                    for(Object f :swingJList.getSelectedValues()){  
//                       data += f + " ";  
//                    }  
//                }  
//                label.setText(data);  
            }
            
        });     
    

        topPannel.add(nameField);
        topPannel.add(addButton);
        topPannel.add(editButton);
        topPannel.add(deleteButton);
                
    }
    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
    
    
    
    void createHeaderPanel(){
        headerPanel = addPanel("North", Color.blue);
        headerPanel.setBackground( new Color(0, 100, 255, 90) );
        createTopPanel(headerPanel);

    }
    
    void createToDoListPanel(){
        p1= addPanel(middlePanel, Color.GRAY, "");
        addTitleToPanel(p1, "To Do");
        p1.setBackground(new Color(0, 100, 255, 15));
        swingJList = new SwingJList<String>(Arrays.asList(Constants.LIST_DATA));
        swingJList.setBackground(Color.WHITE);
        swingJList.setCellRenderer(getRenderer());
        buttomPanel = new JScrollPane(swingJList);
        buttomPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        buttomPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        buttomPanel.setPreferredSize(new Dimension(400, 300));

        p1.add(buttomPanel);
 
    }
    
    void createCompleteListPanel(){
        p2= addPanel(middlePanel, Color.GRAY, "");
       
        addTitleToPanel(p2, "Complete");

        p2.setBackground(new Color(0, 100, 255, 15));
    }
    
    
    
    void createGui(){
        Constants.getList(uiController.getMsgList());
        gridLayout = new GridLayout(0,1);
       
        footerPanel = addPanel("South", Color.BLUE, "footer");
        middlePanel = addPanel("Center", gridLayout);
        
        createHeaderPanel();
        createToDoListPanel();
        createCompleteListPanel();

 
         
    }

    
   ToDoUI(){
       mainFrame = new JFrame();
       uiController = new ToDoUIController();
       createGui();
       initFrame();

   }
   
   void refreshList(){
       
       createGui();

   }
   
     private JPanel getPanel(Color c, String txt)
    {
        JLabel label = new JLabel(txt);
        
        JPanel result = new JPanel();
        result.add(label);
        result.setBorder(BorderFactory.createLineBorder(c));
        return result;
    }
    
    private JPanel getPanel(Color c)
    {
        
        JPanel result = new JPanel();
        result.setBorder(BorderFactory.createLineBorder(c));
        return result;
    }
    

    
     public JPanel addPanel(String direction, Color color, String txt){
        JPanel panel = getPanel(color, txt);
        mainFrame.add(panel, direction);
        
        return panel;

    }
     
     public JPanel addPanel(String direction, Color color){
        JPanel panel = getPanel(color);
        mainFrame.add(panel, direction);
        
        return panel;

    }
     
     public JPanel addPanel(String direction, GridLayout gl){
          
        JPanel panel = new JPanel(gridLayout);
        mainFrame.add(panel, direction);

        return panel;

    }
     
     public JPanel addPanel(JPanel mainPanel, Color color, String txt){
        JPanel panel = getPanel(color, txt);
        mainPanel.add(panel);
        
        return panel;

    }
    
     public java.util.List<JPanel> addPanelsToPanel(JPanel mainPanel,  Color color, String txt, int numberOfComponent){
        java.util.List<JPanel> list = new ArrayList<JPanel>();
        for(Integer i = 0; i < numberOfComponent; ++i){
          
          JPanel panel = getPanel(color, txt + i.toString());
          list.add(panel);
          mainPanel.add(panel);
        }
       
        return list;

    }
     
     public java.util.List<JButton> addButtonsToPanel(JPanel mainPanel, String txt, int numberOfComponent){
        java.util.List<JButton> list = new ArrayList<JButton>();
        for(Integer i = 0; i < numberOfComponent; ++i){
          
          JButton bt = new JButton(txt + i.toString()); 
          list.add(bt);
          mainPanel.add(bt);
        }
       
        return list;

    }
     
     
    void addTitleToPanel(JPanel panel, String title){
        TitledBorder border = new TitledBorder(title);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(border);
        
    }
     
   
     
    void initFrame(){
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(600,600);

        mainFrame.setVisible(true);
    }

    
 
   

   public static void main(String args[]) {
       
      
      SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					setLookAndFeel(Constants.NIMBUS_LF);
					ToDoUI  todo = new ToDoUI(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
   }
     
        
    public static void setLookAndFeel(String lf) throws Exception {
		// Set Nimbus as L&F
//		try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				if (lf.equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (Exception e) {
//			// If Nimbus is not available, you can set the GUI the system
//			// default L&F.
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		}
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