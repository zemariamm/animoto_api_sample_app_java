class UrlMappings {

	static mappings = {
    "/play"(controller:"home", action:"play")

    "/widget"(controller:"home", action:"widget")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"home", action:"index")
		"500"(view:'/error')
	}
}
