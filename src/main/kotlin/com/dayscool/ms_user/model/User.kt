package com.dayscool.ms_user.model
import javax.persistence.*
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Entity
data class User (
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        val id: Long = 0,
        @get: NotBlank @Column(unique = true)
        val username: String = "",
        @get: NotBlank @Column(unique = true)
        val mail: String = "",
        @get: NotNull
        val birthDate: LocalDate = LocalDate.now(),
        @get: NotBlank
        val career: String = "",
        @get: NotBlank
        val role: String = "",
        @get: NotBlank
        val name: String = "",
        @get: NotBlank
        val password: String = "")