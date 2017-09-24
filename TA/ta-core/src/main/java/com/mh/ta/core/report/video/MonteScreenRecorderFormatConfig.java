
package com.mh.ta.core.report.video;

import org.monte.media.Format;

/**
 * @author minhhoang
 *
 */
class MonteScreenRecorderFormatConfig {
	private Format fileFormat;
	private Format screenFormat;
	private Format mouseFormat;
	private Format audioFormat;

	public Format getFileFormat() {
		return fileFormat;
	}

	public Format getScreenFormat() {
		return screenFormat;
	}

	public Format getMouseFormat() {
		return mouseFormat;
	}

	public Format getAudioFormat() {
		return audioFormat;
	}

	public MonteScreenRecorderFormatConfig(Format fileFormat, Format screenFormat, Format mouseFormat,
			Format audioFormat) {
		super();
		this.fileFormat = fileFormat;
		this.screenFormat = screenFormat;
		this.mouseFormat = mouseFormat;
		this.audioFormat = audioFormat;
	}
}
