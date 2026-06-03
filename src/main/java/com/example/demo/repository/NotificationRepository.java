package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Notification;
import com.example.demo.enums.ReceiverType;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	List<Notification> findByAccount_IdOrderByCreatedAtDesc(Integer accountId);

	List<Notification> findByAccount_IdAndIsReadFalseOrderByCreatedAtDesc(Integer accountId);

	long countByAccount_IdAndIsReadFalse(Integer accountId);

	@Query("""
			    SELECT n
			    FROM Notification n
			    WHERE
			        (n.account.id = :accountId)
			        OR (n.receiverType = 'ALL')
			        OR (n.receiverType = :role)
			    ORDER BY n.createdAt DESC
			""")
	List<Notification> findForAccount(@Param("accountId") Integer accountId, @Param("role") ReceiverType role);

}
