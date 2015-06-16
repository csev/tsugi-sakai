
Running this in Sakai (SUPER EXPERIMENTAL)
------------------------------------------

Note - this just a skeleton - there is no implementations - just enough to get the right 
Tsugi implementation class loaded.  But there is nothing in that implementation class.

Check this repo (tsugi-sakai) out into the folder:

    trunk/basiclti/tsugi-sakai

Don't compile tsugi-sakai until instructored to do so later.

There are three repos that you will be working with

* https://github.com/csev/tsugi-java - Contains the APIs the Java Tsugi 
* https://github.com/csev/tsugi-java-servlet - The sample servlet using the Java Tsugi API
* https://github.com/csev/tsugi-sakai - (this repo) - Contains the Sakai-Tsugi bridge code

As you go through these instructions, be careful to look at the repo to make sure that you are
working with the correct repo of the above three.

Pre-Requisites
--------------

Checkout and compile the Tsugi Java library - this can be anywhere in your folder 
tree - "mvn clean install" just puts a jar into the maven repo that is needed for tsugi-sakai.

    git clone https://github.com/csev/tsugi-java
    cd tsugi-java
    mvn clean install

Add this to your Tomcat's sakai.properties:

    tsugi.factory.tsugiClassName=org.sakaiproject.tsugi.Tsugi_Sakai

Edit the Sakai file:

    kernel/component-manager/src/main/bundle/org/sakaiproject/config/sakai-configuration.xml

Add this line:

        <property name="sakaiPropertyToSystemPropertyMap">
            <map>
                <entry key="serverId" value="sakai.serverId"/>
                ...
        -> new  <entry key="tsugi.factory.tsugiClassName" value="tsugi.factory.tsugiClassName" />
            </map>
        </property>

And recompile kernel.  

NOTE TO SELF: By the way we need a bettter way to to set System properties from sakai.properties - see https://jira.sakaiproject.org/browse/KNL-1361

Setting up the Tsugi-Sakai Bridge
---------------------------------

Now compile this  repo (tsugi-sakai) out into the folder.  This just puts the Sakai-Tsugi-Bridge
jar into your maven repo.

    cd trunk/basiclti/tsugi-sakai
    mvn clean install

Then check out: 

    cd trunk/basiclti
    git clone https://github.com/csev/tsugi-java-servlet

Copy the file tsugi-sakai/pom-sakai-servlet.xml to tsugi-java-servlet/pom.xml

Edit the newly updated tsugi-java-servlet/pom.xml and add / uncomment the
outputDirectory line to point to *your* Sakai Tomcat:

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <configuration>
          <webXml>src\main\webapp\WEB-INF\web.xml</webXml>
          <outputDirectory>/Users/csev/dev/sakai-scripts/apache-tomcat-8.0.23</outputDirectory>
        </configuration>
      </plugin>

I wish there were a cleaner way to do this on the following mvn command, that would be preferable.

To compile tsugi-java-servlet and deploy into the running Sakai:

    mvn clean install war:war

Start your sakai instance

To navigate to the app go to:

    http://localhost:8080/tsugi-java-servlet

Don't be surprised - all you get is a 500 error that says:

    The Sakai implementation of the Tsugi APIs are not fully implemented

Since the implementation is empty - this is the expected outcome.




