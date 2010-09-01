// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

// Workflow Application specific properties.

// These are the photo gallery images.
images = [
  'http://s3-p.animoto.com/Image/t9KLX4mC4sAbsAAjIKPMuQ/l.jpg',
  'http://s3-p.animoto.com/Image/J14rwixVGvmx1coDBuiKyg/l.jpg',
  'http://s3-p.animoto.com/Image/aI2R2FmVetp8Ux7McvN1Eg/l.jpg',
  'http://s3-p.animoto.com/Image/aQ7dx3E9b0ypOm2BsMJPQQ/l.jpg',
  'http://s3-p.animoto.com/Image/blFNy7LuTOCCRbLZIaYvBg/l.jpg',
  'http://s3-p.animoto.com/Image/RSlVAvr57bj1de7YxSf9RQ/l.jpg'
]

// These are our Animoto credentials for the Workflow Widget and Platform API.
widget.partnerId      = "ac42be375feb79fe55e6"
widget.partnerSecret  = "93823b7bed3552131a7f"
widget.host           = "https://widget-service-sandbox.animoto.com"
widget.appId          = "46053ed4739ffc1aa0c5"

platform.key          = "99320bd08867012dc34a12313b045877"
platform.secret       = "867319c36489597437c6965fea6b1512d7ece403"
api2.host             = "https://api2-sandbox.animoto.com"
