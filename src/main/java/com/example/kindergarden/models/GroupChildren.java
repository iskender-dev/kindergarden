package com.example.kindergarden.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "group_child")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"child", "group", "payments"})
@EqualsAndHashCode(of = {"id", "startDate"})
public class GroupChildren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "child_id")
    @JsonIgnore
    private Child child;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;

    @OneToMany(mappedBy = "groupChildren", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Payment> payments;

    public GroupChildren(LocalDate startDate, Integer price, Child child, Group group) {
        this.startDate = startDate;
        this.price = price;
        this.child = child;
        this.group = group;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setGroupChildren(this);
    }

    public void removePayment(Payment payment) {
        payments.remove(payment);
        payment.setGroupChildren(null);
    }
}
