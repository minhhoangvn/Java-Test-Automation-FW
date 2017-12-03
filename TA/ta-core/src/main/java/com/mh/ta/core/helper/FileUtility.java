
package com.mh.ta.core.helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

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
		Stream<Path> path = null;
		try {
			Path rootPath = FileSystems.getDefault().getPath(System.getProperty("user.dir")).getParent();
			int folderDepth = 20;
			path = Files.find(rootPath, folderDepth,
					(filePath, attribute) -> filePath.getFileName().toString().equals(fileName)
							&& attribute.isRegularFile() != findFolder && attribute.isDirectory() == findFolder);
			return path.findFirst()
					.orElseThrow(() -> new TestContextException("Can not find file [" + fileName + "] path"));
		} catch (IOException | NoSuchElementException e) {
			throw new TestContextException("Can not find file path " + e);
		} finally {
			if (path != null)
				path.close();
		}
	}
}
