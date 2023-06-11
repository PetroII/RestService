package com.empik.restservice.controller;

import com.empik.restservice.model.User;
import com.empik.restservice.service.RestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestServiceControllerTest {

    @MockBean
    RestService restService;

    @Autowired
    RestServiceController controller;

    @Test
    void getUserByLogin() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(restService.getUserFromApi(any())).thenReturn(new User());
        ResponseEntity<?> responseEntity = controller.getUserByLogin("test");

        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}