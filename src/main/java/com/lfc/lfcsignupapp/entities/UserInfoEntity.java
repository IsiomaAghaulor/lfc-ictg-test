package com.lfc.lfcsignupapp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "LFC_USER_TABLE", uniqueConstraints={
        @UniqueConstraint(name ="UniqueEmailAndMerchantId", columnNames = {"EMAIL"})
})
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserInfoEntity {
    @Id
    @GeneratedValue(generator = "LFC_USER_TABLE_SEQUENCE")
    @SequenceGenerator(name = "LFC_USER_TABLE_SEQUENCE", sequenceName = "LFC_USER_TABLE_SEQUENCE",allocationSize = 1)

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE_NUM")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
