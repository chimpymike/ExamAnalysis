import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamAnalysis {
    public static void main(String[] args) {
	Scanner inputDataFile = null;
	Scanner keyboard = new Scanner(System.in);

	System.out.println("I hope you are ready to begin ...");
	System.out.println();

	System.out.print("Please type the correct answers to the exam questions, one right after the other: ");
	String correctAnswers = keyboard.next();
	System.out.println();
	
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
	System.out.println("Thank you for the data on " + studentCount + " students. Here's the analysis:");
	System.out.println();
	System.out.println(analyzeStudentGrades(studentAnswers, correctAnswers));

	System.out.println(analyzeQuestions(studentAnswers, correctAnswers));
    }

    public static String analyzeStudentGrades(ArrayList<String> studentAnswers, String correctAnswers) {
	StringBuilder studentGradesAnalysis = new StringBuilder(String.format("%-17s%-15s%-17s%s%n%-17s%-15s%-17s%s%n",
									      "Student#", "Correct", "Incorrect", "Blank",
									      "~~~~~~~~", "~~~~~~~", "~~~~~~~~~", "~~~~~"));
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
	    studentGradesAnalysis.append(String.format("%5d%17d%15d%15d%n", i+1, correct, incorrect, blank));
	}

	return studentGradesAnalysis.toString();
    }

    public static String analyzeQuestions(ArrayList<String> studentAnswers, String correctAnswers) {
	StringBuilder questionsAnalysis = new StringBuilder(String.format("%s%33s%n%s%n%n", "Question Analysis", "(* marks the correct response)", "~~~~~~~~~~~~~~~~~"));

	float numberOfStudents = studentAnswers.size();
	
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
	    questionsAnalysis.append(String.format("%4d%8d%8d%8d%8d%8d%n", aCount, bCount, cCount, dCount, eCount, blankCount));
	    questionsAnalysis.append(String.format("%4.1f%%%8.1f%%%8.1f%%%8.1f%%%8.1f%%%8.1f%%%n%n", (aCount/numberOfStudents)*100, (bCount/numberOfStudents)*100, (cCount/numberOfStudents)*100,
						   (dCount/numberOfStudents)*100, (eCount/numberOfStudents)*100, (blankCount/numberOfStudents)*100));
	}
	return questionsAnalysis.toString();
    }

    public static String getQuestionAnalysisHeader(char correctAnswer) {
	String[] questionAnalysisHeaders = {String.format("%4s%8s%8s%8s%8s%8s%n", "A*", "B", "C", "D", "E", "Blank"),
					    String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B*", "C", "D", "E", "Blank"),
					    String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C*", "D", "E", "Blank"),
					    String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C", "D*", "E", "Blank"),
					    String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C", "D", "E*", "Blank"),
					    String.format("%4s%8s%8s%8s%8s%8s%n", "A", "B", "C", "D", "E", "Blank*")};

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
