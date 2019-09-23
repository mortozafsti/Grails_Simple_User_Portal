package com.mortoza.uinfo

/**
 * Created by HP on 9/7/2019.
 */
class GlobalConfig {

    public static final def USER_TYPE = [
            ADMINISTRATOR: "ADMINISTRATOR",
            REGULAR_MEMBER: "REGULAR_MEMBER",
    ]


    public static Integer itemsPerPage() {
        return 5
    }

}
