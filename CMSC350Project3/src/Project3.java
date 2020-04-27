/**
 * Project3.java
 * Brian Yu
 * 4/26/2020
 * This class constructs a GUI that allows users to enter an unsorted list of integers or fractions and display
 * the sorted list in ascending or descending order based on user selection.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Project3 {
    //places SortingPanel in frame
    public static void main(String[] args) {
        JFrame project3 = new JFrame("Binary Search Tree Sort");
        project3.setSize(400,250);
        project3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project3.add(new SortingPanel());
        project3.setVisible(true);
    }
    //inner class that puts together all the labels, fields, and buttons on a panel
    private static class SortingPanel extends JPanel {
        private JLabel ogListLabel = new JLabel("Original List");
        private JLabel sortListLabel = new JLabel("Sorted List");
        private JTextField ogListField = new JTextField("",25);
        private JTextField sortListField = new JTextField ("", 25);
        //button that creates BinarySearchTree object, inserts inputted text into binary search tree, and prints sorted list
        private JButton sortButton = new JButton(new AbstractAction("Perform Sort") {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortListField.setText("");
                String inputList = ogListField.getText();
                //creates list with elements divided by spaces and removes any empty strings
                List<String> unsortedList = Arrays.asList(inputList.split("\\s+"));
                List<String> filteredList = unsortedList.stream().filter(str -> !str.isBlank()).collect(Collectors.toList());
                BinarySearchTree bst = new BinarySearchTree();
                if(integerButton.isSelected()) {
                    //if integer is selected, checks for non-integer inputs and creates binary search tree with integers
                    try {
                        Integer[] integerList = new Integer[filteredList.size()];
                        for (int i = 0; i < integerList.length; i++) {
                            integerList[i] = Integer.parseInt(filteredList.get(i));
                        }
                        bst.createTree(integerList);
                    } catch(NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Non-numeric input.");
                    }
                } else if(fractionButton.isSelected()) {
                    //if fractions is selected, checks for non-fraction inputs and creates binary search tree with fractions
                    try {
                        Fraction[] fractionList = new Fraction[filteredList.size()];
                        for (int i = 0; i < fractionList.length; i++) {
                            if(!filteredList.get(i).matches("\\d+[\\/]\\d+")) {
                                throw new NumberFormatException();
                            }
                            fractionList[i] = new Fraction(filteredList.get(i));
                        }
                        bst.createTree(fractionList);
                    } catch(NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Incorrect fraction format.");
                    }
                }
                //if ascending is selected, prints out sorted list in ascending order
                if(ascendingButton.isSelected()) {
                    sortListField.setText(bst.ascendingSort(bst.getRoot()));
                } else if(descendingButton.isSelected()) { // if descending is selected, prints out sorted list in descending order
                    sortListField.setText(bst.descendingSort(bst.getRoot()));
                }
            }
        });

        ButtonGroup sortOrder = new ButtonGroup();
        private JRadioButton ascendingButton = new JRadioButton("Ascending");
        private JRadioButton descendingButton = new JRadioButton("Descending");

        ButtonGroup numericType = new ButtonGroup();
        private JRadioButton integerButton = new JRadioButton("Integer");
        private JRadioButton fractionButton = new JRadioButton("Fraction");
        //constructor for panel
        public SortingPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel ogListPanel = new JPanel();
            ogListPanel.setLayout(new FlowLayout());
            ogListPanel.add(ogListLabel);   ogListPanel.add(ogListField);
            sortButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel sortListPanel = new JPanel();
            sortListPanel.setLayout(new FlowLayout());
            sortListField.setEditable(false);
            sortListPanel.add(sortListLabel);   sortListPanel.add(sortListField);
            JPanel sortPanel = new JPanel();
            sortPanel.setLayout(new GridLayout(2,1));
            sortPanel.setBorder(new TitledBorder("Sort Order"));
            sortOrder.add(ascendingButton); sortOrder.add(descendingButton);
            sortPanel.add(ascendingButton,0);   sortPanel.add(descendingButton, 1);
            JPanel numericPanel = new JPanel();
            numericPanel.setLayout(new GridLayout(2,1));
            numericPanel.setBorder(new TitledBorder("Numeric Type"));
            numericType.add(integerButton); numericType.add(fractionButton);
            numericPanel.add(integerButton,0);  numericPanel.add(fractionButton,1);
            JPanel optionsPanel = new JPanel();
            optionsPanel.setLayout(new GridLayout(1,2));
            optionsPanel.add(sortPanel);    optionsPanel.add(numericPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(ogListPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(sortListPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(sortButton);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(optionsPanel);
        }

    }
}
