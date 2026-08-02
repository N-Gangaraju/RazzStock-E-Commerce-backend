package com.example.demo.services;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Order;
import com.example.demo.repos.OrderRepo;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class InvoiceService {

	@Autowired
	private OrderRepo orderRepo;
	
	public byte[] generateInvoice(Integer orderId) throws Exception {

	    Order order = orderRepo.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order Not Found"));

	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    Document document = new Document();

	    PdfWriter.getInstance(document, out);

	    document.open();

	    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

	    Paragraph title = new Paragraph("RAZZSTOCK", titleFont);

	    title.setAlignment(Paragraph.ALIGN_CENTER);

	    document.add(title);

	    document.add(new Paragraph(" "));

	    document.add(new Paragraph("Invoice No : INV-" + order.getId()));

	    document.add(new Paragraph("Date : " + order.getOrderedAt()));

	    document.add(new Paragraph("Customer : " + order.getUser().getUsername()));

	    document.add(new Paragraph(" "));

	    document.add(new Paragraph("------------------------------------------"));

	    document.add(new Paragraph("Product : " + order.getProduct().getName()));

	    document.add(new Paragraph("Quantity : " + order.getQuantity()));

	    document.add(new Paragraph("Price : ₹" + order.getPrice()));

	    document.add(new Paragraph("Amount : ₹" + order.getAmount()));

	    document.add(new Paragraph("Status : " + order.getStatus()));

	    document.add(new Paragraph("----------------------------------------"));

	    document.add(new Paragraph("Thank You For Shopping With Razzstock"));

	    document.close();

	    return out.toByteArray();
	}
}
