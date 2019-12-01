package ru.sbrf.ku.library;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import ru.sbrf.ku.library.servlets.BookListServlet;
import ru.sbrf.ku.library.servlets.BookServlet;
import ru.sbrf.ku.library.servlets.IndexServlet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

public class WebStarter {
    private final static int PORT = 8080;

    public static void main (String[] args) throws Exception {
        org.h2.tools.Server server = org.h2.tools.Server.createTcpServer().start();
        new WebStarter().start();
        server.stop();
    }

    private void start() throws Exception {
        Server server = createServer(PORT);
        server.start();
        server.join();
    }

    public Server createServer(int port) throws MalformedURLException {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new IndexServlet()), "/index");
        context.addServlet(new ServletHolder(new BookListServlet()), "/booklist");
        context.addServlet(new ServletHolder(new BookServlet()), "/book/*");


        Server server = new Server(port);
        server.setHandler(new HandlerList(context));

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{createSecurityHandler(context)});
        server.setHandler(handlers);
        return server;
    }

    private SecurityHandler createSecurityHandler(ServletContextHandler context) throws MalformedURLException {
        Constraint constraint = new Constraint();
        constraint.setName("auth");
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[]{"client", "librarian"});

        ConstraintMapping mapping = new ConstraintMapping();
        mapping.setPathSpec("/book/*");
        mapping.setConstraint(constraint);

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        security.setAuthenticator(new BasicAuthenticator());

        URL propFile = null;
        File realmFile = new File("./realm.properties");
        if (realmFile.exists()) {
            propFile = realmFile.toURI().toURL();
        }
        if (propFile == null) {
            System.out.println("local realm config not found, looking into Resources");
            propFile = WebStarter.class.getClassLoader().getResource("realm.properties");
        }

        if (propFile == null) {
            throw new RuntimeException("Realm property file not found");
        }

        security.setLoginService(new HashLoginService("MyRealm", propFile.getPath()));
        security.setHandler(new HandlerList(context));
        security.setConstraintMappings(Collections.singletonList(mapping));

        return security;
    }

}
