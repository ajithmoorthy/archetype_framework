#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.logreports;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ${package}.constants.FileConstants;
import ${package}.extentreport.Extent;
import com.relevantcodes.extentreports.LogStatus;
/**
 *  This logReporter class is used for the implement log 4j
 * and captured the actions which is performed in testscripts 
 */
public class LogReporter extends Extent {
	Logger logger = null;
	/**
	 * This logReportMessage method is load the log4j.properties file to store the log details of the test script
	 * Logger class getLogger method is used to find or create a logger with the name passed as parameter.
	 * @param message
	 * logger info store the message what we passing.
	 */
	public void logReportMessage(String message) {
		PropertyConfigurator.configure(FileConstants.LOG4J_CONFIG_PROPERTY_PATH);
		logger = Logger.getLogger(LogReporter.class.getName());
		logger.info(message);
		extentlogger.log(LogStatus.INFO,message);
		
	}
}

