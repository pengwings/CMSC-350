/**
 * Project2.java
 * Brian Yu
 * 4/12/2020
 * This class constructs a GUI that allows users to convert postfix expression to infix expression and generate three address instruction file.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Project2 {
    //places GeneratorPanel in frame
    public static void main(String[] args) {
        JFrame project2 = new JFrame("Three Address Generator");
        project2.setSize(400,150);
        project2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project2.add(new GeneratorPanel());
        project2.setVisible(true);
    }
    //inner class that puts together all the labels, fields, and buttons on a panel
    private static class GeneratorPanel extends JPanel {
        private JLabel postfixLabel = new JLabel("Enter Postfix Expression");
        private JLabel infixLabel = new JLabel("Infix Expression");
        private JTextField postfixField = new JTextField("",20);
        private JTextField infixField = new JTextField("",20);
        //button that instantiates AddressGenerator object with stacks, displays result, and creates output file in C:/temp
        private JButton constructButton = new JButton(new AbstractAction("Construct Tree") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputExpression = postfixField.getText();
                AddressGenerator generator = new AddressGenerator();
                File output = new File("C:/temp/output.txt");
                try {
                    String infixExpression = generator.generateExpression(inputExpression, output);
                    OperandNode.resetRegisterCounter();
                    infixField.setText(infixExpression);
                } catch(RuntimeException r) { //prints out message if invalid token detected
                    JOptionPane.showMessageDialog(null, "Invalid token entered.");
                }
            }
        });
        //constructor for panel
        public GeneratorPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel postfixPanel = new JPanel();
            postfixPanel.setLayout(new FlowLayout());
            postfixPanel.add(postfixLabel); postfixPanel.add(postfixField);
            constructButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel infixPanel = new JPanel();
            infixPanel.setLayout(new FlowLayout());
            infixField.setEditable(false);
            infixPanel.add(infixLabel); infixPanel.add(infixField);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(postfixPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(constructButton);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(infixPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
        }
    }
}

