package com.shaadi.assignment.utils

import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import com.shaadi.assignment.data.remote.response.*
import org.junit.Test


class UtilsTest {


    @Test
    fun integrationMapperTest() {

        val resultList = mutableListOf<Result>().apply {
            this.add(
                Result(
                    "",
                    Dob(30, ""),
                    "TEST_EMAIL",
                    "MALE",
                    Id("", ""),
                    Location(
                        "TEST_CITY", Coordinates("", ""), "TEST_COUNTRY", "", "TEST_STATE",
                        Street("", 0), Timezone("", "")
                    ),
                    Login("", "", "", "", "", "", ""),
                    Name("FIRST_NAME", "LAST_NAME", "TITLE"),
                    "",
                    "",
                    Picture("TEST_PICTURE", "", ""),
                    Registered(1, "")
                )
            )
        }


        val list: List<User> = Utils.convertList(resultList)

        assert(list[0].name == "FIRST_NAME L")
        assert(list[0].age == 30)
        assert(list[0].imgUrl == "TEST_PICTURE")
        assert(list[0].user_status == UserStatus.PENDING)

    }

}