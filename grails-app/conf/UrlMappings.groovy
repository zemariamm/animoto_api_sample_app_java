class UrlMappings {

	static mappings = {
    "/play" (controller:"home", action:"play")

    "/finalize" (controller:"home", action:"finalize")

    "/widget" (controller:"home", action:"widget")

    "/poll" (controller:"home", action:"poll")

		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}

    "/callback" (parseRequest:false) {
      controller = "callback"
      action = [POST:"create", GET:"list"]
    }

		"/" (controller:"home", action:"index")

		"500" (view:'/error')
	}
}
