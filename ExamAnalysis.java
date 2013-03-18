import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExamAnalysis {
    public static void main(String[] args) {
	System.out.println("Exam Analysis");

	Scanner inputDataFile = null;

	try {
	    inputDataFile = new Scanner(new File("exams.dat"));
	} catch (FileNotFoundException e) {
	    System.out.println("Input Data File does not exist.");
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
