/*
 * Name: Michael Callahan
 * Course: CSCI E-50B
 * Date: 03/25/2013
 * Assignment Number: 53
 */

// ExamAnalysis.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will print out an analysis of exam answers.
 *
 * The correct answers are entered as a string
 * and the student answers are read in from a file.
 *
 * @author Michael Callahan
 * @version Last Modified 03_25_2013
 */
public class ExamAnalysis {
    public static void main(String[] args) {
        // Initialize the exams input data file to null to enable getting an existing file
        Scanner inputDataFile = null;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("I hope you are ready to begin ...");
        System.out.println();

        // Get the correct answers
        System.out.print("Please type the correct answers to the exam questions, one right after the other: ");
        String correctAnswers = keyboard.next();
        System.out.println();
        
        // Get the exams answer data file
        // Loop until the user enters an existing file
        while (inputDataFile == null) {
            System.out.print("What is the name of the file containing each student's responses to the " + correctAnswers.length() + " questions? ");
            String inputDataFileName = keyboard.next();
            try {
                inputDataFile = new Scanner(new File(inputDataFileName));
            } catch (FileNotFoundException e) {
                System.out.println("Input Data File does not exist.");
                System.out.println("Check the spelling of the file name and try again.");
            }
        }
        System.out.println();

        // Read the student answers from the data file into a list for processing
        ArrayList<String> studentAnswers = new ArrayList<String>();
        int studentCount = 0;
        while (inputDataFile.hasNextLine()) {
            String nextLine = inputDataFile.nextLine();
            if (nextLine.equals("")) {
                System.out.println("We have reached \"end of file!\"");
            } else {
                studentAnswers.add(nextLine);
                studentCount++;
                System.out.println("Student #" + studentCount + "'s responses: " + nextLine);
            }
        }
        System.out.println();

        // Print out the student exam analysis
        System.out.println("Thank you for the data on " + studentCount + " students. Here's the analysis:");
        System.out.println();
        System.out.println(analyzeStudentGrades(studentAnswers, correctAnswers));

        // Print out the question analysis
        System.out.println(analyzeQuestions(studentAnswers, correctAnswers));
    }

    /**
     * Analyze a list of student answers and return a string containing the analysis.
     *
     * @param studentAnswers an ArrayList of student answer strings
     * @param correctAnswers the correct answers string
     * @return the analysis of all of the students answers as a String
     */
    public static String analyzeStudentGrades(ArrayList<String> studentAnswers, String correctAnswers) {
        // Add the header text
        StringBuilder studentGradesAnalysis = new StringBuilder(String.format("%-17s%-15s%-17s%s%n%-17s%-15s%-17s%s%n",
                                                                              "Student#", "Correct", "Incorrect", "Blank",
                                                                              "~~~~~~~~", "~~~~~~~", "~~~~~~~~~", "~~~~~"));

        // Iterate through each student's answers
        // to count the correct and incorrect answers
        for (int i = 0; i < studentAnswers.size(); i++) {
            String studentAnswer = studentAnswers.get(i);
            int correct = 0, incorrect = 0, blank = 0;
            for (int j = 0; j < studentAnswer.length(); j++) {
                char answer = studentAnswer.charAt(j);
                char correctAnswer = correctAnswers.charAt(j);
                if (answer == correctAnswer) {
                    correct++;
                } else if (answer == ' ') {
                    blank++;
                } else {
                    incorrect++;
                }
            }
            // Add the current student's analysis
            studentGradesAnalysis.append(String.format("%5d%17d%15d%15d%n", i+1, correct, incorrect, blank));
        }
        return studentGradesAnalysis.toString();
    }

    /**
     * Analyze a list of exam questions and return a string containing the analysis.
     *
     * @param studentAnswers an ArrayList of student answer strings
     * @param correctAnswers the correct answers string
     * @return the analysis of all the questions as a String
     */
    public static String analyzeQuestions(ArrayList<String> studentAnswers, String correctAnswers) {
        // Add the questions analysis header
        StringBuilder questionsAnalysis = new StringBuilder(String.format("%s%33s%n%s%n%n", "Question Analysis", "(* marks the correct response)", "~~~~~~~~~~~~~~~~~"));

        // Get the total number of students for calculating the answer percentages
        float numberOfStudents = studentAnswers.size();
        
        // Iterate through the questions counting
        // the number of students that gave each answer
        for (int i = 0; i < correctAnswers.length(); i++) {
            char correctAnswer = correctAnswers.charAt(i);
            questionsAnalysis.append(String.format("Question #%d:%n", i + 1));
            questionsAnalysis.append(getQuestionAnalysisHeader(correctAnswer));
            int aCount = 0, bCount = 0, cCount = 0, dCount = 0, eCount = 0, blankCount = 0;
            for (String studentAnswer : studentAnswers) {
                char studentQuestionAnswer = studentAnswer.charAt(i);
                switch (studentQuestionAnswer) {
                case 'A': aCount++;
                    break;
                case 'B': bCount++;
                    break;
                case 'C': cCount++;
                    break;
                case 'D': dCount++;
                    break;
                case 'E': eCount++;
                    break;
                case ' ': blankCount++;
                    break;
                }
            }

            // Add the student answer counts and the percentages for each question
            questionsAnalysis.append(String.format("%4d%8d%8d%8d%8d%8d%n", aCount, bCount, cCount, dCount, eCount, blankCount));
            questionsAnalysis.append(String.format("%5.1f%%%7.1f%%%7.1f%%%7.1f%%%7.1f%%%7.1f%%%n%n", (aCount/numberOfStudents)*100, (bCount/numberOfStudents)*100, (cCount/numberOfStudents)*100,
                                                   (dCount/numberOfStudents)*100, (eCount/numberOfStudents)*100, (blankCount/numberOfStudents)*100));
        }
        return questionsAnalysis.toString();
    }

    /**
     * Returns a formatted question analysis header string based on the
     * current correct answer.
     *
     * @param correctAnswer the correct answer char
     * @return the question analysis header String
     */
    public static String getQuestionAnalysisHeader(char correctAnswer) {
        // Array of header strings for each different correct answer
        String[] questionAnalysisHeaders = {String.format("%4s%8s%8s%8s%8s%8s%n", "A*", "B", "C", "D", "E", "Blank"),
                                            String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B*", "C", "D", "E", "Blank"),
                                            String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C*", "D", "E", "Blank"),
                                            String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C", "D*", "E", "Blank"),
                                            String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C", "D", "E*", "Blank"),
                                            String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C", "D", "E", "Blank*")};


        // Switch on the correct answer
        // returning the corresponding header string with the
        // correct answer marked with an asterisk
        // Return an empty string if this is not a valid answer
        switch (correctAnswer) {
        case 'A': return questionAnalysisHeaders[0];
        case 'B': return questionAnalysisHeaders[1];
        case 'C': return questionAnalysisHeaders[2];
        case 'D': return questionAnalysisHeaders[3];
        case 'E': return questionAnalysisHeaders[4];
        case ' ': return questionAnalysisHeaders[5];
        default: return "";
        }
    }
}
