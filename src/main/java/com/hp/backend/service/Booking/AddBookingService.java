package com.hp.backend.service.Booking;

import com.hp.backend.model.booking.dto.AddBookingRequestDTO;

public interface AddBookingService {

    void addBookingForMentee(AddBookingRequestDTO addBooking, int account_id);

}
