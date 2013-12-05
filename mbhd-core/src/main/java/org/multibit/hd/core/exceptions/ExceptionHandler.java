package org.multibit.hd.core.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * <p>[Pattern] to provide the following to {@link Object}:</p>
 * <ul>
 * <li></li>
 * </ul>
 * <p>Example:</p>
 * <pre>
 * </pre>
 *
 * @since 0.0.1
 *         
 */
public class ExceptionHandler extends EventQueue implements Thread.UncaughtExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

  /**
   * <p>Set this as the default uncaught exception handler</p>
   */
  public static void registerExceptionHandler() {

    Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    System.setProperty("sun.awt.exception.handler", ExceptionHandler.class.getName());

  }

  /**
   * <p>The handler of last resort when a programming error has caused an uncaught exception to occur</p>
   *
   * @param t The cause of the problem
   */
  public static void handleThrowable(Throwable t) {

    log.error("Uncaught exception", t);

    String message = t.getMessage();

    if (message == null || message.length() == 0) {
      message = "Fatal: " + t.getClass();
    }

    // TODO Replace this with a full-on reporting system
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  protected void dispatchEvent(AWTEvent newEvent) {
    try {
      super.dispatchEvent(newEvent);
    } catch (Throwable t) {
      handleThrowable(t);
    }
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    handleThrowable(e);
  }

}
