package com.example.springretry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRetryApplicationTests {
	@Autowired
	BackendAdapterImpl backendAdapter;

	@Test
	public void contextLoads() throws RemoteServiceNotAvailableException, JsonProcessingException {
        final TestAppender appender = new TestAppender();
        final Logger logger = Logger.getRootLogger();
        logger.addAppender(appender);
		backendAdapter.getBackendResponse(true, true);
		logger.removeAppender(appender);
        final List<LoggingEvent> log = appender.getLog();
        System.err.println(log.size());
        System.err.println(appender.getLog().get(0).getLoggerName());
        System.err.println(appender.getLog().get(1).getLoggerName());
        System.err.println(appender.getLog().get(2).getLoggerName());
        System.err.println(appender.getLog().get(0).getMessage());
        final LoggingEvent firstLogEntry = log.get(0);
        assertThat(firstLogEntry.getLevel(), is(Level.INFO));
        assertThat((String) firstLogEntry.getMessage(), is("tetststts gs s s s shdsbdhsadsjj"));
        assertThat(firstLogEntry.getLoggerName(), is("com.example.springretry.BackendAdapterImpl"));
		//ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LogManager.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		Assert.assertTrue(true);
	}

}

class TestAppender extends AppenderSkeleton {
    private final List<LoggingEvent> log = new ArrayList<LoggingEvent>();

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(final LoggingEvent loggingEvent) {
        log.add(loggingEvent);
    }

    @Override
    public void close() {
    }

    public List<LoggingEvent> getLog() {
        return new ArrayList<LoggingEvent>(log);
    }
}