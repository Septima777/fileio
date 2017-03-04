package ku.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import stopwatch.TaskTimer;

/**
 * This class defines code used to create Runnable 'tasks' to test the file copy
 * mehods in FileUtil. The subclasses should define their own run() method to
 * perform the actual task.
 * 
 * See the main method for example of how to use this. It uses the Stopwatch and
 * TaskTimer classes from stopwatch project.
 * 
 * All the file copy methods require an input stream (read from file) and an
 * output stream that writes to a file, so this class defines methods to open a
 * file as an InputStream and open an output file as an OutputStream. Files can
 * be opened via the constructor or setInput(filename) and setOutput(filename)
 * methods.
 * 
 * The method to open an InputStream shows how to use the ClassLoader to find a
 * file on the classpath of this project. The classpath includes files in your
 * project's src/ directory. It is a standard technique for opening resources.
 * 
 * @author Noppawan Kulchol
 *
 */

public class FileCopyTask implements Runnable {

	/** The InputStream that data will be read form. */
	protected InputStream in = null;
	/** The OutputStream that data will be written do. */
	protected OutputStream out = null;

	/**
	 * Default constructor doesn't do anything but may be needed for subclasses
	 * that don't invoke parameterized constructor.
	 */
	public FileCopyTask() {

	}

	/**
	 * Initialize a FileCopyTask with names of the input and output files to
	 * use.
	 * 
	 * @param infile
	 *            name of the file to use as input
	 * @param outfile
	 *            name of the file to use as output
	 * @throws RuntimeException
	 *             if either file cannot be opened
	 */
	public FileCopyTask(String infileName, String outfileName) {
		setInput(infileName);
		setOutput(outfileName);
	}

	/**
	 * Set the file to use as this object's 'in' attribute (InputStream).
	 * 
	 * @param filename
	 *            is the name of a file to read as input
	 * @throws RuntimeException
	 *             if the filename cannot be opened for input, which usually
	 *             means file not found.
	 */
	public void setInput(String filename) {
		in = null;
		try {
			in = new FileInputStream(filename);
		} catch (FileNotFoundException fne) {
			System.out.println(fne);
		}

		if (in != null)
			return;
		// The ClassLoader knows the application's classpath
		// and can open files that are on the classpath.
		// The filename can have a relative directory to refer to
		// subdirectories of the project source tree.
		ClassLoader loader = this.getClass().getClassLoader();
		in = loader.getResourceAsStream(filename);

		// If loader.getResourceAsStream() cannot create an InputStream
		// then it returns null. (No exception is thrown.)
		// If 'in' is null then throw a RuntimeException
		// so the caller will know that filename could not be opened.
		if (in == null) {
			throw new RuntimeException("File could not be opened");
		}

	}

	/**
	 * Specify a filename to use as the OutputStream (out attribute).
	 * 
	 * @param filename
	 *            is the name of the file to write to. If the file already
	 *            exists it will be overwritten.
	 * @throws RuntimeException
	 *             if the filename cannot be opened as an OutputStream.
	 */
	public void setOutput(String filename) {

		try {
			out = new FileOutputStream(filename);
		} catch (FileNotFoundException fne) {
			throw new RuntimeException("could not open output file " + filename, fne);
		}
	}

	/**
	 * The run() method should be overridden by subclasses to perform a task.
	 */
	@Override
	public void run() {
		System.out.println("You forgot to override run in subclass");

	}

	/**
	 * The toString() method should be overridden by subclasses to describe the
	 * task.
	 */
	public String toString() {
		return "Pay attention! You forgot to write toString in subclass.";
	}

	/**
	 * This main method could be in a separate class, for clarity. It uses this
	 * class to create subclasses for each task. It uses Stopwatch and TaskTimer
	 * to execute the task.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final String inputFilename = "Big-Alice-in-Wonderland.txt";
		final String outputFileName = "filecopy.txt";
		
	
		// Define a FileUtil task to copy a file byte by byte.
		// This is an anonymous class that extends FileUtilTimer.
		FileCopyTask task1 = new FileCopyTask(inputFilename, outputFileName) {
			public void run() {
				FileUtil.copy(in, out);
			}

			public String toString() {
				return "Copy a file byte-by-byte\n";
			}
		};
		task1.setInput(inputFilename);
		task1.setOutput("filecopy1.txt");

		int firstBlock = 1024;
		int secondBlock = 4 * firstBlock;
		int thirdBlock = 64 * firstBlock;

		CopyByteTask task2 = new CopyByteTask(inputFilename, outputFileName);
		BlockCopyTask task3 = new BlockCopyTask(inputFilename, outputFileName, firstBlock);
		BlockCopyTask task4 = new BlockCopyTask(inputFilename, outputFileName, secondBlock);
		BlockCopyTask task5 = new BlockCopyTask(inputFilename, outputFileName, thirdBlock);
		BufferedReaderTask task6 = new BufferedReaderTask(inputFilename, outputFileName);

		TaskTimer timer = new TaskTimer();
		timer.measureAndPrint(task1);
		timer.measureAndPrint(task2);
		timer.measureAndPrint(task3);
		timer.measureAndPrint(task4);
		timer.measureAndPrint(task5);
		timer.measureAndPrint(task6);
		

	}

}
