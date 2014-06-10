package com.odde.mail.controller;

import com.odde.mail.model.Result;
import com.odde.mail.service.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class MailControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MailService mailService;

    @InjectMocks
    MailController mailController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mailController).build();
    }

    @Test
    public void should_return_status_success_when_send_mail_success() throws Exception {
        verifyBySendResult("成功");
    }

    @Test
    public void should_return_status_failed_when_send_mail_failed() throws Exception {
        verifyBySendResult("失败");
    }

    private void verifyBySendResult(String result) throws Exception {
        when(mailService.send("test@test.com", "test", "test")).thenReturn(new Result(result));

        mockMvc.perform(post("/mail/send")
                .param("recipients", "test@test.com")
                .param("subject", "test")
                .param("content", "test"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(is("{\"status\":\"" + result + "\"}")));

        verify(mailService).send("test@test.com", "test", "test");
    }
}