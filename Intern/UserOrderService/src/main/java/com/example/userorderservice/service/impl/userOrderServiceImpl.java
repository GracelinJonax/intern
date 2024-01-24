package com.example.userorderservice.service.impl;

import com.example.userorderservice.Dto.OrderBillDto;
import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.feign.ProxyFeign;
import com.example.userorderservice.model.OrderDetails;
import com.example.userorderservice.model.OrderedProductDetails;
import com.example.userorderservice.model.ProductDetails;
import com.example.userorderservice.model.UserDetails;
import com.example.userorderservice.repository.OrderDetailsRepository;
import com.example.userorderservice.repository.OrderedProductDetailsRepository;
import com.example.userorderservice.repository.ProductDetailsRepository;
import com.example.userorderservice.repository.UserDetailsRepository;
import com.example.userorderservice.service.userOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class userOrderServiceImpl implements userOrderService {
    private final UserDetailsRepository userDetailsRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderedProductDetailsRepository orderedProductDetailsRepository;
    private final ProxyFeign ProxyFeign;
    private final ModelMapper modelMapper;

    public userOrderServiceImpl(UserDetailsRepository userDetailsRepository,
                                ProductDetailsRepository productDetailsRepository, OrderDetailsRepository orderDetailsRepository,
                                OrderedProductDetailsRepository orderedProductDetailsRepository,
                                ProxyFeign ProxyFeign,ModelMapper modelMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderedProductDetailsRepository = orderedProductDetailsRepository;
        this.modelMapper = modelMapper;
        this.ProxyFeign = ProxyFeign;
    }

    @Override
    public String saveUserService(UserDetails userDetails) {
        if(userDetails==null)
            return "user is null";
        else if (userDetails.getName().length()>50)
            return "username is too long";
        else if (!userDetails.getEmail().contains("@"))
            return "user email is invalid";
        userDetailsRepository.save(userDetails);
        return "success";
    }

    @Override
    public String saveProductService(ProductDetails productDetails) {
        if (productDetails==null)
            throw new RuntimeException("product is null");
        else if (productDetails.getName().length()>50)
            throw new RuntimeException("product details is too long");
        else if (productDetails.getPrice()==null||productDetails.getGst()==null)
            throw new RuntimeException("product details has null values");
        productDetailsRepository.save(productDetails);
        return "success";
    }

    @Override
    public String orderProductService(OrderDto orderDto) {
        if(orderDto==null)
            throw new RuntimeException("no order is placed");
        OrderBillDto bill = new OrderBillDto();
        OrderDetails orderDetails = new OrderDetails();
        Optional<UserDetails> userDetails = userDetailsRepository.findById(orderDto.getUserId());
        orderDetails.setUserDetails(userDetails.get());
//        OrderDetails orderDetails1 = orderDetailsRepository.save(orderDetails);
        bill.setUserId(userDetails.get().getUserId());
        bill.setUserEmail(userDetails.get().getEmail());
        //
        bill.setOrderId(orderDetails.getOrderId());
        List<OrderBillDto.Product> billProductList = new ArrayList<>();
        List<OrderedProductDetails> orderedProductDetailsList=new ArrayList<>();
        double totalPrice = 0.0;
        for (OrderDto.Product productOrdered : orderDto.getProductDetailsList()) {
            OrderBillDto.Product billProduct = new OrderBillDto.Product();
            OrderedProductDetails orderedProductDetails = new OrderedProductDetails();
            modelMapper.map(productOrdered, orderedProductDetails);
            orderedProductDetails.setOrderDetails(orderDetails);
            Optional<ProductDetails> productDetails = productDetailsRepository.findById(productOrdered.getProductId());
            ProductDetails productDetail = productDetails.get();
            modelMapper.map(productDetail, billProduct);
            billProduct.setQuantity(productOrdered.getQuantity());
            orderedProductDetails.setProductDetails(productDetail);
            orderedProductDetailsList.add(orderedProductDetails);
            double price=((productDetail.getPrice() + ((productDetail.getPrice() * productDetail.getGst()) / 100)) * productOrdered.getQuantity());
            billProduct.setTotalPrice(price);
            totalPrice+=price;
            billProductList.add(billProduct);
        }
        orderDetails.setTotalPrice(totalPrice);
        bill.setTotalPrice(totalPrice);
        bill.setProducts(billProductList);
        ProxyFeign.saveBill(bill);

        orderDetailsRepository.save(orderDetails);
        String id = orderDetails.getOrderId();
        orderedProductDetailsRepository.saveAll(orderedProductDetailsList);
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
