import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import com.hacom.liguiweb.util.CryptoConverter
import com.hacom.liguiweb.util.LogFileHeaderPatternLayout
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def targetDir = System.getProperty("logging.path")?:'.'
/*if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}*/

conversionRule("sample", CryptoConverter)
appender("FILE", FileAppender) {
    file = "${targetDir}/limanager.log"
    append = true
    encoder(LayoutWrappingEncoder) {
        layout(LogFileHeaderPatternLayout) {
            filePath = "${targetDir}/limanager.log"
            header = "con,thread,sample,msg"
            pattern = "%-4relative,[%thread],%sample,%msg%n"
        }
    }
}
logger("com.hacom.limanager", DEBUG, ['FILE'], false)

root(ERROR, ['STDOUT'])
