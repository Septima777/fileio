package ku.util;

/**
 * Copy task that using copy the file by use a byte array of sizes.
 * 
 * @author Noppawan Kulchol
 *
 */
public class BlockCopyTask extends FileCopyTask {

	/* byte of array size*/
	private int blockSize;

	/**
	 * Intialize a BlockCopyTask with names of the input and output files to
	 * use.
	 * 
	 * @param infileName
	 *            the things that you read from.
	 * @param outfileName
	 *            the things that you write to.
	 * @param blockSize
	 *            byte array of size.
	 */
	public BlockCopyTask(String infileName, String outfileName, int blockSize) {
		super(infileName, outfileName);
		this.blockSize = blockSize;

	}

	/**
	 * Set the byte of array size.
	 * 
	 * @param blockSize
	 *            byte of array size
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	/**
	 * The run() method should be overridden by subclasses to perform a task.
	 */
	@Override
	public void run() {
		FileUtil.copy(in, out, blockSize);

	}

	/**
	 * The toString() method should be overridden by subclasses to describe the
	 * task.
	 * 
	 */
	@Override
	public String toString() {
		return String.format("Copy the file using a byte array of size %dKB\n", this.blockSize);
	}
}
