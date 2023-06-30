package com.hp.backend.service.Booking;

import java.util.List;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.booking.dto.BookingListMentorDTO;
import com.hp.backend.model.booking.dto.DashboardMentorDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;



public interface BookingListMentorService {

    List<BookingListMentorDTO> getAllMentorBooking(int id) throws CustomNotFoundException;

    ViewBookingDTO findBookingDetailByID(int id) throws CustomBadRequestException;

    Booking findBookingByID(int id) throws CustomBadRequestException;

    void saveMentorBooking(Booking b);

    List<DashboardMentorDTO> getDashboardMentorBooking(int id) throws CustomNotFoundException;
        
    

    
   
}
