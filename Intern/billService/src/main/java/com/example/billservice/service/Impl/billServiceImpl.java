package com.example.billservice.service.Impl;

import com.example.billservice.Dto.orderBillDto;
import com.example.billservice.model.Bill;
import com.example.billservice.model.OrderedProduct;
import com.example.billservice.repository.BillRepository;
import com.example.billservice.repository.OrderedProductRepository;
import com.example.billservice.repository.Service.BillRepositoryService;
import com.example.billservice.repository.Service.OrderedProductRepositoryService;
import com.example.billservice.service.billService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class billServiceImpl implements billService {
    private final BillRepository billRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final BillRepositoryService billRepositoryService;
    private final OrderedProductRepositoryService orderedProductRepositoryService;
    private final ModelMapper modelMapper;
    //    Executor executor;

    public billServiceImpl(BillRepository billRepository, OrderedProductRepository orderedProductRepository,
            BillRepositoryService billRepositoryService,
            OrderedProductRepositoryService orderedProductRepositoryService, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.billRepositoryService = billRepositoryService;
        this.orderedProductRepositoryService = orderedProductRepositoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveBillService(orderBillDto orderBillDto) {
        List<OrderedProduct> products = orderBillDto.getProducts().stream()
                .map(a -> modelMapper.map(a, OrderedProduct.class)).collect(Collectors.toList());
        List<OrderedProduct> productList = orderedProductRepository.saveAll(products);
        Bill bill = new Bill();
        bill.setOrderId(orderBillDto.getOrderId());
        bill.setUserId(orderBillDto.getUserId());
        bill.setProducts(productList);
        bill.setTotalPrice(orderBillDto.getTotalPrice());
        billRepository.save(bill);
        System.out.println(bill.getId() + "  id");
        return bill.getId();
    }

    public void aggregate(){

    }
}
