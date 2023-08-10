package com.tech.device.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.device.domain.apis.ICreateBooking;
import com.tech.device.domain.apis.IEndBooking;
import com.tech.device.domain.apis.IExtendBooking;
import com.tech.device.domain.mocks.MockBookingProvider;
import com.tech.device.domain.models.BookDeviceReq;
import com.tech.device.infra.controller.BookingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @MockBean
    ICreateBooking createBooking;

    @MockBean
    IEndBooking endBooking;

    @MockBean
    IExtendBooking extendBooking;

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenCreateBooking_whenValidRequest_thenReturnJsonArray()
            throws Exception {

        BookDeviceReq request = new BookDeviceReq();
        request.setPersonId(1L);
        request.setDeviceId(1L);

        given(createBooking.execute(request)).willReturn(MockBookingProvider.generateValidBooking());

        mvc.perform(post("/booking/create").content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.person.id", is(MockBookingProvider.generateValidBooking().getPerson().getId())))
                .andExpect(jsonPath("$.person.firstname", is(MockBookingProvider.generateValidBooking().getPerson().getFirstname())))
                .andExpect(jsonPath("$.device.id", is(MockBookingProvider.generateValidBooking().getDevice().getId())))
                .andExpect(jsonPath("$.id", is(MockBookingProvider.generateValidBooking().getId())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
