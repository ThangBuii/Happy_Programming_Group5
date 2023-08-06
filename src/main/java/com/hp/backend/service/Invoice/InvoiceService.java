package com.hp.backend.service.Invoice;

import java.util.List;

import com.hp.backend.exception.custom.CustomBadRequestException;
import com.hp.backend.exception.custom.CustomNotFoundException;
import com.hp.backend.model.receipt.dto.InvoiceAdminDTO;
import com.hp.backend.model.receipt.dto.InvoiceDTO;
import com.hp.backend.model.receipt.dto.ViewInvoiceDTO;



public interface InvoiceService {

    List<InvoiceDTO> getInvoiceMentor(int receipt_id) throws CustomNotFoundException;

    List<InvoiceDTO> getInvoiceMentee(int receipt_id) throws CustomNotFoundException;

    List<InvoiceAdminDTO> getInvoiceAdmin();

    ViewInvoiceDTO findInvoiceById(int id) throws CustomBadRequestException;

    
    
    
}
