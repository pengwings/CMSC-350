/**
 * Project4.java
 * Brian Yu
 * 5/10/2020
 * This class constructs a GUI that allows users to enter in a file with class dependencies to create a directed graph.
 * Users can then enter in a class name to retrieve the dependencies in topological order.
 */
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Project4 {
    //places GraphPanel in frame
    public static void main (String[] args) {
        JFrame project4 = new JFrame("Class Dependency Graph");
        project4.setSize(500,250);
        project4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project4.add(new GraphPanel());
        project4.setVisible(true);
    }
    //inner class that puts together all the labels, fields, and buttons on a panel
    private static class GraphPanel extends JPanel {
        private JLabel inputLabel = new JLabel("Input file name:");
        private JLabel recompileLabel = new JLabel("Class to recompile:");
        private JTextField inputField = new JTextField("", 15);
        private JTextField recompileField = new JTextField("", 15);
        private DirectedGraph<String> graph;
        //button that instantiates DirectedGraph object, reads in file, and constructs directed graph
        private JButton buildButton = new JButton(new AbstractAction("Build Directed Graph") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    graph = new DirectedGraph<>();
                    BufferedReader reader = new BufferedReader(new FileReader(inputField.getText()));
                    String newLine;
                    while((newLine = reader.readLine())!= null) {
                        List<String> newLineArray = Arrays.asList(newLine.split("\\s+"));
                        graph.createGraph(newLineArray);
                    }
                    JOptionPane.showMessageDialog(null, "Graph built successfully.");
                } catch(IOException ioe) {
                    JOptionPane.showMessageDialog(null, "File did not open.");
                }
            }
        });
        //button that prints class dependencies of inputted class on text area
        private JButton orderButton = new JButton(new AbstractAction("Topological Order") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = recompileField.getText();
                ArrayList<Integer> visited = new ArrayList<>();
                ArrayList<Integer> finished = new ArrayList<>();
                Stack<Integer> resultStack = new Stack<>();
                try {
                    int input = graph.toKey(inputText);
                    graph.generateTopologicalOrder(input, visited, finished, resultStack);
                } catch(InvalidClassNameException icnex) {
                    JOptionPane.showMessageDialog(null, "Invalid class name.");
                } catch(DirectedGraphCycleException dgcex) {
                    JOptionPane.showMessageDialog(null, "Cycle detected.");
                }
                orderArea.setText(graph.toString(resultStack));
            }
        });

        private JTextArea orderArea = new JTextArea(10,30);
        //constructor for panel
        public GraphPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel filePanel = new JPanel();
            filePanel.setLayout(new GridLayout(2,3,10,10));
            filePanel.setBorder(new TitledBorder(""));
            inputLabel.setHorizontalAlignment(JLabel.CENTER);
            recompileLabel.setHorizontalAlignment(JLabel.CENTER);
            filePanel.add(inputLabel);  filePanel.add(inputField); filePanel.add(buildButton);
            filePanel.add(recompileLabel);  filePanel.add(recompileField);  filePanel.add(orderButton);
            orderArea.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Recompilation Order"), orderArea.getBorder()));
            orderArea.setLineWrap(true);

            this.add(Box.createRigidArea(new Dimension(500,5)));
            this.add(filePanel);
            this.add(Box.createRigidArea(new Dimension(500,5)));
            this.add(orderArea);
            this.add(Box.createRigidArea(new Dimension(500,5)));
        }
    }

}
