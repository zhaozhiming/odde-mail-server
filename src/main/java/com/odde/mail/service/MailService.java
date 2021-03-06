package com.odde.mail.service;

import com.odde.mail.model.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static java.lang.String.format;

@Service
public class MailService {
    private static final Log log = LogFactory.getLog(MailService.class);

    @Value("${send_mail}")
    private String sendMail;

    @Value("${mail_user}")
    private String mailUser;

    @Value("${mail_password}")
    private String mailPassword;

    @Value("${smtp_host}")
    private String smtpHost;

    @Value("${smtp_port}")
    private String smtpPort;

    @Value("${smtp_auth}")
    private String smtpAuth;

    public Result send(String recipients, String subject, String content) {
        log.debug("mail service send start");

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", smtpAuth);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, mailPassword);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sendMail));
            msg.setRecipients(Message.RecipientType.TO, transformStringsToAddresses(recipients));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
            return new Result("成功");
        } catch (MessagingException mex) {
            log.debug(format("error:%s", mex));
            return new Result("失败");
        } finally {
            log.debug("mail service send finish");
        }
    }

    private Address[] transformStringsToAddresses(String recipients) throws AddressException {
        String[] multipleRecipients = recipients.split(",");
        Address[] addresses = new Address[multipleRecipients.length];
        for (int i = 0; i < multipleRecipients.length; i++) {
            addresses[i] = new InternetAddress(multipleRecipients[i]);
        }
        return addresses;
    }
}
