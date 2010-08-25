package sundawgco

class PlayController {
  def index = { 
    def videoUrl = params['links[video]']
    return [videoUrl: videoUrl]
  }
}
