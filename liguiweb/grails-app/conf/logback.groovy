import ch.qos.logback.classic.filter.LevelFilter
import com.hacom.li.util.CryptoConverter
import com.hacom.li.util.LogFileHeaderPatternLayout
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter
import static ch.qos.logback.core.spi.FilterReply.DENY
import static ch.qos.logback.core.spi.FilterReply.ACCEPT

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

def targetDir = BuildSettings.TARGET_DIR

appender("FULL_STACKTRACE", FileAppender) {
    file = "${targetDir}/stacktrace.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
    filter(LevelFilter) {
        level = ERROR
        onMismatch = DENY
        onMatch = ACCEPT
    }
}

def isDevelopmentMode = Environment.isDevelopmentMode()
def logFile = "liguiweb.log"
conversionRule("msgencrypt", CryptoConverter)
appender("FILE", RollingFileAppender) {
    file = "${targetDir}/${logFile}"
    append = true
    encoder(LayoutWrappingEncoder) {
        layout(LogFileHeaderPatternLayout) {
            filePath = "${targetDir}/${logFile}"
            header = "datetime,level,thread,msg"
            def typemsg = "%msgencrypt"
            if (isDevelopmentMode)
                typemsg = "%msg"
            pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS},%5p,[%thread],"+typemsg+"%n"
        }
    }
    rollingPolicy(SizeAndTimeBasedRollingPolicy) {
        fileNamePattern = "${targetDir}/${logFile}.%d{yyyy-MM-dd}_%i.zip" //diario
        maxFileSize = "10MB" //archivos de 10 megas
        maxHistory = 30 //historia de 30 dias
        totalSizeCap = "20GB" // total de log (todos los archivos)
    }
    //https://gist.github.com/finnjohnsen/87b14223aba07cf5f7c3c84c4f474e0f
    filter(LevelFilter) {
        level = ERROR
        onMismatch = ACCEPT
        onMatch = DENY
    }
}
logger("com.hacom.liguiweb", DEBUG, ['FILE','FULL_STACKTRACE'], false)

root(ERROR, ['STDOUT'])
