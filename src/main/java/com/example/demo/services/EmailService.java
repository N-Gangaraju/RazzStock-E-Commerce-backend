package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.Entites.Order;
import com.example.demo.Entites.User;
import com.example.demo.Enum.OrderStatus;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	

	@Value("${spring.mail.username}")
	private String fromEmail;
	
	public void sendEmail(String to,String subject,String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rajunagam624@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
	public void sendWelcomeEmail(String username,String email)
	{
		String subject = "Welcome to RazzStock 🎉";
		
		String body = """
		 Welcome to RazzStock!

         Your account has been created successfully.

         You can now:
         • Browse products
         • Add products to cart
         • Place orders
         • Download Invoices

         Thank you for choosing RazzStock.

         Regards,
         RazzStock Team
         """.formatted(username);

		sendEmail(email, subject, body);
	}
	
	public void sendOrderConfirmation(User user, Order order) {

	    String subject = "🎉 Your RazzStock Order has been Placed Successfully";

	    String body =
	            "Hello " + user.getUsername() + ",\n\n" +

	            "Thank you for shopping with RazzStock!\n\n" +

	            "Your order has been placed successfully.\n\n" +

	            "===============================\n" +
	            "ORDER DETAILS\n" +
	            "===============================\n" +
	            "Order ID   : " + order.getId() + "\n" +
	            "Product    : " + order.getProduct().getName() + "\n" +
	            "Quantity   : " + order.getQuantity() + "\n" +
	            "Price      : ₹" + order.getPrice() + "\n" +
	            "Total      : ₹" + order.getAmount() + "\n" +
	            "Status     : " + order.getStatus() + "\n\n" +

	            "We are preparing your order.\n" +
	            "You'll receive another email once it has been shipped.\n\n" +

	            "Thank you for choosing RazzStock.\n\n" +

	            "Best Regards,\n" +
	            "RazzStock Team";

	    sendEmail(user.getEmail(), subject, body);
	}
	public void sendOrderStatusUpdate(User user, Order order) {

	    String subject = "📦 RazzStock Order Status Updated";

	    String body =
	            "Hello " + user.getUsername() + ",\n\n" +

	            "Your RazzStock order status has been updated.\n\n" +

	            "================================\n" +
	            "ORDER DETAILS\n" +
	            "================================\n" +

	            "Order ID : " + order.getId() + "\n" +
	            "Product  : " + order.getProduct().getName() + "\n" +
	            "Quantity : " + order.getQuantity() + "\n" +
	            "Amount   : ₹" + order.getAmount() + "\n" +
	            "Status   : " + order.getStatus() + "\n\n" +

	            getStatusMessage(order.getStatus()) +

	            "\n\nThank you for choosing RazzStock.\n\n" +

	            "Best Regards,\n" +
	            "RazzStock Team";


	    sendEmail(user.getEmail(), subject, body);
	}
	private String getStatusMessage(OrderStatus status) {

	    switch(status) {

	        case SHIPPED:
	            return "Your order has been shipped 🚚\nIt will reach you soon.";

	        case DELIVERED:
	            return "Your order has been delivered successfully 🎉\nEnjoy your purchase.";

	        case CANCELLED:
	            return "Your order has been cancelled.";

	        default:
	            return "Your order is being processed.";
	    }
	}
	
	
	
	
}
