package com.dayscool.ms_user.repository


import com.dayscool.ms_user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByMail(mail: String?): Optional<User>
    fun findByUsername(username: String?): Optional<User>
    fun findByRole(role: String?): Optional<List<User>>
}