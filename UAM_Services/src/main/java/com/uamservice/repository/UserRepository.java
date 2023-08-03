package com.uamservice.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uamservice.entity.User;
import com.uamservice.projection.UserProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Page<UserProjection> findBy(Pageable pageable);

	Optional<User> findByUserIdAndIsDeleted(Long userId, boolean b);

	Page<UserProjection> findByNameLikeIgnoreCaseAndIsDeleted(String string, boolean b, Pageable pageable);

	Optional<User> findByUserName(String userName);

	Optional<User> findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User user SET user.isDeleted = true WHERE user.userId = :userId")
	void softDelete(Long userId);
	
	User findByEmailIgnoreCase(String emailId);

    Boolean existsByEmail(String email);

}
