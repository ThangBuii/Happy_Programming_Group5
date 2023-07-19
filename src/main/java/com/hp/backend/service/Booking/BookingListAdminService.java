package com.hp.backend.service.Booking;

import java.util.List;

import com.hp.backend.model.booking.dto.BookingListAdminDTO;

public interface BookingListAdminService {

    List<BookingListAdminDTO> getAllAdminBooking();

}
