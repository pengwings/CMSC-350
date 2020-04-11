/**
 * Project1.java
 * Brian Yu
 * 3/29/2020
 * This class constructs a GUI that allows users to calculate in-fix expressions
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project1 {
    //places evaluatorpanel in frame
    public static void main(String[] args) {
        JFrame project1 = new JFrame("Infix Expression Evaluator");
        project1.setSize(400,150);
        project1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project1.add(new EvaluatorPanel());
        project1.setVisible(true);
    }
    //inner class that puts together all the labels, fields, and buttons on a panel
    private static class EvaluatorPanel extends JPanel {
        private JLabel expressionLabel = new JLabel("Enter Infix Expression");
        private JLabel resultLabel = new JLabel("Result");
        private JTextField expressionField = new JTextField("",20);
        private JTextField resultField = new JTextField("",20);
        //button that instantiates InfixExpressionEvaluator object with stacks and displays result
        private JButton evaluateButton = new JButton(new AbstractAction("Evaluate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputExpression = expressionField.getText();
                InfixExpressionEvaluator evaluator = new InfixExpressionEvaluator();
                int result = evaluator.calculateExpression(inputExpression);
                resultField.setText(Integer.toString(result));
            }
        });
        //constructor for panel
        public EvaluatorPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel expressionPanel = new JPanel();
            expressionPanel.setLayout(new FlowLayout());
            expressionPanel.add(expressionLabel); expressionPanel.add(expressionField);
            evaluateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new FlowLayout());
            resultField.setEditable(false);
            resultPanel.add(resultLabel); resultPanel.add(resultField);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(expressionPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(evaluateButton);
            this.add(Box.createRigidArea(new Dimension(400,5)));
            this.add(resultPanel);
            this.add(Box.createRigidArea(new Dimension(400,5)));
        }
    }
}
