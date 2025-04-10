import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List; // Explicitly import java.util.List to resolve ambiguity
import java.util.Map;
import javax.swing.*;

public class ExaminationProcessingGUI {

    // Class to hold student details
    static class Student {
        private final String name;
        private final String id;
        private final Map<String, Integer> subjects;
        private double averageScore;
        private String grade;
        private String recommendation;

        public Student(String name, String id) {
            this.name = name;
            this.id = id;
            this.subjects = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public Map<String, Integer> getSubjects() {
            return subjects;
        }

        public double getAverageScore() {
            return averageScore;
        }

        public void setAverageScore(double averageScore) {
            this.averageScore = averageScore;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getRecommendation() {
            return recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation;
        }
    }

    // Dynamic list to store student details
    private static final List<Student> students = new ArrayList<>();

    // Predefined array of subjects
    private static final String[] subjects = {
        "Human-Computer Interaction",
        "Software Engineering",
        "Database Systems",
        "Operating Systems",
        "Artificial Intelligence"
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Examination Processing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton addStudentsButton = new JButton("Add Students");
        JButton addGradesButton = new JButton("Add Grades");
        JButton displayReportsButton = new JButton("View Report Cards");

        addStudentsButton.addActionListener(e -> addStudents());
        addGradesButton.addActionListener(e -> addGrades());
        displayReportsButton.addActionListener(e -> showReportCardsDropdown());

        frame.add(addStudentsButton);
        frame.add(addGradesButton);
        frame.add(displayReportsButton);

        frame.setVisible(true);
    }

    // Method to add students
    public static void addStudents() {
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Student Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String id = idField.getText();
            Student student = new Student(name, id);
            students.add(student);
            JOptionPane.showMessageDialog(null, "Student added successfully!");
        }
    }

    // Method to add grades
    public static void addGrades() {
        List<Student> studentsWithoutGrades = new ArrayList<>();
        for (Student student : students) {
            if (student.getSubjects().isEmpty()) {
                studentsWithoutGrades.add(student);
            }
        }

        if (studentsWithoutGrades.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All students already have grades, Add New Student");
            return;
        }

        String[] studentNames = studentsWithoutGrades.stream().map(Student::getName).toArray(String[]::new);
        String selectedStudentName = (String) JOptionPane.showInputDialog(null, "Select a student to add grades:",
                "Add Grades", JOptionPane.QUESTION_MESSAGE, null, studentNames, studentNames[0]);

        if (selectedStudentName != null) {
            for (Student student : studentsWithoutGrades) {
                if (student.getName().equals(selectedStudentName)) {
                    for (String subject : subjects) {
                        String scoreStr = JOptionPane.showInputDialog("Enter score for " + subject + ":");
                        int score = Integer.parseInt(scoreStr);
                        student.getSubjects().put(subject, score);
                    }
                    JOptionPane.showMessageDialog(null, "Grades added successfully for " + selectedStudentName + "!");
                    break;
                }
            }
        }
    }

    // Method to calculate grades and recommendations
    public static void calculateGrades() {
        for (Student student : students) {
            double totalScore = 0;
            for (int score : student.getSubjects().values()) {
                totalScore += score;
            }
            double averageScore = totalScore / student.getSubjects().size();
            student.setAverageScore(averageScore);
            student.setGrade(determineGrade(averageScore));
            student.setRecommendation(determineRecommendation(student.getGrade()));
        }
    }

    // Method to determine grade
    public static String determineGrade(double averageScore) {
        if (averageScore >= 70) return "A";
        else if (averageScore >= 60) return "B";
        else if (averageScore >= 50) return "C";
        else if (averageScore >= 40) return "D";
        else return "F";
    }

    // Method to determine recommendation
    public static String determineRecommendation(String grade) {
        return switch (grade) {
            case "A" -> "Excellent";
            case "B" -> "Good";
            case "C" -> "Fair";
            case "D" -> "Poor";
            default -> "Very Poor";
        };
    }

    // Method to show report cards in a dropdown
    public static void showReportCardsDropdown() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students available. Please add students first.");
            return;
        }

        calculateGrades();

        String[] studentNames = students.stream().map(Student::getName).toArray(String[]::new);
        String selectedStudentName = (String) JOptionPane.showInputDialog(null, "Select a student to view the report card:",
                "View Report Cards", JOptionPane.QUESTION_MESSAGE, null, studentNames, studentNames[0]);

        if (selectedStudentName != null) {
            for (Student student : students) {
                if (student.getName().equals(selectedStudentName)) {
                    StringBuilder report = new StringBuilder();
                    report.append("Name: ").append(student.getName()).append("\n");
                    report.append("ID: ").append(student.getId()).append("\n");
                    report.append("Subjects and Scores:\n");
                    for (Map.Entry<String, Integer> entry : student.getSubjects().entrySet()) {
                        report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                    report.append("Average Score: ").append(student.getAverageScore()).append("\n");
                    report.append("Grade: ").append(student.getGrade()).append("\n");
                    report.append("Recommendation: ").append(student.getRecommendation()).append("\n");
                    report.append("Date: ").append(new SimpleDateFormat("dd-MM-yyyy").format(new Date())).append("\n");

                    JOptionPane.showMessageDialog(null, report.toString(), "Report Card", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }
}
