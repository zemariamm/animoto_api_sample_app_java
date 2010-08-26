package sundawgco

import com.animoto.api.ApiClient;
import com.animoto.api.RenderingManifest;
import com.animoto.api.RenderingProfile;
import com.animoto.api.enums.VerticalResolution;
import com.animoto.api.enums.Format;
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
   * Homepage.
   */
  def index = { 
    return [images : config.images]
  }

  /**
   * Create workflow signature and create widget in frame.
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
   * Video playback.
   */
  def play = {
    def videoUrl = params['links[video]']
    return [videoUrl: videoUrl]
  }

  /**
   * Render a storyboard from the widget.
   */
  def finalize = {
    def storyboardUrl = params['links[storyboard]']
    def renderingManifest = new RenderingManifest()
    def renderingProfile = new RenderingProfile()
    def storyboard = new Storyboard()
  
    /**
     * Remember this is Groovy which auto will eventually auto resolve Java setters/getters on the API Client. :)
     */ 
    storyboard.url = storyboardUrl

    renderingProfile.verticalResolution = VerticalResolution.VR_720P
    renderingProfile.framerate = 30
    renderingProfile.format = Format.H264

    renderingManifest.storyboard = storyboard
    renderingManifest.renderingProfile = renderingProfile

    def renderingJob = apiClient.render(renderingManifest)
    return [videoUrl: renderingJob.url]
  }

  /**
   * AJAX endpoint to determine if the video job is complete and return JSON.
   */
  def poll = {
    def renderingJob = new RenderingJob()
    renderingJob.setUrl(params['jobUrl'])
    apiClient.reload(renderingJob)
    if (renderingJob.completed) {
      def video = renderingJob.video
      apiClient.reload(video)
      render(text: "{\"completed\":true, \"url\":\"/sundawgco/play?links[video]=" + video.links.get("download")+ "\"}", contentType: "text/json")
    }
    else {
      render(text: "{\"completed\":false}", contentType:"text/json")
    }
  }
}
