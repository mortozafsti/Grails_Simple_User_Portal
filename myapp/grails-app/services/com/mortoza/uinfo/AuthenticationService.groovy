package com.mortoza.uinfo

class AuthenticationService {

    private static final String AUTHORIZED = "AUTHORIZED"

    def setMemberAuthorization(Member member) {
        def authorization = [isLoggedIn: true, member: member]
        AppUtil.getAppSession()[AUTHORIZED] = authorization
    }


    boolean isAuthenticated() {
        def authorization = AppUtil.getAppSession()[AUTHORIZED]
        if (authorization && authorization.isLoggedIn) {
            return true
        }
        return false
    }


    def getMember() {
        def authorization = AppUtil.getAppSession()[AUTHORIZED]
        return authorization?.member
    }

    def getCurrentMemberId() {
        def member = getMember()
        return "${member.id}"
    }

    def getNormalMember() {
        def member = getMember()
        return member
    }

    def getMemberName() {
        def member = getMember()
        return "${member.firstName} ${member.lastName}"
    }


    def doLogin(String email, String password) {
        password = password.encodeAsMD5()
        Member member = Member.findByEmailAndPassword(email, password)
        if (member) {
            setMemberAuthorization(member)
            return true
        }
        return false
    }

    def checkPassword(String oldPassword, String newPassword, String confirmNewPassword) {
        oldPassword = oldPassword.encodeAsMD5()
        newPassword = confirmNewPassword.encodeAsMD5()
        confirmNewPassword = confirmNewPassword.encodeAsMD5()
        if(newPassword.equals(confirmNewPassword)){
            Member member = Member.findByPassword(oldPassword)
            if (member) {
                return true
            }
        }
        return false
    }

    def isAdministratorMember() {
        def member = getMember()
        if (member && member.memberType == GlobalConfig.USER_TYPE.ADMINISTRATOR) {
            return true
        }
        return false
    }

}
