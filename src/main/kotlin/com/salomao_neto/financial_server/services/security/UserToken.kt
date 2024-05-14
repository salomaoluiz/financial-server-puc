package com.salomao_neto.financial_server.services.security

import com.salomao_neto.financial_server.domain.user.UserEntity
import java.util.UUID

data class UserToken(
    val id: UUID,
    val name: String
) {
    constructor() : this(UUID.randomUUID(), "")
    constructor(user: UserEntity) : this(id = user.id!!, name = user.name!!);
}
