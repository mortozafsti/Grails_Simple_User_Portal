package com.mortoza.uinfo

import grails.web.servlet.mvc.GrailsParameterMap

import java.util.concurrent.TimeUnit

class MemberService {

    def save(GrailsParameterMap params) {
        long current = System.currentTimeMillis()//
        Date birthDate = params.birthDate
        long difference = current - birthDate.getTime()
        long years = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)/365;

        Member member = new Member(params)
        member.age = years

        def response = AppUtil.saveResponse(false, member)
        if (member.validate()) {
            member.save(flush: true)
            if (!member.hasErrors()) {
                response.isSuccess = true
            }
        }
        return response
    }


    def update(Member member, GrailsParameterMap params) {
        member.properties = params
        def response = AppUtil.saveResponse(false, member)
        if (member.validate()) {
            member.save(flush: true)
            if (!member.hasErrors()) {
                response.isSuccess = true
            }
        }
        return response
    }


    def getById(Serializable id) {
        return Member.get(id)
    }


    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<Member> memberList = Member.createCriteria().list(params) {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
        }
        return [list: memberList, count: Member.count()]
    }
}
