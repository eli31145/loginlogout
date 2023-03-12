package com.assignment.LoginLogout.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository tells Spring this is interface to use for DB management fns
@Repository
//JpaRepository<T, Id> links interface to db table. Tell it to look at User table, and Id is long
public interface UserRepository extends JpaRepository<User, Long> {
}
