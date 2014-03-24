package org.multibit.hd.brit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;

/**
 * <p>Utility to provide the following to file system:</p>
 * <ul>
 * <li>Handling temporary files</li>
 * <li>Reading and writing files</li>
 * </ul>
 *
 * @since 0.0.1
 *  
 */
public class FileUtils {

  private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

  private static final int MAX_FILE_SIZE = 1024 * 1024 * 1024; // Do not read files greater than 1 gigabyte.

  /**
   * Utilities have private constructor
   */
  private FileUtils() {
  }

  public static byte[] readFile(File file) throws IOException {
       if (file == null) {
           throw new IllegalArgumentException("File must be provided");
       }

       if ( file.length() > MAX_FILE_SIZE ) {
           throw new IOException("File '" + file.getAbsolutePath() + "' is too large to input");
       }

       byte []buffer = new byte[(int) file.length()];
       InputStream ios = null;
       try {
           ios = new FileInputStream(file);
           if ( ios.read(buffer) == -1 ) {
               throw new IOException("EOF reached while trying to read the whole file");
           }
       } finally {
           try {
                if ( ios != null )  {
                     ios.close();
                }
           } catch ( IOException e) {
               log.error(e.getClass().getName() + " " + e.getMessage());
           }
       }

       return buffer;
   }

  /**
    * Write a file from the inputstream to the outputstream
    */
   public static void writeFile(InputStream in, OutputStream out)
           throws IOException {
     byte[] buffer = new byte[1024];
     int len;

     while ((len = in.read(buffer)) >= 0)
       out.write(buffer, 0, len);

     in.close();
     out.close();
   }

  public static File makeRandomTemporaryDirectory() throws IOException {
     File temporaryFile = File.createTempFile("nothing", "nothing");
     temporaryFile.deleteOnExit();

     File parentDirectory = temporaryFile.getParentFile();

     File temporaryDirectory = new File(parentDirectory.getAbsolutePath() + File.separator + ("" + (new Random()).nextInt(1000000)));
     temporaryDirectory.mkdir();
     temporaryDirectory.deleteOnExit();

     return temporaryDirectory;
   }
}
