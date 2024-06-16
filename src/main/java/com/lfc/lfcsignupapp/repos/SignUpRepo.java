package com.lfc.lfcsignupapp.repos;

import com.lfc.lfcsignupapp.entities.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SignUpRepo extends JpaRepository<UserInfoEntity,Long> {

 @Query(value = "select * from LFC_USER_TABLE where EMAIL = ?1", nativeQuery = true)
 Optional<UserInfoEntity> findUserByEmail(String email);
}
