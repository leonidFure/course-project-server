package com.lgorev.courseworkserver.api

import com.lgorev.courseworkserver.domain.UserModel
import com.lgorev.courseworkserver.services.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {
    @GetMapping
    fun getAllUsers() = ResponseEntity.ok(userService.findAll())

    @PostMapping
    fun register(@RequestBody userModel: UserModel) = ResponseEntity.ok(userService.save(userModel))

    @PutMapping
    fun update(@RequestBody userModel: UserModel) = ResponseEntity.ok(userService.update(userModel))

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = ResponseEntity.ok(userService.delete(id))

    @GetMapping("user")
    fun getUser(@RequestParam("id") id: Long) = ResponseEntity.ok(userService.findOne(id))

    @GetMapping("page")
    fun getPage(@RequestParam("page") page: Int, @RequestParam("size") size: Int)
            = ResponseEntity.ok(userService.findPage(PageRequest.of(page, size)))

}