// ww w. j a v  a  2s .  c o  m

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Main {
  public static void main(String[] args) {
    final DefaultListModel<String> model = new DefaultListModel<>();
    final JList<String> list = new JList<>(model);
    JFrame f = new JFrame();
    
    model.addElement("A");
    model.addElement("B");
    model.addElement("C");
    model.addElement("D");
    model.addElement("E");

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    leftPanel.setLayout(new BorderLayout());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    list.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = list.locationToIndex(e.getPoint());
          Object item = model.getElementAt(index);
          String text = JOptionPane.showInputDialog("Rename item", item);
          String newitem = "";
          if (text != null)
            newitem = text.trim();
          else
            return;

          if (!newitem.isEmpty()) {
            model.remove(index);
            model.add(index, newitem);
            ListSelectionModel selmodel = list.getSelectionModel();
            selmodel.setLeadSelectionIndex(index);
          }
        }
      }
    });
    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    leftPanel.add(new JScrollPane(list));

    JButton removeall = new JButton("Remove All");
    JButton add = new JButton("Add");
    JButton rename = new JButton("Rename");
    JButton delete = new JButton("Delete");

    add.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String text = JOptionPane.showInputDialog("Add a new item");
        String item = null;

        if (text != null)
          item = text.trim();
        else
          return;

        if (!item.isEmpty())
          model.addElement(item);
      }
    });

    delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        if (index >= 0)
          model.remove(index);
      }

    });

    rename.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        if (index == -1)
          return;
        Object item = model.getElementAt(index);
        String text = JOptionPane.showInputDialog("Rename item", item);
        String newitem = null;

        if (text != null) {
          newitem = text.trim();
        } else
          return;

        if (!newitem.isEmpty()) {
          model.remove(index);
          model.add(index, newitem);
        }
      }
    });

    removeall.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.clear();
      }
    });

    rightPanel.add(add);
    rightPanel.add(rename);
    rightPanel.add(delete);
    rightPanel.add(removeall);

    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

    panel.add(leftPanel);
    panel.add(rightPanel);

    f.add(panel);

    f.setSize(350, 250);
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

  }
}