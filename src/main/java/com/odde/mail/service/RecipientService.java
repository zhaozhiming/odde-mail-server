package com.odde.mail.service;

import com.odde.mail.model.Recipient;
import com.odde.mail.model.Result;
import com.odde.mail.repo.RecipientRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {
    private static final Log log = LogFactory.getLog(RecipientService.class);

    @Autowired
    private RecipientRepository recipientRepository;

    public Result add(String username, String email) {
        log.debug("recipient service add start");
        recipientRepository.save(new Recipient(username, email));
        log.debug("recipient service add finish");
        return new Result("成功");
    }

    public List<Recipient> list() {
        log.debug("recipient service list start");
        List<Recipient> recipients = recipientRepository.findAll();
        log.debug("recipient service list finish");
        return recipients;
    }
}
