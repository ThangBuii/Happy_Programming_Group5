package com.hp.backend.service.Invoice.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hp.backend.entity.Receipt;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.receipt.dto.InvoiceAdminDTO;
import com.hp.backend.model.receipt.dto.InvoiceDTO;
import com.hp.backend.model.receipt.mapper.InvoiceMapper;
import com.hp.backend.repository.AccountRepository;
import com.hp.backend.repository.BookingRepository;
import com.hp.backend.repository.ReceiptRepository;

class InvoiceServiceImplTest {
    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInvoiceAdmin() {
        Receipt receipt = new Receipt();
        List<Receipt> receipts = new ArrayList<>();
        receipts.add(receipt);
        InvoiceAdminDTO invoiceAdminDTO = new InvoiceAdminDTO();
        List<InvoiceAdminDTO> expectedInvoiceList = new ArrayList<>();
        expectedInvoiceList.add(invoiceAdminDTO);

        when(receiptRepository.findAll()).thenReturn(receipts);
        when(invoiceMapper.toAdminInvoiceDTO(receipt)).thenReturn(invoiceAdminDTO);

        List<InvoiceAdminDTO> actualInvoiceList = invoiceService.getInvoiceAdmin();

        assertEquals(expectedInvoiceList, actualInvoiceList);
        verify(receiptRepository, times(1)).findAll();
        verify(invoiceMapper, times(1)).toAdminInvoiceDTO(receipt);
    }

    @Test
    void testGetInvoiceMentee() throws CustomNotFoundException {
        int menteeId = 1;
        Receipt receipt = new Receipt();
        List<Receipt> receipts = new ArrayList<>();
        receipts.add(receipt);
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setUsername("John Doe"); // Set the username
        invoiceDTO.setEmail("john.doe@example.com"); // Set the email
        List<InvoiceDTO> expectedInvoiceList = new ArrayList<>();
        expectedInvoiceList.add(invoiceDTO);

        when(receiptRepository.findReceiptIdsByMenteeId(menteeId)).thenReturn(receipts);
        when(invoiceMapper.toMenteeInvoiceDTO(receipt)).thenReturn(invoiceDTO);

        List<InvoiceDTO> actualInvoiceList = invoiceService.getInvoiceMentee(menteeId);

        assertEquals(expectedInvoiceList, actualInvoiceList);
        verify(receiptRepository, times(1)).findReceiptIdsByMenteeId(menteeId);
        verify(invoiceMapper, times(1)).toMenteeInvoiceDTO(receipt);
    }

    @Test
    void testGetInvoiceMentor() throws CustomNotFoundException {
        int mentorId = 1;
        Receipt receipt = new Receipt();
        List<Receipt> receipts = new ArrayList<>();
        receipts.add(receipt);
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setUsername("John Doe"); // Set the username
        invoiceDTO.setEmail("john.doe@example.com"); // Set the email
        List<InvoiceDTO> expectedInvoiceList = new ArrayList<>();
        expectedInvoiceList.add(invoiceDTO);

        when(receiptRepository.findReceiptsByMentorId(mentorId)).thenReturn(receipts);
        when(invoiceMapper.toMentorInvoiceDTO(receipt)).thenReturn(invoiceDTO);

        List<InvoiceDTO> actualInvoiceList = invoiceService.getInvoiceMentor(mentorId);

        assertEquals(expectedInvoiceList, actualInvoiceList);
        verify(receiptRepository, times(1)).findReceiptsByMentorId(mentorId);
        verify(invoiceMapper, times(1)).toMentorInvoiceDTO(receipt);
    }
}
