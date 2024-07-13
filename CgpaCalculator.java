import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CgpaCalculator {

    private static Map<String, Integer> gradeMap = new HashMap<>();
    static {
        gradeMap.put("O", 10);
        gradeMap.put("A+", 9);
        gradeMap.put("A", 8);
        gradeMap.put("B+", 7);
        gradeMap.put("B", 6);
        gradeMap.put("C", 5);
        gradeMap.put("D", 4);
        gradeMap.put("E", 3);
        gradeMap.put("F", 0);
    }

    private static int gradeval(String grade) {
        return gradeMap.getOrDefault(grade, 0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CGPA Calculator");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.GREEN);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel sem1Label = new JLabel("Semester 1");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(sem1Label, gbc);

        JLabel gradeLabel1 = new JLabel("Grade");
        gbc.gridx = 15;
        gbc.gridy = 8;
        frame.add(gradeLabel1, gbc);

        JLabel creditLabel1 = new JLabel("Credit");
        gbc.gridx = 20;
        gbc.gridy = 8;
        frame.add(creditLabel1, gbc);

        JTextField[] sem1Grades = new JTextField[6];
        JTextField[] sem1Credits = new JTextField[6];

        for (int i = 0; i < 6; i++) {
            gbc.gridx = 0;
            gbc.gridy = 10 + i;
            frame.add(new JLabel("Subject " + (i + 1)), gbc);

            sem1Grades[i] = new JTextField(10);
            gbc.gridx = 15;
            frame.add(sem1Grades[i], gbc);

            sem1Credits[i] = new JTextField(10);
            gbc.gridx = 20;
            frame.add(sem1Credits[i], gbc);
        }

        JLabel sem2Label = new JLabel("Semester 2");
        gbc.gridx = 0;
        gbc.gridy = 20;
        frame.add(sem2Label, gbc);

        JLabel gradeLabel2 = new JLabel("Grade");
        gbc.gridx = 15;
        gbc.gridy = 23;
        frame.add(gradeLabel2, gbc);

        JLabel creditLabel2 = new JLabel("Credit");
        gbc.gridx = 20;
        gbc.gridy = 23;
        frame.add(creditLabel2, gbc);

        JTextField[] sem2Grades = new JTextField[6];
        JTextField[] sem2Credits = new JTextField[6];

        for (int i = 0; i < 6; i++) {
            gbc.gridx = 0;
            gbc.gridy = 25 + i;
            frame.add(new JLabel("Subject " + (i + 1)), gbc);

            sem2Grades[i] = new JTextField(10);
            gbc.gridx = 15;
            frame.add(sem2Grades[i], gbc);

            sem2Credits[i] = new JTextField(10);
            gbc.gridx = 20;
            frame.add(sem2Credits[i], gbc);
        }

        JButton tgpaButton1 = new JButton("TGPA");
        gbc.gridx = 25;
        gbc.gridy = 12;
        frame.add(tgpaButton1, gbc);

        JButton tgpaButton2 = new JButton("TGPA");
        gbc.gridx = 25;
        gbc.gridy = 27;
        frame.add(tgpaButton2, gbc);

        JButton cgpaButton = new JButton("CGPA");
        gbc.gridx = 0;
        gbc.gridy = 33;
        frame.add(cgpaButton, gbc);

        JButton remarkButton = new JButton("REMARK");
        gbc.gridx = 0;
        gbc.gridy = 34;
        frame.add(remarkButton, gbc);

        tgpaButton1.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                calculateTGPA(sem1Grades, sem1Credits, frame, 13, 25);
            }
        });

        tgpaButton2.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                calculateTGPA(sem2Grades, sem2Credits, frame, 28, 25);
            }
        });

        cgpaButton.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                calculateCGPA(sem1Grades, sem1Credits, sem2Grades, sem2Credits, frame, 33, 1);
            }
        });

        remarkButton.addActionListener(new ActionListener() {
            // @Override
            public void actionPerformed(ActionEvent e) {
                calculateRemark(sem1Grades, sem1Credits, sem2Grades, sem2Credits, frame, 35, 0);
            }
        });

        frame.setVisible(true);
    }

    private static void calculateTGPA(JTextField[] grades, JTextField[] credits, JFrame frame, int row, int col) {
        int[] x = new int[6];
        int y = 0;
        for (int i = 0; i < 6; i++) {
            x[i] = gradeval(grades[i].getText());
            y += Integer.parseInt(credits[i].getText());
        }
        double z = (x[0] * Integer.parseInt(credits[0].getText()) +
                    x[1] * Integer.parseInt(credits[1].getText()) +
                    x[2] * Integer.parseInt(credits[2].getText()) +
                    x[3] * Integer.parseInt(credits[3].getText()) +
                    x[4] * Integer.parseInt(credits[4].getText()) +
                    x[5] * Integer.parseInt(credits[5].getText())) / (double) y;
        JLabel resultLabel = new JLabel(String.valueOf(z));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        frame.add(resultLabel, gbc);
        frame.revalidate();
        frame.repaint();
    }

    private static void calculateCGPA(JTextField[] sem1Grades, JTextField[] sem1Credits, JTextField[] sem2Grades, JTextField[] sem2Credits, JFrame frame, int row, int col) {
        int[] x1 = new int[6];
        int y1 = 0;
        for (int i = 0; i < 6; i++) {
            x1[i] = gradeval(sem1Grades[i].getText());
            y1 += Integer.parseInt(sem1Credits[i].getText());
        }
        double z1 = (x1[0] * Integer.parseInt(sem1Credits[0].getText()) +
                     x1[1] * Integer.parseInt(sem1Credits[1].getText()) +
                     x1[2] * Integer.parseInt(sem1Credits[2].getText()) +
                     x1[3] * Integer.parseInt(sem1Credits[3].getText()) +
                     x1[4] * Integer.parseInt(sem1Credits[4].getText()) +
                     x1[5] * Integer.parseInt(sem1Credits[5].getText())) / (double) y1;

        int[] x2 = new int[6];
        int y2 = 0;
        for (int i = 0; i < 6; i++) {
            x2[i] = gradeval(sem2Grades[i].getText());
            y2 += Integer.parseInt(sem2Credits[i].getText());
        }
        double z2 = (x2[0] * Integer.parseInt(sem2Credits[0].getText()) +
                     x2[1] * Integer.parseInt(sem2Credits[1].getText()) +
                     x2[2] * Integer.parseInt(sem2Credits[2].getText()) +
                     x2[3] * Integer.parseInt(sem2Credits[3].getText()) +
                     x2[4] * Integer.parseInt(sem2Credits[4].getText()) +
                     x2[5] * Integer.parseInt(sem2Credits[5].getText())) / (double) y2;

        double cc = (z1 + z2) / 2;
        JLabel resultLabel = new JLabel(String.valueOf(cc));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        frame.add(resultLabel, gbc);
        frame.revalidate();
        frame.repaint();
    }

    private static void calculateRemark(JTextField[] sem1Grades, JTextField[] sem1Credits, JTextField[] sem2Grades, JTextField[] sem2Credits, JFrame frame, int row, int col) {
        int[] x1 = new int[6];
        int y1 = 0;
        for (int i = 0; i < 6; i++) {
            x1[i] = gradeval(sem1Grades[i].getText());
            y1 += Integer.parseInt(sem1Credits[i].getText());
        }
        double z1 = (x1[0] * Integer.parseInt(sem1Credits[0].getText()) +
                     x1[1] * Integer.parseInt(sem1Credits[1].getText()) +
                     x1[2] * Integer.parseInt(sem1Credits[2].getText()) +
                     x1[3] * Integer.parseInt(sem1Credits[3].getText()) +
                     x1[4] * Integer.parseInt(sem1Credits[4].getText()) +
                     x1[5] * Integer.parseInt(sem1Credits[5].getText())) / (double) y1;

        int[] x2 = new int[6];
        int y2 = 0;
        for (int i = 0; i < 6; i++) {
            x2[i] = gradeval(sem2Grades[i].getText());
            y2 += Integer.parseInt(sem2Credits[i].getText());
        }
        double z2 = (x2[0] * Integer.parseInt(sem2Credits[0].getText()) +
                     x2[1] * Integer.parseInt(sem2Credits[1].getText()) +
                     x2[2] * Integer.parseInt(sem2Credits[2].getText()) +
                     x2[3] * Integer.parseInt(sem2Credits[3].getText()) +
                     x2[4] * Integer.parseInt(sem2Credits[4].getText()) +
                     x2[5] * Integer.parseInt(sem2Credits[5].getText())) / (double) y2;

        double cc = (z1 + z2) / 2;
        String remark = cc > 5 ? "Pass" : "Fail";
        JLabel resultLabel = new JLabel(remark);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        frame.add(resultLabel, gbc);
        frame.revalidate();
        frame.repaint();
    }
}
