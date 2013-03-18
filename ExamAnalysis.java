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

	while (inputDataFile.hasNextLine()) {
	    String nextLine = inputDataFile.nextLine();
	    System.out.println(nextLine.equals("") ? "We have reached \"end of file!\"" : nextLine);
	}
    }
}
