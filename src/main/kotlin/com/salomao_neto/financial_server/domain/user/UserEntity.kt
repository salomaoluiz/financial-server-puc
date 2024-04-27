package com.salomao_neto.financial_server.domain.user

import java.util.*

data class UserEntity(
    var id: UUID? = null,
    var email: String? = "",
    var name: String? = ""
) {}
