package com.thinkgem.jeesite.autocode.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileUtils {
	public static void copyURLToFile(URL source, File destination)
			throws IOException {
		java.io.InputStream input = source.openStream();
		try {
			FileOutputStream output = openOutputStream(destination);
			try {
				copy(input, output);
			} finally {
				closeQuietly(output);
			}
		} finally {
			closeQuietly(input);
		}
	}

	public static FileOutputStream openOutputStream(File file)
			throws IOException {
		if (file.exists()) {
			if (file.isDirectory())
				throw new IOException("File '" + file
						+ "' exists but is a directory");
			if (!file.canWrite())
				throw new IOException("File '" + file
						+ "' cannot be written to");
		} else {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists() && !parent.mkdirs())
				throw new IOException("File '" + file
						+ "' could not be created");
		}
		return new FileOutputStream(file);
	}

	public static int copy(InputStream input, OutputStream output)
			throws IOException {
		long count = copyLarge(input, output);
		if (count > 2147483647L)
			return -1;
		else
			return (int) count;
	}

	public static long copyLarge(InputStream input, OutputStream output)
			throws IOException {
		byte buffer[] = new byte[4096];
		long count = 0L;
		for (int n = 0; -1 != (n = input.read(buffer));) {
			output.write(buffer, 0, n);
			count += n;
		}

		return count;
	}

	public static void closeQuietly(OutputStream output) {
		try {
			if (output != null)
				output.close();
		} catch (IOException ioe) {
		}
	}

	public static void closeQuietly(InputStream input) {
		try {
			if (input != null)
				input.close();
		} catch (IOException ioe) {
		}
	}
}
