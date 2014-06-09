package com.odde.mail.controller;


import com.odde.mail.model.Recipient;
import com.odde.mail.model.Result;
import com.odde.mail.service.RecipientService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.lang.String.format;

@Controller
@RequestMapping("/recipient")
public class RecipientController {
    private static final Log log = LogFactory.getLog(RecipientController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RecipientService recipientService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    String add(@RequestParam("username") String username,
               @RequestParam("email") String email) throws Exception {
        log.debug("recipient controller add start");
        log.debug(format("username:%s", username));
        log.debug(format("email:%s", email));
        Result mailResult = recipientService.add(username, email);
        String result = mapper.writeValueAsString(mailResult);
        log.debug(format("result:%s", result));
        log.debug("recipient controller add finish");
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    String list() throws Exception {
        log.debug("recipient controller list start");
        List<Recipient> recipients = recipientService.list();
        String result = mapper.writeValueAsString(recipients);
        log.debug(format("recipients:%s", result));
        log.debug("recipient controller list finish");
        return result;
    }

}
