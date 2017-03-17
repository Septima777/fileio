package ku.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Class for copying InputStream to Outputstream.
 * 
 * @author Noppawan Kulchol
 *
 */
public class FileUtil {

	/**
	 * Copy the InputStream to the OutputStream one byte at a time. Close the
	 * InputStream and OutputStream when finished.
	 * 
	 * @param in
	 *            the things that you read from.
	 * @param out
	 *            the things that you write to.
	 */
	static void copy(InputStream in, OutputStream out) {
		int alphabet = 0;
		try {
			while ((alphabet = in.read()) > 0) {
				out.write(alphabet);
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				in.close();
				out.close();

			} catch (IOException e) {

			}

		}

	}

	/**
	 * Copy the InputStream to the OutputStream using a byte array of size
	 * blocksize. Close the InputStream and OutputStream when finished.
	 * 
	 * @param in
	 *            the things that you read from.
	 * @param out
	 *            the things that you write to.
	 * @param blocksize
	 *            byte array of size.
	 */
	static void copy(InputStream in, OutputStream out, int blocksize) {

		byte[] buffer = new byte[blocksize];
		int alphabet;
		try {
			while ((alphabet = in.read(buffer)) > 0) {
				out.write(buffer);
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				in.close();
				out.close();

			} catch (IOException e) {

			}
		}
	}

	/**
	 * Copy the InputStream to the OutputStream using a BufferedReader to read
	 * the InputStream and PrintWriter to write the OutputStream. Read and write
	 * one line at time.
	 * 
	 * @param in
	 *            the things that you read from.
	 * @param out
	 *            the things that you write to.
	 */
	static void bcopy(InputStream in, OutputStream out) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		PrintWriter writer = new PrintWriter(out);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				writer.write(line);
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				in.close();
				out.close();
				reader.close();
				writer.close();
			} catch (IOException e) {

			}
		}

	}

}
