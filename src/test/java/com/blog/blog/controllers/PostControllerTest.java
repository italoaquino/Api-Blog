package com.blog.blog.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostControllerTest {

    @Autowired
    public WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testaRequisicaoGuidSucesso() throws Exception {
        String url = "/v1/posts/facb2ab4-f46a-44b5-abd5-c70b851d97da";
        this.mvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    public void testaRequisicaoGuidFail() throws Exception {
        String url = "/v1/posts/2312313dadasda";
        this.mvc.perform(get(url)).andExpect(status().isNotFound());
    }

}

