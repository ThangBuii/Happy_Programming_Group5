package com.hp.backend.service.Time.Impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Session;
import com.hp.backend.entity.Times;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.time.dto.AddTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeRequestDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseDTO;
import com.hp.backend.model.time.dto.GetListTimeResponseFindMentorDTO;
import com.hp.backend.model.time.mapper.TimeMapper;
import com.hp.backend.repository.SessionRepository;
import com.hp.backend.repository.TimeRepository;
import com.hp.backend.service.Time.TimeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {
    private final TimeRepository timeRepository;
    private final TimeMapper timeMapper;
    private final SessionRepository sessionRepository;

    @Override
    public List<GetListTimeResponseDTO> getAllTime(GetListTimeRequestDTO requestDTO) {
        List<GetListTimeResponseDTO> listTimeResponseDTOs = new ArrayList<>();
        List<Times> times = timeRepository.findStartTimeAndEndTime(requestDTO.getSession_id(),
                requestDTO.getStart_date());
        for (Times time : times) {
            listTimeResponseDTOs.add(timeMapper.toGetListTimeResponseDTO(time));
        }
        return listTimeResponseDTOs;
    }

    @Override
    public void addTime(int account_id, AddTimeRequestDTO addTimeRequestDTO) throws CustomBadRequestException {
        // Lấy thông tin từ AddTimeRequestDTO
        Date date = addTimeRequestDTO.getDate();

        // Kiểm tra ngày đặt lịch không được trước ngày hiện tại
        Date currentDate = new Date(System.currentTimeMillis());
        if (date.before(currentDate)) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Invalid date. Cannot schedule before the current date.").code("400")
                            .build());
        }

        // Chuyển đổi startTime và endTime sang kiểu Time
        LocalTime startTime = LocalTime.parse(addTimeRequestDTO.getStart_time());
        LocalTime endTime = LocalTime.parse(addTimeRequestDTO.getEnd_time());
        int hour_st = startTime.getHour();
        int minute_st = startTime.getMinute();
        int second_st = startTime.getSecond();
        Time startTimeObj = Time.valueOf(String.format("%02d:%02d:%02d", hour_st, minute_st, second_st));

        int hour_et = endTime.getHour();
        int minute_et = endTime.getMinute();
        int second_et = endTime.getSecond();
        Time endTimeObj = Time.valueOf(String.format("%02d:%02d:%02d", hour_et, minute_et, second_et));

        // Kiểm tra start_time và end_time có tồn tại trong cơ sở dữ liệu hay không
        boolean isStartTimeExists = timeRepository.existsByStartTime(startTimeObj, date);
        boolean isEndTimeExists = timeRepository.existsByEndTime(endTimeObj, date);
        if (isStartTimeExists || isEndTimeExists) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Time slot overlaps with an existing slot.").code("400").build());
        }

        // Tính toán thời gian duration từ Session
        Session session = sessionRepository.findById(addTimeRequestDTO.getSession_id()).get();
        if (session.getMentor_id() != account_id) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Bad request").code("400").build());
        }
        int duration = session.getDuration();

        // Tính toán thời gian duration từ startTime và endTime
        long timeDifference = endTimeObj.getTime() - startTimeObj.getTime();
        int calculatedDuration = (int) (timeDifference / (1000 * 60)); // Đổi từ milliseconds sang phút

        // Kiểm tra calculatedDuration và duration có khớp nhau
        if (calculatedDuration != duration) {
            throw new CustomBadRequestException(
                    CustomError.builder().message("Invalid time duration").code("400").build());
        }

        Times time = Times.builder()
                .start_time(startTimeObj)
                .end_time(endTimeObj)
                .session(session)
                .start_date(date)
                .build();
        timeRepository.save(time);
    }

    @Override
    public List<GetListTimeResponseFindMentorDTO> getListTimeResponseFindMentorDTOs(int session_id) {
        List<Date> dates = timeRepository.getListTimesBySession(session_id);
        List<GetListTimeResponseFindMentorDTO> timeList = new ArrayList<>();

        for(Date date : dates){
            List<GetListTimeResponseDTO> getListTimeResponseDTOs = getAllTime(
                GetListTimeRequestDTO.builder().session_id(session_id).start_date(date).build());
            timeList.add(timeMapper.toGetListTimeResponseFindMentorDTO(date,getListTimeResponseDTOs));
        }

        return timeList;
    }

    @Override
    public void deleteTimeByID(int timeID) throws CustomBadRequestException {
        Optional<Times> time = timeRepository.findById(timeID);

        if(!time.isPresent()){
            throw new CustomBadRequestException(
                    CustomError.builder().message("Bad request").code("400").build());
        }

        timeRepository.deleteById(timeID);
    }

}
