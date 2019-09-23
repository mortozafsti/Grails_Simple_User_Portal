package myapp

import com.mortoza.uinfo.AppInitializationService

class BootStrap {

    AppInitializationService appInitializationService

    def init = { servletContext ->
        appInitializationService.initialize()
    }
    def destroy = {
    }
}
