package com.hp.backend.service.Booking;

import java.util.List;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;



public interface BookingListMenteeService {

    List<BookingListMenteeDTO> getAllBooking(int id) throws CustomNotFoundException;
    
    Booking findBookingByID(int id) throws CustomBadRequestException;
    // void addBooking(Booking booking);

    ViewBookingDTO findMenteeBookingDetailByID(int id) throws CustomBadRequestException;

    void deleteMenteeBookingByStatus(int id) throws CustomBadRequestException;

    
    // BookingDTO findEmailAndUsernameByBookingID(int id);
    // void deleteBookingByID(int id);

    // void saveBooking(Booking r);

}
