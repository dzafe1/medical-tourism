package com.tracking.panel.repository;

import com.tracking.panel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByEmail(String user);
    User findOneById(Long id);
    @Query("SELECT u FROM User u where is_active=true and role='ADMIN'")
    List<User> getAllAdmins();
    @Query("SELECT u FROM User u where u.role <> 'ADMIN'")
    List<User> getAllNonAdmins();
    @Query(nativeQuery = true,value = "SELECT count(id) FROM user WHERE is_active=true AND role!='ADMIN'")
    Long getAllNonAdminsCount();
    @Query(nativeQuery = true,value = "SELECT count(id) FROM user WHERE is_active=true")
    Long getAllUsers();
}
