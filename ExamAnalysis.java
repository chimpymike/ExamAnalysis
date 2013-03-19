import java.io.File;
import java.io.FileNotFoundException;
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

	int studentCount = 0;
	while (inputDataFile.hasNextLine()) {
	    String nextLine = inputDataFile.nextLine();
	    if (nextLine.equals("")) {
		System.out.println("We have reached \"end of file!\"");
	    } else {
		studentCount++;
		System.out.println("Student #" + studentCount + "'s responses: " + nextLine);
	    }
	}
	System.out.println();
	System.out.println("Thank you for the data on " + studentCount + " students. Here's the analysis:");
    }
}
