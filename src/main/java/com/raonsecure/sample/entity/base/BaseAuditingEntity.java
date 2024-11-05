package com.raonsecure.sample.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

import static com.raonsecure.sample.entity.constant.DomainTypeSize.LOGIN_ID;


@SuperBuilder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditingEntity {
    @NotNull
    @CreatedDate
    @Builder.Default
    @Column(name = "created_dt", nullable = false, updatable = false)
    private Instant createdDt = Instant.now();

    @Size(max = LOGIN_ID)
    @NotNull
    @CreatedBy
    @Column(name = "created_by", length = LOGIN_ID, updatable = false)
    private String createdBy;
}
