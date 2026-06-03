package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Account;
import com.example.demo.entities.Role;
import com.example.demo.entities.RoleAccount;

@Repository
public interface RoleAccountRepository extends JpaRepository<RoleAccount, Integer>{

	 @Query("""
		        select ra.account
		        from RoleAccount ra
		        where ra.role.nameRole = :roleName
		    """)
		    List<Account> findAccountsByRoleName(@Param("roleName") String roleName);
	
	
}
