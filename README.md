
Running this in Sakai (SUPER EXPERIMENTAL)
------------------------------------------

Check this out into the folder:

    trunk/basiclti/tsugi-java

For now these are just rough notes - they won't even work yet.

Checkout and compile

    https://github.com/csev/tsugi-java

Copy the resulting jar into Tomcat's shared library.

Add these to your sakai.properties:

    tsugi.factory.tsugiClassName=org.sakaiproject.tsugi.Tsugi_Sakai

Edit the file:

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

And recompile kernel.  By the way we need a bettter way to do this - note to self.

Come in and run maven so as to get the resulting jar file in shared.

Then check out: 

    cd basiclti
    git clone https://github.com/csev/tsugi-sakai

Edit the pom.xml and add / uncomment the
outputDirectory line to point to the Sakai Tomcat:

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <configuration>
          <webXml>src\main\webapp\WEB-INF\web.xml</webXml>
          <outputDirectory>/Users/csev/dev/sakai-scripts/apache-tomcat-8.0.23</outputDirectory>
        </configuration>
      </plugin>

To compile and delpoy into the running Sakai:

    mvn clean install war:war

Start Sakai.

To navigate to the app go to:

    http://localhost:8080/tsugi-java-servlet

And of course it will fail miserably - but that s a start.


