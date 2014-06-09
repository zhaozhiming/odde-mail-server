package com.odde.mail.service;

import com.odde.mail.model.Recipient;
import com.odde.mail.model.Result;
import com.odde.mail.repo.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;


    public Result add(String username, String email) {
        recipientRepository.save(new Recipient(username, email));
        return new Result("成功");
    }
}
