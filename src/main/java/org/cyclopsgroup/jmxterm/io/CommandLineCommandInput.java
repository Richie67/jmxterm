package org.cyclopsgroup.jmxterm.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

import org.apache.commons.lang3.Validate;

/**
 * Implementation of CommandInput with given File
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public class CommandLineCommandInput extends CommandInput {
  private final LineNumberReader in;

  /**
   * Read input from a given file
   * 
   * @param inputFile Given input file
   * @throws FileNotFoundException Thrown when file doesn't exist
   */
  public CommandLineCommandInput(String s)  {
    Validate.notNull(s, "Input can't be NULL");
    StringReader sr = new StringReader(s);
    this.in = new LineNumberReader(sr);
  }

  @Override
  public void close() throws IOException {
    in.close();
  }

  @Override
  public String readLine() throws IOException {
    return in.readLine();
  }

  @Override
  public String readMaskedString(String prompt) throws IOException {
    throw new UnsupportedOperationException("Reading password from a file is not supported");
  }
}
