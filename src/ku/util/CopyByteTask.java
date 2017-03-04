package ku.util;

/**
 * Copy task that using copy the file one byte at a time.
 * 
 * @author Noppawan Kulchol
 *
 */
public class CopyByteTask extends FileCopyTask {

	/**
	 * Initialize a CopyByteTask with names of the input and output files to
	 * use.
	 * 
	 * @param infileName
	 *            the things that you read from.
	 * @param outfileName
	 *            the things that you write to.
	 */
	public CopyByteTask(String infileName, String outfileName) {
		super(infileName, outfileName);
	}

	/**
	 * The run() method should be overridden by subclasses to perform a task.
	 */
	@Override
	public void run() {
		FileUtil.copy(in, out);
	}

	/**
	 * The toString() method should be overridden by subclasses to describe the
	 * task.
	 */
	@Override
	public String toString() {
		return "Copy file one byte at a time\n";
	}

}
