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
        Recipient recipient = recipientRepository.findByEmail(email);
        Result result;
        if (recipient == null) {
            recipientRepository.save(new Recipient(username, email));
            result = new Result("成功");
        } else {
            result = new Result("失败");
        }

        log.debug("recipient service add finish");
        return result;
    }

    public List<Recipient> list() {
        log.debug("recipient service list start");
        List<Recipient> recipients = recipientRepository.findAll();
        log.debug("recipient service list finish");
        return recipients;
    }
}
