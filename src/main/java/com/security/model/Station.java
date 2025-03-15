package com.security.model;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "station", schema = "training")  // 🔥 Adiciona o schema corretamente
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial",  nullable = false)
    private String serial;

    @Column(name = "onLineStatus")
    private Boolean onLineStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = true)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estation_model_id", nullable = true)
    private EstationModel estationModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "installationDate")
    private LocalDateTime installationDate;

    @Column(name = "observations")
    private String observations;

    @Column(name = "simCardIccid", length = 30)
    private String simCardIccid;

    @Column(name = "simCardState")
    private Boolean simCardState;
    
    
}
