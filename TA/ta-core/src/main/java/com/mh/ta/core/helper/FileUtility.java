
package com.mh.ta.core.helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
public class FileUtility {

	private FileUtility() {
		throw new IllegalStateException("FileUtility class");
	}

	public static Path findFileOrFolderPath(String fileName, boolean findFolder) {
		try {
			return Files
					.find(FileSystems.getDefault().getPath(System.getProperty("user.dir")).getParent(), 20,
							(filePath, attribute) -> filePath.getFileName().toString().equals(fileName)
									&& attribute.isRegularFile() != findFolder && attribute.isDirectory() == findFolder)
					.findFirst()
					.orElseThrow(() -> new TestContextException("Can not find file [" + fileName + "] path"));
		} catch (IOException | NoSuchElementException e) {
			throw new TestContextException("Can not find file path " + e);
		}
	}
}
