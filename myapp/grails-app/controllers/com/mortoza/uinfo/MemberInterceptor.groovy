package com.mortoza.uinfo

/**
 * Created by HP on 9/7/2019.
 */
class MemberInterceptor {

    AuthenticationService authenticationService

    boolean before() {
        if (authenticationService.isAdministratorMember()){
            return true
        }
        redirect(controller: "dashboard", action: "index")
        return false
    }
}
