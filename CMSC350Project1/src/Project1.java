import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project1 {

    public static void main(String[] args) {
        JFrame project1 = new JFrame("Infix Expression Evaluator");
        project1.setSize(400,150);
        project1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project1.add(new evaluatorPanel());
        project1.setVisible(true);
    }

    private static class evaluatorPanel extends JPanel {
        private JLabel expressionLabel = new JLabel("Enter Infix Expression");
        private JLabel resultLabel = new JLabel("Result");
        private JTextField expressionField = new JTextField("",20);
        private JTextField resultField = new JTextField("",20);

        private JButton evaluateButton = new JButton(new AbstractAction("Evaluate") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        public evaluatorPanel() {
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
