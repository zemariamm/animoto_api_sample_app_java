package sundawgco

import java.util.regex.Pattern;

import com.animoto.api.ApiClient;
import com.animoto.api.RenderingManifest;
import com.animoto.api.RenderingParameters;
import com.animoto.api.enums.Resolution;
import com.animoto.api.enums.Format;
import com.animoto.api.enums.Framerate;
import com.animoto.api.resource.Storyboard;
import com.animoto.api.resource.RenderingJob;
import com.animoto.api.resource.Video;

import com.sundawgco.widget.Signature;

class HomeController {
  def config = null
  def apiClient = null

  def beforeInterceptor = {
    if (config == null) {
      config = grailsApplication.config
    }
    if (apiClient == null) {
      apiClient = new ApiClient(config.platform.key, config.platform.secret, config.api2.host)
    }
  }

  /**
   * Homepage that renders a faux photo album gallery.
   */
  def index = { 
    return [images : config.images]
  }

  /**
   * Create a workflow signature and create widget in iframe.
   */
  def widget = {
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

  /**
   * Play the video in a standard web video player.
   */
  def play = {
    def videoUrl = params['links[file]']
    return [videoUrl: videoUrl]
  }

  /**
   * Create a video Render with a storyboard from the widget and display an AJAX polling page.
   */
  def finalize = {
    def storyboardUrl = params['links[storyboard]']
    def renderingManifest = new RenderingManifest()
    def renderingParameters = new RenderingParameters()
    def storyboard = new Storyboard()
  
    /**
     * Remember this is Groovy which auto will eventually auto resolve Java setters/getters on the API Client. :)
     */ 
    storyboard.url = storyboardUrl

    renderingParameters.resolution = Resolution.R_720P
    renderingParameters.framerate = Framerate.F_30 
    renderingParameters.format = Format.H264

    renderingManifest.storyboard = storyboard
    renderingManifest.renderingParameters = renderingParameters

    def renderingJob = apiClient.render(renderingManifest)
    return [videoUrl: renderingJob.url]
  }

  /**
   * AJAX endpoint to determine if the video job is complete and return JSON.
   *
   * The user will wait at this page until the Render is complete from API.
   */
  def poll = {
    def renderingJob = new RenderingJob()
    renderingJob.setUrl(params['jobUrl'])
    apiClient.reload(renderingJob)
    if (renderingJob.completed) {
      def video = renderingJob.video
      apiClient.reload(video)
      render(text: "{\"completed\":true, \"url\":\"" + request.contextPath + "/play?links[file]=" + URLEncoder.encode(video.links.get("file"))+ "\"}", contentType: "text/json")
    }
    else {
      render(text: "{\"completed\":false}", contentType:"text/json")
    }
  }
}
