package com.example.kindergarden.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "groupChildren")
@EqualsAndHashCode(of = {"id", "paymentDate"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_child_id", nullable = false)
    @JsonIgnore
    private GroupChildren groupChildren;

    public Payment(Integer amount, GroupChildren groupChildren) {
        this.amount = amount;
        this.groupChildren = groupChildren;
        this.paymentDate = LocalDate.now();
    }

    public boolean isRecent() {
        return paymentDate != null && paymentDate.isAfter(LocalDate.now().minusDays(30));
    }
}
