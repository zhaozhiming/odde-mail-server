package com.odde.mail.repo;

import com.odde.mail.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    public Recipient findByEmail(String email);
}
