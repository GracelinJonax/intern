package com.example.userorderservice.service.impl;

import com.example.userorderservice.Dto.LoginDto;
import com.example.userorderservice.Dto.OrderBillDto;
import com.example.userorderservice.Dto.OrderDto;
import com.example.userorderservice.feign.ProxyFeign;
import com.example.userorderservice.model.*;
import com.example.userorderservice.repository.*;
import com.example.userorderservice.repository.service.TenantDetailsRepositoryService;
import com.example.userorderservice.repository.service.UserDetailsRepositoryService;
import com.example.userorderservice.service.UserOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserOrderServiceImpl implements UserOrderService {
    private final UserDetailsRepository userDetailsRepository;
    private final TenantDetailsRepository tenantDetailsRepository;
    private final TenantDetailsRepositoryService tenantDetailsRepositoryService;
    private final UserDetailsRepositoryService userDetailsRepositoryService;
    private final ProductDetailsRepository productDetailsRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderedProductDetailsRepository orderedProductDetailsRepository;
    private final ProxyFeign ProxyFeign;
    private final ModelMapper modelMapper;
//    com.example.seatingArrangement.Dto.TeamDto teamDto;
//    private com.example.seatingArrangement.Dto.TeamDto teamDto1;

    public UserOrderServiceImpl(UserDetailsRepository userDetailsRepository, TenantDetailsRepository tenantDetailsRepository, TenantDetailsRepositoryService tenantDetailsRepositoryService, UserDetailsRepositoryService userDetailsRepositoryService,
                                ProductDetailsRepository productDetailsRepository, OrderDetailsRepository orderDetailsRepository,
                                OrderedProductDetailsRepository orderedProductDetailsRepository,
                                ProxyFeign ProxyFeign, ModelMapper modelMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.tenantDetailsRepository = tenantDetailsRepository;
        this.tenantDetailsRepositoryService = tenantDetailsRepositoryService;
        this.userDetailsRepositoryService = userDetailsRepositoryService;
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
        else if (userDetails.getPassword().isEmpty()||userDetails.getPassword().length()<8)
            return "user password is invalid";
        userDetailsRepository.save(userDetails);
//        teamDto1 = teamDto;

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
        if (userDetails.isEmpty())
            throw new RuntimeException("user not present");
        orderDetails.setUserDetails(userDetails.get());
        bill.setUserId(orderDto.getUserId());
        bill.setUserEmail(userDetails.get().getEmail());
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
            if (productDetails.isEmpty())
                throw new RuntimeException("product not present");
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

    @Override
    public boolean isTenantValid(String tenantId) {
        Optional<TenantDetails> tenant=tenantDetailsRepository.findById(tenantId);
        if (tenant.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public String loginTenantService(LoginDto loginDto) {
        TenantDetails tenant=tenantDetailsRepositoryService.findByEmail(loginDto.getEmail());
        if(tenant!=null&&tenant.getPassword().equals(loginDto.getPassword()))
            return tenant.getTenantId();
        throw new RuntimeException("tenant invalid");
    }

    @Override
    public String loginUserService(LoginDto loginDto) {
        UserDetails userDetails=userDetailsRepositoryService.findByEmail(loginDto.getEmail());
        if(userDetails!=null&&userDetails.getPassword().equals(loginDto.getPassword()))
            return "login successful";
        throw new RuntimeException("login unsuccessful");
    }

    @Override
    public String saveTenantService(TenantDetails tenantDetails) {
        if(tenantDetails==null)
            return "tenant is null";
        else if (!tenantDetails.getEmail().contains("@"))
            return "tenant email is invalid";
        else if (tenantDetails.getPassword().isEmpty()||tenantDetails.getPassword().length()<8)
            return "tenant password is invalid";
        tenantDetailsRepository.save(tenantDetails);
        return "success";
    }
}
