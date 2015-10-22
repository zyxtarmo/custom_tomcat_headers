# custom_tomcat_headers

Quick'n'dirty way to add custom static headers to Apache Tomcat globally

1. Copy .jar in github target/ to lib/ of your Tomcat
2. and add to conf/web.xml:
```
<filter>
        <filter-name>CustomTomcatHeaders</filter-name>
        <filter-class>net.suvaline.CustomTomcatHeaders</filter-class>
	<init-param>
      		<param-name>X-Custom-Header</param-name>
      		<param-value>STATIC</param-value>
    	</init-param>
</filter>

<filter-mapping>
       	<filter-name>CustomTomcatHeaders</filter-name>
       	<url-pattern>/*</url-pattern>
</filter-mapping>
```

Use it at your own risk.

zyxtarmo @ Oct 2015
