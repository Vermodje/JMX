package example;

import accountServer.AccountServer;
import accountServer.AccountServerControllerMBean;
import accountServer.AccountServerImpl;
import accountServer.AccountServerController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.AccountServerServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;

public class Main {
    static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        AccountServer accountServer = new AccountServerImpl();
        //Create new MBean class
        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        //Create new MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        //Create name to MBean Server (Admin) and add ClassName which will be the management(AccountServerController)
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        //MBean server register MBean class
        mBeanServer.registerMBean(serverStatistics, name);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AccountServerServlet(accountServer)), "/admin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        logger.info("Server started");
        server.join();
    }
}
