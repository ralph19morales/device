package com.tech.device.infra.configurations;

import com.tech.device.domain.DomainBaseClass;
import com.tech.device.domain.annotations.DomainService;
import com.tech.device.domain.spis.IBookingProvider;
import com.tech.device.domain.spis.IDeviceProvider;
import com.tech.device.domain.spis.IPersonProvider;
import com.tech.device.infra.persistence.repositories.BookingDurationRepository;
import com.tech.device.infra.persistence.repositories.BookingRepository;
import com.tech.device.infra.persistence.repositories.DeviceRepository;
import com.tech.device.infra.persistence.repositories.PersonRepository;
import com.tech.device.infra.providers.BookingJPAProvider;
import com.tech.device.infra.providers.DeviceJPAProvider;
import com.tech.device.infra.providers.PersonJPAProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = DomainBaseClass.class,
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = DomainService.class)
)
public class DomainConfiguration {

    @Bean
    IBookingProvider instantiateBookingProvider(BookingRepository bookingRepository, DeviceRepository deviceRepository, PersonRepository personRepository, BookingDurationRepository bookingDurationRepository) {
        return new BookingJPAProvider(bookingRepository, deviceRepository, personRepository, bookingDurationRepository);
    }

    @Bean
    IDeviceProvider instantiateDeviceProvider(DeviceRepository deviceRepository) {
        return new DeviceJPAProvider(deviceRepository);
    }

    @Bean
    IPersonProvider instantiatePersonProvider(PersonRepository personRepository) {
        return new PersonJPAProvider(personRepository);
    }
}
