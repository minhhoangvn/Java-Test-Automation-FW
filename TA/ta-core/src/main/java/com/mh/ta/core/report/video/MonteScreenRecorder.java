
package com.mh.ta.core.report.video;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

/**
 * @author minhhoang
 *
 */
class MonteScreenRecorder extends ScreenRecorder {

	private String name;

	public MonteScreenRecorder(MonteScreenRecorderFormatConfig format, GraphicsConfiguration cfg, Rectangle captureArea,
			File movieFolder, String name) throws IOException, AWTException {
		super(cfg, captureArea, format.getFileFormat(), format.getScreenFormat(), format.getMouseFormat(),
				format.getAudioFormat(), movieFolder);
		this.name = name;
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {
		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory.");
		}
		return new File(movieFolder, name + "." + Registry.getInstance().getExtension(fileFormat));
	}
}
