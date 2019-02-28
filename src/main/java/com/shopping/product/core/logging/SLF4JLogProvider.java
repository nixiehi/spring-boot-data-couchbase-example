package com.shopping.product.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JLogProvider {

    private static final Logger logger = LoggerFactory.getLogger("application");
    private static final Logger requestLogger = LoggerFactory.getLogger("request");


    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public void error(String msg) {
        if(logger.isErrorEnabled()) logger.error(msg);
    }

    public void errorE(String msg, Throwable t) {
        if(logger.isErrorEnabled()) logger.error(msg, t);
    }

    public void writeToRequestLog(String msg) {
        requestLogger.info(msg);
    }

    public void writeToApplicationLog(String msg) {
        logger.info(msg);
    }


}

