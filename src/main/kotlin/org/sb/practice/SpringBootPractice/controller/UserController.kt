package org.sb.practice.SpringBootPractice.controller

import org.sb.practice.SpringBootPractice.entity.UserInfo
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class UserController {
    private val userMap: MutableMap<String, UserInfo> = mutableMapOf()

    @PostConstruct
    fun init() {
        userMap["1"] = UserInfo("1", "성현기", "010-1212-3434", "수원")
        userMap["2"] = UserInfo("2", "이경재", "010-5656-7878", "오산")
        userMap["3"] = UserInfo("3", "신동금", "010-9090-1212", "서울")
    }

    @GetMapping(path = ["/user/{id}"])
    fun getUserInfo(@PathVariable("id") id: String) = userMap[id]

    @GetMapping(path = ["user/all"])
    fun getUserInfoAll() = ArrayList<UserInfo>(userMap.values)

    @PutMapping(path = ["/user/{id}"])
    fun putUserInfo(@PathVariable("id") id: String, @RequestParam("name") name: String, @RequestParam("phone") phone: String, @RequestParam("address") address: String) {
        val userInfo = UserInfo(id, name, phone, address)
        userMap[id] = userInfo
    }

    @PostMapping(path = ["/user/{id}"])
    fun postUserInfo(@PathVariable("id") id: String, @RequestParam("name") name: String, @RequestParam("phone") phone: String, @RequestParam("address") address: String) {
        val userInfo = userMap[id]
        userInfo?.name = name
        userInfo?.phone = phone
        userInfo?.address = address
    }
}