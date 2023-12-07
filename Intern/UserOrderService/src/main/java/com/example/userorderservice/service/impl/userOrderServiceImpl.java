package com.example.userorderservice.service.impl;

import com.example.userorderservice.Dto.orderBillDto;
import com.example.userorderservice.Dto.orderDto;
import com.example.userorderservice.feign.ProxyFeign;
import com.example.userorderservice.model.OrderDetails;
import com.example.userorderservice.model.OrderedProductDetails;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.userDetails;
import com.example.userorderservice.repository.OrderDetailsRepository;
import com.example.userorderservice.repository.OrderedProductDetailsRepository;
import com.example.userorderservice.repository.productDetailsRepository;
import com.example.userorderservice.repository.service.OrderDetailsRepositoryService;
import com.example.userorderservice.repository.service.OrderedProductDetailsRepositoryService;
import com.example.userorderservice.repository.service.productDetailsRepositoryService;
import com.example.userorderservice.repository.service.userDetailsRepositoryService;
import com.example.userorderservice.repository.userDetailsRepository;
import com.example.userorderservice.service.userOrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class userOrderServiceImpl implements userOrderService {
    private final userDetailsRepository userDetailsRepository;
    private final productDetailsRepository productDetailsRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderedProductDetailsRepository orderedProductDetailsRepository;
    private final userDetailsRepositoryService userDetailsRepositoryService;
    private final productDetailsRepositoryService productDetailsRepositoryService;
    private final OrderDetailsRepositoryService orderDetailsRepositoryService;
    private final OrderedProductDetailsRepositoryService orderedProductDetailsRepositoryService;
    private final ProxyFeign ProxyFeign;
    private final ModelMapper modelMapper;
    @Autowired
    private HttpServletResponse productResponsePage;

    public userOrderServiceImpl(userDetailsRepository userDetailsRepository,
            productDetailsRepository productDetailsRepository, OrderDetailsRepository orderDetailsRepository,
            OrderedProductDetailsRepository orderedProductDetailsRepository,
            userDetailsRepositoryService userDetailsRepositoryService,
            productDetailsRepositoryService productDetailsRepositoryService,
            OrderDetailsRepositoryService orderDetailsRepositoryService,
            OrderedProductDetailsRepositoryService orderedProductDetailsRepositoryService, ProxyFeign ProxyFeign,
            ModelMapper modelMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderedProductDetailsRepository = orderedProductDetailsRepository;
        this.userDetailsRepositoryService = userDetailsRepositoryService;
        this.productDetailsRepositoryService = productDetailsRepositoryService;
        this.orderDetailsRepositoryService = orderDetailsRepositoryService;
        this.orderedProductDetailsRepositoryService = orderedProductDetailsRepositoryService;
        this.modelMapper = modelMapper;
        this.ProxyFeign = ProxyFeign;
    }

    @Override
    public String saveUserService(userDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "success";
    }

    @Override
    public String saveProductService(ProductDetails productDetails) {
        productDetailsRepository.save(productDetails);
        return "success";
    }

    @Override
    public String orderProductService(orderDto orderDto) {
        orderBillDto bill = new orderBillDto();
        OrderDetails orderDetails = new OrderDetails();
        Optional<userDetails> userDetails = userDetailsRepository.findById(orderDto.getUserId());
        orderDetails.setUserDetails(userDetails.get());
        OrderDetails orderDetails1 = orderDetailsRepository.save(orderDetails);
        bill.setUserId(userDetails.get().getUserId());
        bill.setUserEmail(userDetails.get().getEmail());
        bill.setOrderId(orderDetails1.getOrderId());
        List<orderDto.Product> product = orderDto.getProductDetailsList();
        List<orderBillDto.Product> billProductList = new ArrayList<>();
        Double totalPrice = 0.0;
        for (orderDto.Product productOrdered : product) {
            orderBillDto.Product billProduct = new orderBillDto.Product();
            OrderedProductDetails orderedProductDetails = new OrderedProductDetails();
            modelMapper.map(productOrdered, orderedProductDetails);
            orderedProductDetails.setOrderDetails(orderDetails1);
            Optional<ProductDetails> productDetails = productDetailsRepository.findById(productOrdered.getProductId());
            ProductDetails productDetail = productDetails.get();
            modelMapper.map(productDetail, billProduct);
            billProduct.setQuantity(productOrdered.getQuantity());
            orderedProductDetails.setProductDetails(productDetail);
            orderedProductDetailsRepository.save(orderedProductDetails);
            totalPrice += ((productDetail.getPrice() + ((productDetail.getPrice() * productDetail.getGst()) / 100)) * productOrdered.getQuantity());
            billProductList.add(billProduct);
        }
        orderDetails1.setTotalPrice(totalPrice);
        bill.setTotalPrice(totalPrice);
        bill.setProducts(billProductList);
        String billId = ProxyFeign.saveBill(bill).getBody();
        orderDetails1.setBillId(billId);
        String id = orderDetails1.getOrderId();
        orderDetailsRepository.save(orderDetails1);

        return id;
    }

    //    @Override
    //    public void sendEmailService(orderBillDto bill) {
    //        String message="<head>"+"</head>"+"<body>"+"<table border =1>"+"<tr>"+"<th>Product Name</th>"+"<th>Quantity</th>"+"<th>Gst</th>"+"<th>Price</th>";
    //        for(orderBillDto.Product product:bill.getProducts()){
    //            ProductDetails productDetails=productDetailsRepository.findById(product.getProductId()).get();
    //            message+="<tr>"+"<td>"+productDetails.getName()+"</td>"+"<td>"+product.getQuantity()+"</td>"+"<td>"+product.getGst()+"</td>"+"<td>"+product.getPrice()+"</td>"+"</tr>";
    //        }
    //        message+="</table>"+"Total Price       "+bill.getTotalPrice()+"</body>"+"</html>";
    //        String email=userDetailsRepository.findById(bill.getUserId()).get().getEmail();
    //        emailService.sendEmail(email,"Your Order is Confirmed",message);
    //    }
}
