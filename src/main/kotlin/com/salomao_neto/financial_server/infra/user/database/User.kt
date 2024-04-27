package com.salomao_neto.financial_server.infra.user.database

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @NotNull
    var name: String,

    @Email
    @Column(unique = true, nullable = false)
    var email: String,

    @NotNull
    var password: String
) {}
