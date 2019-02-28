package com.shopping.product.core.logging;

public final class L {
    private static final SLF4JLogProvider logger = new SLF4JLogProvider();

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void errorE(String msg, Throwable t) {
        logger.errorE(msg, t);
    }


}

