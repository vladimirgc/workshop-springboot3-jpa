package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.OrderService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
    private OrderService orderService; 
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		Category cat4 = new Category(null, "Gamer");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "", "2093035968245");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "", null);
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "", null);
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "", null);
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "", null);
		Product p6 = new Product(null, "Age of Empires", "Game for PC.", 135.99, "http://localhost:8080/uploads/products/28bfb729-2e6d-414e-a823-8708eba3495e-Age_of_Empires_II.jpg", "5488194547672");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		p6.getCategories().add(cat4);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "21988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "21977777777", "123456");
		User u3 = new User(null, "João Silva", "joao@gmail.com", "21966666666", "123456");
		User u4 = new User(null, "Ana Souza", "ana@gmail.com", "21955555555", "123456");
		User u5 = new User(null, "Carlos Mendes", "carlos@gmail.com", "21944444444", "123456");
		User u6 = new User(null, "Fernanda Lima", "fernanda@gmail.com", "21933333333", "123456");
		User u7 = new User(null, "Bruno Costa", "bruno@gmail.com", "21922222222", "123456");
		User u8 = new User(null, "Juliana Alves", "juliana@gmail.com", "21911111111", "123456");
		User u9 = new User(null, "Rafael Martins", "rafael@gmail.com", "21900000000", "123456");
		User u10 = new User(null, "Patricia Rocha", "patricia@gmail.com", "21899999999", "123456");
		User u11 = new User(null, "Lucas Pereira", "lucas@gmail.com", "21888888888", "123456");
		User u12 = new User(null, "Camila Fernandes", "camila@gmail.com", "21877777777", "123456");
	
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		o1.setNumero(orderService.gerarNumeroPedido());

		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITINGS_PAYMENT, u2);
		o2.setNumero(orderService.gerarNumeroPedido());

		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITINGS_PAYMENT, u1);
		o3.setNumero(orderService.gerarNumeroPedido());

		Order o4 = new Order(null, Instant.parse("2019-08-01T10:15:30Z"), OrderStatus.PAID, u3);
		o4.setNumero(orderService.gerarNumeroPedido());

		Order o5 = new Order(null, Instant.parse("2019-08-03T14:22:11Z"), OrderStatus.SHIPPED, u4);
		o5.setNumero(orderService.gerarNumeroPedido());

		Order o6 = new Order(null, Instant.parse("2019-08-05T09:05:45Z"), OrderStatus.DELIVERED, u5);
		o6.setNumero(orderService.gerarNumeroPedido());

		Order o7 = new Order(null, Instant.parse("2019-08-10T18:33:50Z"), OrderStatus.CANCELED, u6);
		o7.setNumero(orderService.gerarNumeroPedido());

		Order o8 = new Order(null, Instant.parse("2019-08-12T21:17:05Z"), OrderStatus.PAID, u7);
		o8.setNumero(orderService.gerarNumeroPedido());

		Order o9 = new Order(null, Instant.parse("2019-08-15T07:44:19Z"), OrderStatus.WAITINGS_PAYMENT, u8);
		o9.setNumero(orderService.gerarNumeroPedido());

		Order o10 = new Order(null, Instant.parse("2018-08-18T16:29:37Z"), OrderStatus.SHIPPED, u9);
		o10.setNumero(orderService.gerarNumeroPedido());

		Order o11 = new Order(null, Instant.parse("2018-08-20T11:11:11Z"), OrderStatus.DELIVERED, u10);
		o11.setNumero(orderService.gerarNumeroPedido());

		Order o12 = new Order(null, Instant.parse("2017-08-22T13:55:42Z"), OrderStatus.CANCELED, u11);
		o12.setNumero(orderService.gerarNumeroPedido());

		Order o13 = new Order(null, Instant.parse("2017-08-25T19:40:00Z"), OrderStatus.PAID, u12);
		o13.setNumero(orderService.gerarNumeroPedido());

		
		userRepository.saveAll(Arrays.asList(
		        u1, u2, u3, u4, u5, u6,
		        u7, u8, u9, u10, u11, u12
		));

		orderRepository.saveAll(Arrays.asList(
		        o1, o2, o3, o4, o5, o6,
		        o7, o8, o9, o10, o11, o12, o13
		));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		OrderItem oi5 = new OrderItem(o4, p2, 1, p2.getPrice());
		OrderItem oi6 = new OrderItem(o4, p4, 3, p4.getPrice());

		OrderItem oi7 = new OrderItem(o5, p1, 2, p1.getPrice());
		OrderItem oi8 = new OrderItem(o5, p5, 1, p5.getPrice());
		OrderItem oi9 = new OrderItem(o6, p3, 4, p3.getPrice());
		OrderItem oi10 = new OrderItem(o7, p2, 2, p2.getPrice());
		OrderItem oi11 = new OrderItem(o7, p4, 1, p4.getPrice());
		OrderItem oi12 = new OrderItem(o8, p5, 3, p5.getPrice());
		OrderItem oi13 = new OrderItem(o9, p1, 1, p1.getPrice());
		OrderItem oi14 = new OrderItem(o9, p3, 2, p3.getPrice());
		OrderItem oi15 = new OrderItem(o10, p4, 2, p4.getPrice());
		OrderItem oi16 = new OrderItem(o11, p2, 5, p2.getPrice());
		OrderItem oi17 = new OrderItem(o12, p5, 1, p5.getPrice());
		OrderItem oi18 = new OrderItem(o12, p1, 2, p1.getPrice());
		OrderItem oi19 = new OrderItem(o13, p3, 3, p3.getPrice());

		
		orderItemRepository.saveAll(Arrays.asList(
		        oi1, oi2, oi3, oi4,
		        oi5, oi6, oi7, oi8,
		        oi9, oi10, oi11, oi12,
		        oi13, oi14, oi15, oi16,
		        oi17, oi18, oi19
		));
	
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		Payment pay2 = new Payment(null, Instant.parse("2019-08-01T12:20:30Z"), o4);
		o4.setPayment(pay2);

		Payment pay3 = new Payment(null, Instant.parse("2019-08-12T23:30:00Z"), o8);
		o8.setPayment(pay3);

		
		orderRepository.saveAll(Arrays.asList(o1, o4, o8));

		
	}
	
	
}
