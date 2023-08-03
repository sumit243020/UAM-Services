package com.uamservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uamservice.entity.Mail;
import com.uamservice.projection.MailProjection;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

	Page<MailProjection> findBy(Pageable pageable);


}
