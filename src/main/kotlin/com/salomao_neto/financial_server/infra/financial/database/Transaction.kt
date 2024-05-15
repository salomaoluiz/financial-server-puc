package com.salomao_neto.financial_server.infra.financial.database

import com.salomao_neto.financial_server.infra.user.database.User
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.UUID

@Entity
@Table(name = "financial-transaction")
class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @NotNull
    var description: String,

    @NotNull
    var date: String,

    @NotNull
    var value: String,

    @NotNull
    var category: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var user: User
)
