
package com.mh.ta.core.report.video;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.helper.DateTimeUtility;

/**
 * @author minhhoang
 *
 */
public class VideoRecord implements IVideoRecord {
	private Logger log = LoggerFactory.instance().createClassLogger(getClass());
	private String folder = System.getProperty("user.dir");
	private ScreenRecorder screenRecorder;
	private String videoNameStorage;

	public VideoRecord() {
	}

	public VideoRecord(String folder) {
		this.folder = folder;
	}

	private void record(String videoName) throws IOException, AWTException {
		String name = videoName + DateTimeUtility.getDateStringFormat("yyyy_MM_dd_HH_mm_ss");
		File folderName = new File(this.folder);
		this.videoNameStorage = name + "_" + Thread.currentThread().getId();		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		Rectangle captureArea = new Rectangle(0, 0, width, height);

		GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		MonteScreenRecorderFormatConfig format = new MonteScreenRecorderFormatConfig(
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null);
		this.screenRecorder = new MonteScreenRecorder(format, graphicsConfiguration, captureArea, folderName,
				this.videoNameStorage);
		this.screenRecorder.start();
	}

	private void stopRecord() {
		try {
			log.info("Stop record video with video file " + videoNameStorage);
			this.screenRecorder.stop();
		} catch (IOException e) {
			log.error("Error in stop record video", e.getCause());
		}
	}

	@Override
	public void startRecord(String videoName) {
		try {
			log.info("Start record video with video file " + videoName);
			this.record(videoName);
		} catch (IOException | AWTException e) {
			log.error("Error in start record video", e.getCause());
		}
	}

	@Override
	public String stopRecording() {
		this.stopRecord();
		return videoNameStorage;
	}
}
