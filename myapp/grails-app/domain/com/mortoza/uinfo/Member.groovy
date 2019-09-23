package com.mortoza.uinfo

/**
 * Created by HP on 9/7/2019.
 */
class Member {

    Integer id
    String firstName
    String lastName
    String address
    String phone
    Date birthDate
    Long age
    String email
    String password
    String memberType = GlobalConfig.USER_TYPE.REGULAR_MEMBER
    Boolean isActive = true

    static constraints = {
        email(nullable: false, unique: true, blank: false)
        password(blank: false)
        lastName(nullable: true)
        address(nullable: true)
        phone(nullable: true)
        birthDate(nullable: true)
        age(nullable: true)
    }

    static mapping = {
        table("user")
        version(false)
    }

    def beforeInsert (){
        this.password = this.password.encodeAsMD5()
    }


    def beforeUpdate(){
        this.password = this.password.encodeAsMD5()
    }

}
