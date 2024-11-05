package com.raonsecure.sample.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

import static com.raonsecure.sample.entity.constant.DomainTypeSize.LOGIN_ID;

@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class UpdatedAuditingEntity extends BaseAuditingEntity {
    @LastModifiedDate
    @Column(name = "updated_dt")
    private Instant updatedDt;

    @Size(max = LOGIN_ID)
    @LastModifiedBy
    @Column(name = "updated_by", length = LOGIN_ID)
    private String updatedBy;
}
