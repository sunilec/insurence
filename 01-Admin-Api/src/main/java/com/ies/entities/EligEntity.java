package com.ies.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ELIG_DTLS")
public class EligEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer edgTraceId;

    private String planStatus;

    private Double benefitAmt;
}
