package com.cy.monitor.openapi;

import java.io.IOException;

/**
 * @author wang
 * @see
 * @since 1.0
 */
public interface Response {

    byte[] asBytes() throws IOException;
}
