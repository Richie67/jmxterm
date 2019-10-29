package org.cyclopsgroup.jmxterm.cmd;

import org.cyclopsgroup.jcli.annotation.Argument;
import org.cyclopsgroup.jcli.annotation.Cli;
import org.cyclopsgroup.jcli.annotation.Option;
import org.cyclopsgroup.jmxterm.Command;
import org.cyclopsgroup.jmxterm.JavaProcess;
import org.cyclopsgroup.jmxterm.Session;

import javax.management.JMException;
import java.io.IOException;
import java.util.List;

/**
 * Command to list all running local JVM processes
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
@Cli(name = "findvm", description = "find running local vm with a given class name of argument and open it")
public class FindvmCommand extends Command {
    
    private String className;

  @Override
  public void execute() throws IOException, JMException {
    Session session = getSession();
    int pid = -1;
    List<JavaProcess> processList = session.getProcessManager().list();
    boolean done = false;
    for (JavaProcess p : processList) {
        if (p.getDisplayName().indexOf(className) >= 0) {
            pid = p.getProcessId();
            OpenCommand openCommand = new OpenCommand();
            openCommand.setUrl("" + pid);
            openCommand.setSession(session);
            openCommand.execute();
            done = true;
            break;
        }
    }
    if (!done) {
        throw new IllegalStateException("command findvm did not succeed successfully");
    }
  }

  /**
   * @param url URL of MBean service to open
   */
  @Argument(displayName = "class", description = "classname")
  public final void setClassName(String className) {
    this.className = className;
  }
  
}
