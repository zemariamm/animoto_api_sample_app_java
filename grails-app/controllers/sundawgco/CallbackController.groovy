package sundawgco

import com.animoto.api.resource.BaseResource
import com.animoto.api.util.CallbackUtil

import com.sundawgco.widget.CallbackManager;

class CallbackController {
  private static final List callbacks = callbacks;

  /**
   * Callback handler from Widget Service. We expect an HTTP POST with JSON.
   */
  def create = { 
    def transactionToken = params['transactionToken']    
    def body = request.reader.text

    CallbackManager.addCallback(transactionToken, body);

    /**
     * Let's test the Callback utility's ability to deserialize callback JSON into resource objects.
     */
    BaseResource resource = (BaseResource) CallbackUtil.generateFromJson(body)
    resource.prettyPrintToSystem()
    response.send(status: 200, message: "ok")
  }

  /**
   * List all callbacks received.
   */
  def list = { 
    return [callbacks: CallbackManager.getCallbacks()] 
  }
}
