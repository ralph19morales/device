package com.tech.device.infra.controller;

import com.tech.device.domain.apis.ICreateBooking;
import com.tech.device.domain.apis.IEndBooking;
import com.tech.device.domain.apis.IExtendBooking;
import com.tech.device.domain.models.BookDeviceReq;
import com.tech.device.domain.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("booking")
public class BookingController {
    final ICreateBooking createBooking;
    final IEndBooking endBooking;
    final IExtendBooking extendBooking;

    @Autowired
    public BookingController(ICreateBooking createBooking, IEndBooking endBooking, IExtendBooking extendBooking) {
        this.createBooking = createBooking;
        this.endBooking = endBooking;
        this.extendBooking = extendBooking;
    }

    @PostMapping("create")
    public Booking create(@RequestBody BookDeviceReq bookDeviceReq) {
        return createBooking.execute(bookDeviceReq);
    }

    @PostMapping("{id}/end")
    public Booking end(@PathVariable(value = "id") Long id) {
        return endBooking.execute(id);
    }

    @PostMapping("{id}/extend")
    public Booking extend(@PathVariable(value = "id") Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end) {
        return extendBooking.execute(id, end);
    }
}
