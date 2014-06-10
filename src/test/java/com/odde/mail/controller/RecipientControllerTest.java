package com.odde.mail.controller;

import com.odde.mail.model.Recipient;
import com.odde.mail.model.Result;
import com.odde.mail.service.RecipientService;
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

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class RecipientControllerTest {
    private MockMvc mockMvc;

    @Mock
    private RecipientService recipientService;

    @InjectMocks
    private RecipientController recipientController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipientController).build();
    }

    @Test
    public void should_return_status_success_when_add_recipient_success() throws Exception {
        verifyAddRecipientByResult("成功");
    }

    @Test
    public void should_return_status_failed_when_add_recipient_failed() throws Exception {
        verifyAddRecipientByResult("失败");
    }

    @Test
    public void should_return_recipients_when_list_success() throws Exception {
        when(recipientService.list()).thenReturn(asList(new Recipient("Tom", "test@test.com")));

        mockMvc.perform(get("/recipient/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"username\":\"Tom\",\"email\":\"test@test.com\"")));

        verify(recipientService).list();
    }

    private void verifyAddRecipientByResult(String result) throws Exception {
        when(recipientService.add("Tom", "test@test.com")).thenReturn(new Result(result));

        mockMvc.perform(post("/recipient/add")
                .param("username", "Tom")
                .param("email", "test@test.com"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(is("{\"status\":\"" + result + "\"}")));

        verify(recipientService).add("Tom", "test@test.com");
    }


}