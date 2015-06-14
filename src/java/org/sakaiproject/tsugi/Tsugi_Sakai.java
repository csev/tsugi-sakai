package org.sakaiproject.tsugi;

import java.util.Properties;

import org.tsugi.*;
import org.tsugi.base.*;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Tsugi_Sakai extends BaseTsugi implements Tsugi 
{
    // Move to the base
    public Launch getLaunch(Properties props) 
    {
        return getLaunch(null, props, null);
    }

    public Connection getConnection() {
        return null;
    }

    public Launch getLaunch(HttpServletRequest req, Properties props, HttpServletResponse res)
    {
        BaseLaunch launch = new BaseLaunch();
        launch.complete = false;
        launch.valid = false;
        launch.error_message = "Not yet implemented";
        return launch;
    }

}
