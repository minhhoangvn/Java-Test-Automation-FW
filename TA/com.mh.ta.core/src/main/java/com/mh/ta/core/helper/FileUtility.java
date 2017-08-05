package com.mh.ta.core.helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtility {
	public static Path findFileOrFolderPath(String fileName, boolean findFolder) {
		try {
			return Files
					.find(FileSystems.getDefault().getPath(System.getProperty("user.dir")).getParent(), 20,
							(filePath, attribute) -> filePath.getFileName().toString().equals(fileName)
									&& attribute.isRegularFile() != findFolder && attribute.isDirectory() == findFolder)
					.findFirst().get();
		} catch (IOException e) {
			throw new RuntimeException("Can not find file path");
		}
	}
}
