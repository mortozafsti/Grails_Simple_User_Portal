package com.mortoza.uinfo

/**
 * Created by HP on 9/7/2019.
 */
class AuthenticationController {

    AuthenticationService authenticationService
    MemberService memberService

    def login() {
        if (authenticationService.isAuthenticated()) {
            def response = memberService.getById()
            redirect(controller: "dashboard", action: "index")
        }
    }


    def doLogin() {
        if (authenticationService.doLogin(params.email, params.password)) {
            redirect(controller: "dashboard", action: "index")
        } else {
            redirect(controller: "authentication", action: "login")
        }
    }


    def logout() {
        session.invalidate()
        redirect(controller: "authentication", action: "login")
    }


    def changePassword(){
        if (authenticationService.isAuthenticated()) {
            def response = memberService.getById(authenticationService.getCurrentMemberId())
            [member: response]
        }
    }

    def doChangePassword(){
        def response = memberService.getById(params.id)
        if (!response){
            redirect(controller: "authentication", action: "changePassword")
        }else{
            if(authenticationService.checkPassword(params.oldPassword, params.password, params.confirmNewPassword)){
                response = memberService.update(response, params)
                if (!response.isSuccess){
                    flash.redirectParams = response.model
                    redirect(controller: "authentication", action: "changePassword")
                }else{
                    redirect(controller: "member", action: "index")
                }
            }else {
                redirect(controller: "authentication", action: "changePassword")
            }
        }
    }

    def registration() {
        [member: flash.redirectParams]
    }

    def doRegistration() {
        def response = memberService.save(params)
        if (response.isSuccess) {
            authenticationService.setMemberAuthorization(response.model)
            redirect(controller: "dashboard", action: "index")
        } else {
            flash.redirectParams = response.model
            redirect(controller: "authentication", action: "registration")
        }
    }
}
