package com.renhouse.lisentner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;

public class OnlineNumberListener implements HttpSessionListener {
    private static int activeSessions = -1;



    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        activeSessions += 1;
        HttpSession session = se.getSession();
        session.setAttribute("online",activeSessions);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        if (activeSessions > 0){
            activeSessions -= 1;
        }
        HttpSession session = se.getSession();
        session.setAttribute("online",activeSessions);

    }

    public static int getActiveSessions() {
        return activeSessions;
    }
}
