package com.dayscool.ms_user.controller

import com.dayscool.ms_user.model.User
import com.dayscool.ms_user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("/ms_user/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("")
    fun getAllUsers(): List<User> =
            userRepository.findAll()

    //Funcionalidad no disponible en este microservicio
    @PostMapping("/testcreate")
    fun createNewUser(@Valid @RequestBody user: User): User =
            userRepository.save(user)


    @GetMapping("/{idUser}")
    fun getUserById(@PathVariable(value = "idUser") idUser: Long): ResponseEntity<User> {
        return userRepository.findById(idUser).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/getUserByMail")
    fun getUserByMail(@Valid @RequestBody mail: Map<String,String>): ResponseEntity<User> {
        return userRepository.findByMail(mail["mail"]).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/getUserByUsername")
    fun getUserByUsername(@Valid @RequestBody username: Map<String,String>): ResponseEntity<User> {
        return userRepository.findByUsername(username["username"]).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{idUser}")
    fun updateUserById(@PathVariable(value = "idUser") idUser: Long,
                          @Valid @RequestBody newUser: User): ResponseEntity<User> {

        return userRepository.findById(idUser).map { existingUser ->
            val updatedUser: User = existingUser
                    .copy(username = newUser.username, mail = newUser.mail, id = existingUser.id, birthDate = existingUser.birthDate, career = newUser.career, role = newUser.role, name = newUser.name, password = newUser.password)
            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/{idUser}")
    fun deleteUserById(@PathVariable(value = "idUser") idUser: Long): ResponseEntity<Void> {

        return userRepository.findById(idUser).map { user  ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}