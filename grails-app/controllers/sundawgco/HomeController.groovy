package sundawgco

import org.codehaus.groovy.grails.commons.ConfigurationHolder as configurationHolder

import com.sundawgco.widget.Signature;

class HomeController {
  def config = null

  def beforeInterceptor = {
    if (config == null) {
      config = new ConfigSlurper().parse(new File('grails-app/conf/App.groovy').toURL())
    }
  }

  def index = { 
    if (request.method == "GET") {
      return [images : config.images]
    } 
    else {
      response.status = 400
    }
  }

  def widget = {
    if (request.method == "GET") {
      def nonce = System.currentTimeMillis()
      def signature = Signature.generate(config.widget.partnerId, config.widget.partnerSecret, config.widget.appId, nonce.toString())
      params.put("signature", signature)
      params.put("partnerId", config.widget.partnerId)
      params.put("appId", config.widget.appId)
      params.put("transactionToken", System.currentTimeMillis())
      params.put("nonce", nonce);
       
      return [
        host: config.widget.host,
        params: params
      ]
    }
    else {
      response.status = 400
    }
  }
}
