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
	    System.out.print("What is the name of the file containing each student's responses to the 10 questions? ");
	    String inputDataFileName = keyboard.next();
	    try {
		inputDataFile = new Scanner(new File(inputDataFileName));
	    } catch (FileNotFoundException e) {
		System.out.println("Input Data File does not exist.");
		System.out.println("Check the spelling of the file name and try again.");
	    }
	}

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
	System.out.println(analyzeStudentGrades(studentAnswers, correctAnswers));
    }

    public static String analyzeStudentGrades(ArrayList<String> studentAnswers, String correctAnswers) {
	String lineSeparator = System.getProperty("line.separator");
	StringBuilder studentGradesAnalysis = new StringBuilder("Student #        Correct        Incorrect        Blank" + lineSeparator +
								"~~~~~~~~~        ~~~~~~~        ~~~~~~~~~        ~~~~~" + lineSeparator);
	for (int i = 0; i < studentAnswers.size(); i++) {
	    String studentAnswer = studentAnswers.get(i);
	    int correct = 0, incorrect = 0, blank = 0;
	    for (int j = 0; j < studentAnswer.length(); j++) {
		char answer = studentAnswer.charAt(j);
		char correctAnswer = correctAnswers.charAt(j);
		if (answer == correctAnswer) {
		    correct++;
		} else if (answer != correctAnswer) {
		    incorrect++;
		} else {
		    blank++;
		}
	    }
	    studentGradesAnalysis.append("    " + (i+1) + "                " + correct + "               " + incorrect + "              " + blank + lineSeparator);
	}

	return studentGradesAnalysis.toString();
    }
}
