package com.study;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author william
 */

public class TimeLine {

	public static final String CLASS_NAME = "TimeLine";

	protected long startTimeStampInSeconds, endTimeStampInSeconds;
	protected long length;
	public String fileNameBase;
	private final String FILE_NAME_EXTENSION = BasicVariables.DOT + TimeLine.CLASS_NAME;
	private final File TARGET_FILE = new File(
			BasicVariables.STUDY_PATH_STRING,
			String.valueOf( BasicVariables.timeLineIdLoadUp )
	);

	static FileManager.FileCreator fc = new FileManager.FileCreator();
	static BasicOutput basicOutput = new BasicOutput();
	static TimeLine timeLine = new TimeLine();

	protected static void preparation(
			LocalDateTime startTimeStamp,
			LocalDateTime endTimeStamp
	) {

		try {
			timeLine.length = BasicFunctions.durationOfLocalDateTimeToSecond(
					startTimeStamp,
					endTimeStamp,
					ZoneOffset.ofHours( BasicVariables.TIME_ZONE_OFFSET_EAST_EIGHT )
			);
			timeLine.startTimeStampInSeconds = BasicFunctions.durationOfLocalDateTimeToSecond(
					BasicVariables.META_YEAR_LOCAL_DATE_TIME,
					startTimeStamp,
					ZoneOffset.ofHours( BasicVariables.TIME_ZONE_OFFSET_EAST_EIGHT )
			);
			timeLine.endTimeStampInSeconds = BasicFunctions.durationOfLocalDateTimeToSecond(
					BasicVariables.META_YEAR_LOCAL_DATE_TIME,
					endTimeStamp,
					ZoneOffset.ofHours( BasicVariables.TIME_ZONE_OFFSET_EAST_EIGHT )
			);

			// Create new file(s) for newTimeLine & Output procession result (Success or not)
			if ( fc.onCreate( timeLine.TARGET_FILE, false ) ) {
				basicOutput.log(
						BasicVariables.BASIC_OUTPUT_LOG_TYPE_INFO,
						FileManager.FileCreator.CLASS_NAME,
						BasicVariables.FILE_MANAGER_FILE_CREATING_SUCCESS
				);
			}

		} catch ( Exception e ) {

			basicOutput.log(
					BasicVariables.BASIC_OUTPUT_LOG_TYPE_ERROR,
					TimeLine.CLASS_NAME,
					e.getLocalizedMessage()
			);
		}

	}

	public static TimeLine onCreate(
			LocalDateTime startTimeStamp,
			LocalDateTime endTimeStamp,
			long length
	) {

		TimeLine newTimeLine = new TimeLine();

		TimeLine.preparation(
				startTimeStamp,
				endTimeStamp
		);

		// Everything's fine, let's go!

		timeLine.fileNameBase = String.valueOf( BasicVariables.timeLineIdLoadUp );

		newTimeLine.length = length;
		newTimeLine.startTimeStampInSeconds = BasicFunctions.durationOfLocalDateTimeToSecond(
				BasicVariables.META_YEAR_LOCAL_DATE_TIME,
				startTimeStamp
		);
		newTimeLine.endTimeStampInSeconds = BasicFunctions.durationOfLocalDateTimeToSecond(
				BasicVariables.META_YEAR_LOCAL_DATE_TIME,
				endTimeStamp
		);

		return newTimeLine;
	}
}