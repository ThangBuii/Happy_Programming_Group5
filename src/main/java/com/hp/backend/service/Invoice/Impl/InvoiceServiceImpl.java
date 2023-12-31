package com.hp.backend.service.Invoice.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hp.backend.entity.Receipt;
import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.CustomError;
import com.hp.backend.model.receipt.dto.InvoiceAdminDTO;
import com.hp.backend.model.receipt.dto.InvoiceDTO;
import com.hp.backend.model.receipt.dto.ViewInvoiceDTO;
import com.hp.backend.model.receipt.mapper.InvoiceMapper;
import com.hp.backend.repository.ReceiptRepository;
import com.hp.backend.service.Invoice.InvoiceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final ReceiptRepository receiptRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceDTO> getInvoiceMentor(int id) throws CustomNotFoundException {
        List<Receipt> receipts = receiptRepository.findReceiptsByMentorId(id);
        List<InvoiceDTO> invoiceMentor = new ArrayList<>();

        for (Receipt receipt : receipts) {

            InvoiceDTO invoiceDTO = invoiceMapper.toMentorInvoiceDTO(receipt);
            if (invoiceDTO.getUsername() == null || invoiceDTO.getEmail() == null) {
                CustomError error = new CustomError("DataNotFound", "Data for mentee not found", "");
                throw new CustomNotFoundException(error);
            }

            invoiceMentor.add(invoiceDTO);
        }

        return invoiceMentor;
    }

    @Override
    public List<InvoiceDTO> getInvoiceMentee(int id) throws CustomNotFoundException {
        List<Receipt> receipts = receiptRepository.findReceiptIdsByMenteeId(id);
        List<InvoiceDTO> invoiceMentee = new ArrayList<>();

        for (Receipt receipt : receipts) {

            InvoiceDTO invoiceDTO = invoiceMapper.toMenteeInvoiceDTO(receipt);
            if (invoiceDTO.getUsername() == null || invoiceDTO.getEmail() == null) {
                CustomError error = new CustomError("DataNotFound", "Data for mentor not found", "");
                throw new CustomNotFoundException(error);
            }

            invoiceMentee.add(invoiceDTO);
        }

        return invoiceMentee;

    }

    @Override
    public List<InvoiceAdminDTO> getInvoiceAdmin() {
        List<InvoiceAdminDTO> invoiceAdminDTO = new ArrayList<>();
        List<Receipt> receipts = receiptRepository.findAll();
        for (Receipt receipt : receipts) {
            InvoiceAdminDTO invoiceDTO = invoiceMapper.toAdminInvoiceDTO(receipt);
            invoiceAdminDTO.add(invoiceDTO);
        }
        return invoiceAdminDTO;
    }

    @Override
    public ViewInvoiceDTO findInvoiceById(int id) throws CustomBadRequestException {
        Optional<Receipt> invoice = receiptRepository.findById(id);
        if (!invoice.isPresent()) {
            throw new CustomBadRequestException(CustomError.builder()
                    .message("There are no invoice for the invoice id: " + id).code("404").build());
        }
        return invoiceMapper.toViewInvoiceDTO(invoice.get());
    }
}
