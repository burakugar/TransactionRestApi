package com.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_generator")
    @SequenceGenerator(name = "transaction_id_generator", sequenceName = "transaction_sequence_id", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false)
    @Size(max = 255, message = "" +
            "can not be larger than 255")
    private Timestamp timestamp;

    @Column(name = "actor", nullable = false, updatable = false)
    @Size(max = 255, message = "" +
            "can not be larger than 255")
    private String actor;

    @Column(name = "type", nullable = false, updatable = false)
    @Size(max = 255, message = "" +
            "can not be larger than 255")
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "join_clmn", referencedColumnName = "id")
    private List<TransactionDataEntity> transactionDataEntityList;
}
