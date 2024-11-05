package com.raonsecure.sample.entity;

import com.raonsecure.sample.entity.base.UpdatedAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;

import static com.raonsecure.sample.entity.constant.DomainTypeSize.TEXT;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "notice")
public class Notice extends UpdatedAuditingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1836621107908266870L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "content", nullable = false, columnDefinition = TEXT)
    private String content;

    @NotNull
    @Builder.Default
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    public static Notice create(
            String title,
            String content

    ) {
        return Notice.builder()
                .title(title)
                .content(content)
                .build();
    }

    public void update(
            String title,
            String content
    ) {
        if (StringUtils.hasText(title)) this.title = title;
        if (StringUtils.hasText(content)) this.content = content;
    }

    public void delete() {
        this.deleted = true;
    }
}
