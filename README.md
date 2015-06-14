
Running this in Sakai (SUPER EXPERIMENTAL)
------------------------------------------

Check this repo (tsugi-sakai) out into the folder:

    trunk/basiclti/tsugi-sakai

For now these are just rough notes - they won't even work yet.

Checkout and compile - this can be anywhere - it just puts a jar into the maven repo:

    https://github.com/csev/tsugi-java

Add these to your Tomcat's sakai.properties:

    tsugi.factory.tsugiClassName=org.sakaiproject.tsugi.Tsugi_Sakai

Edit the Sakai file:

    kernel/component-manager/src/main/bundle/org/sakaiproject/config/sakai-configuration.xml

Add two lines:

        <property name="sakaiPropertyToSystemPropertyMap">
            <map>
                <entry key="serverId" value="sakai.serverId"/>
                ...
        -> new  <entry key="tsugi.factory.tsugiClassName" value="tsugi.factory.tsugiClassName" />
        -> new  <entry key="tsugi.static.path" value="tsugi.static.path" />
            </map>
        </property>

And recompile kernel.  

NOTE TO SELF: By the way we need a bettter way to to set System roperties from sakai.properties.

Then check out: 

    cd trunk/basiclti
    git clone https://github.com/csev/tsugi-java-servlet

Copy the file tsugi-sakai/pom-sakai-servlet.xml into tsugi-java-servlet

Edit the tsugi-java-servlet/pom.xml and add / uncomment the
outputDirectory line to point to your Sakai Tomcat:

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <configuration>
          <webXml>src\main\webapp\WEB-INF\web.xml</webXml>
          <outputDirectory>/Users/csev/dev/sakai-scripts/apache-tomcat-8.0.23</outputDirectory>
        </configuration>
      </plugin>

To compile tsugi-java-servlet and deploy into the running Sakai:

    mvn clean install war:war

Start Sakai.

To navigate to the app go to:

    http://localhost:8080/tsugi-java-servlet

If you get 500 error that says:

    The Sakai implementation of the Tsugi APIs are not fully implemented

You did it all correctly.




