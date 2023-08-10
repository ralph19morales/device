package com.tech.device.infra.providers;

import com.tech.device.domain.models.Booking;
import com.tech.device.domain.models.BookingDuration;
import com.tech.device.domain.spis.IBookingProvider;
import com.tech.device.infra.persistence.entities.BookingDurationEntity;
import com.tech.device.infra.persistence.entities.BookingEntity;
import com.tech.device.infra.persistence.repositories.BookingDurationRepository;
import com.tech.device.infra.persistence.repositories.BookingRepository;
import com.tech.device.infra.persistence.repositories.DeviceRepository;
import com.tech.device.infra.persistence.repositories.PersonRepository;

import java.time.ZonedDateTime;
import java.util.Collections;

public class BookingJPAProvider implements IBookingProvider {

    final BookingRepository bookingRepository;
    final DeviceRepository deviceRepository;
    final PersonRepository personRepository;
    final BookingDurationRepository bookingDurationRepository;

    public BookingJPAProvider(BookingRepository bookingRepository, DeviceRepository deviceRepository, PersonRepository personRepository, BookingDurationRepository bookingDurationRepository) {
        this.bookingRepository = bookingRepository;
        this.deviceRepository = deviceRepository;
        this.personRepository = personRepository;
        this.bookingDurationRepository = bookingDurationRepository;
    }

    @Override
    public Booking addBooking(Long personId, Long deviceId, ZonedDateTime start, ZonedDateTime end) {
        BookingEntity booking = BookingEntity.builder()
                .person(personRepository.findById(personId).get())
                .device(deviceRepository.findById(deviceId).get())
                .build();
        booking.setDuration(Collections.singletonList(
                BookingDurationEntity.builder()
                        .start(start)
                        .end(end)
                        .booking(booking)
                        .build()));
        return bookingRepository.save(
                booking
        ).toDomainBooking();
    }

    @Override
    public Booking addDuration(Long bookingId, ZonedDateTime start, ZonedDateTime end) {
        BookingEntity booking = bookingRepository.findById(bookingId).get();

        BookingDurationEntity durationEntity = bookingDurationRepository.save(
                BookingDurationEntity.builder()
                        .booking(booking)
                        .start(start)
                        .end(end)
                        .build()
        );

        booking.getDuration().add(durationEntity);
        return booking.toDomainBooking();
    }

    @Override
    public Booking endDuration(Long bookingDurationId, ZonedDateTime end) {
        BookingDurationEntity duration = bookingDurationRepository.findById(bookingDurationId).get();
        duration.setEnd(end);
        bookingDurationRepository.save(duration);
        return duration.getBooking().toDomainBooking();
    }

    @Override
    public BookingDuration getLatestDuration(Long bookingId) {
        System.out.println(bookingId);
        return bookingDurationRepository.findTopByBookingIdOrderByIdDesc(bookingId).get().toDomainBookingDuration();
    }
}
