package com.hp.backend.service.Booking;

import java.util.List;

import com.hp.backend.entity.Booking;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.booking.dto.BookingListMenteeDTO;
import com.hp.backend.model.booking.dto.DashboardMenteeDTO;
import com.hp.backend.model.booking.dto.ViewBookingDTO;

public interface BookingListMenteeService {

    List<BookingListMenteeDTO> getAllMenteeBooking(int id) throws CustomNotFoundException;

    Booking findBookingByID(int id) throws CustomBadRequestException;

    ViewBookingDTO findMenteeBookingDetailByID(int id) throws CustomBadRequestException;

    void deleteMenteeBookingByStatus(int id) throws CustomBadRequestException;

    List<DashboardMenteeDTO> getDashboardMenteeBooking(int account_id) throws CustomNotFoundException;

}
