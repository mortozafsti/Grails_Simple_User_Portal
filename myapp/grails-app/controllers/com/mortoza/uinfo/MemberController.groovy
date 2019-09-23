package com.mortoza.uinfo

/**
 * Created by HP on 9/7/2019.
 */
class MemberController {

    MemberService memberService

    def index() {
        def response = memberService.list(params)
        [memberList: response.list, total:response.count]
    }

    def details(Integer id) {
        def response = memberService.getById(id)
        if (!response){
            redirect(controller: "member", action: "index")
        }else{
            [member: response]
        }
    }

    def create() {
        [member: flash.redirectParams]
    }

    def save() {
        Member member = Member.findByEmail(params.email)
        if(member){
            flash.message = AppUtil.infoMessage("Duplicate Email", false)
        }else {
            def response = memberService.save(params)
            if (!response.isSuccess) {
                flash.redirectParams = response.model
                flash.message = AppUtil.infoMessage("Duplicate Email", false)
                redirect(controller: "member", action: "create")
            }else{
                redirect(controller: "member", action: "index")
            }
        }
    }


    def edit(Integer id) {
        if (flash.redirectParams) {
            [member: flash.redirectParams]
        } else {
            def response = memberService.getById(id)
            if (!response) {
                redirect(controller: "member", action: "index")
            } else {
                [member: response]
            }
        }
    }


    def update() {
        def response = memberService.getById(params.id)
        if (!response){
            redirect(controller: "member", action: "index")
        }else{
            response = memberService.update(response, params)
            if (!response.isSuccess){
                flash.redirectParams = response.model
                redirect(controller: "member", action: "edit")
            }else{
                redirect(controller: "member", action: "index")
            }
        }
    }


}
