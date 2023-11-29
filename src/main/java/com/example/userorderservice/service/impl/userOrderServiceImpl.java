package com.example.userorderservice.service.impl;

import com.example.userorderservice.Dto.orderBillDto;
import com.example.userorderservice.Dto.orderDto;
import com.example.userorderservice.feign.billFeign;
//import com.example.userorderservice.feign.billFeignService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
    private final billFeign billFeign;
    private final ModelMapper modelMapper;
    @Autowired
    private HttpServletResponse productResponsePage;

    public userOrderServiceImpl(userDetailsRepository userDetailsRepository,
            productDetailsRepository productDetailsRepository, OrderDetailsRepository orderDetailsRepository,
            OrderedProductDetailsRepository orderedProductDetailsRepository,
            userDetailsRepositoryService userDetailsRepositoryService,
            productDetailsRepositoryService productDetailsRepositoryService,
            OrderDetailsRepositoryService orderDetailsRepositoryService,
            OrderedProductDetailsRepositoryService orderedProductDetailsRepositoryService, billFeign billFeign,
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
        this.billFeign = billFeign;
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
        //        if (userDetails.isEmpty()) {
        //            return "error";
        //        }
        orderDetails.setUserDetails(userDetails.get());
        OrderDetails orderDetails1 = orderDetailsRepository.save(orderDetails);
        bill.setUserId(userDetails.get().getUserId());
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
            //            if(productDetails.isEmpty())
            //                return "error";
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
        String id = orderDetailsRepository.save(orderDetails1).getOrderId();
        billFeign.saveBill(bill);
        //        System.out.println(id+"   345678");
        //        System.out.println(ans+"    ans");
        return id;
    }
}
