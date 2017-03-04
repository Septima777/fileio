package ku.util;

/**
 * Copy task that using copy the file by use BufferedReader and PrintWriter to
 * copy lines of text.
 * 
 * @author Noppawan Kulchol
 *
 */
public class BufferedReaderTask extends FileCopyTask {

	/**
	 * Initialize a BufferedReaderTask with names of the input and output files
	 * to use.
	 * 
	 * @param infileName
	 *            the things that you read from.
	 * @param outfileName
	 *            the things that you write to.
	 */
	public BufferedReaderTask(String infileName, String outfileName) {
		super(infileName, outfileName);

	}

	/**
	 * The run() method should be overridden by subclasses to perform a task.
	 */
	@Override
	public void run() {
		FileUtil.bcopy(in, out);

	}

	/**
	 * The toString() method should be overridden by subclasses to describe the
	 * task.
	 */
	@Override
	public String toString() {
		return "Copy the file using BufferedReader and PrintWriter to copy lines of text(bcopy)\n";
	}

}
