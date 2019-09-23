package com.mortoza.uinfo

/**
 * Created by HP on 9/7/2019.
 */
class DashboardController {

    MemberService memberService
    AuthenticationService authenticationService

    def index() {
        Member member = authenticationService.getNormalMember()
        def response = memberService.getById(member.id)
        [member: response]
    }


}
