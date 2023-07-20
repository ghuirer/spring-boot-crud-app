package logs;

import org.apache.log4j.Logger;

public class LogController {

	private static Logger logger = Logger.getLogger(LogController.class);

	public static void logException(Exception e)
	{
	// Logging various log level messages
	logger.trace("Log level: TRACE");
	logger.info("Log level: INFO"+e.getMessage());
	logger.debug("Log level: DEBUG");
	logger.error("Log level: ERROR");
	logger.warn("Log level: WARN");
	}
	
	public static void log(String str)
	{
	// Logging various log level message
	logger.info("Log level: INFO"+str);
	}
}
