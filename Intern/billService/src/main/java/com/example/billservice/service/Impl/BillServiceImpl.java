package com.example.billservice.service.Impl;

import com.example.billservice.Dto.OrderBillDto;
import com.example.billservice.model.Bill;
import com.example.billservice.model.OrderedProduct;
import com.example.billservice.repository.BillRepository;
import com.example.billservice.repository.OrderedProductRepository;
import com.example.billservice.service.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final ModelMapper modelMapper;

    public BillServiceImpl(BillRepository billRepository, OrderedProductRepository orderedProductRepository,
                            ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveBillService(OrderBillDto orderBillDto) {
        List<OrderedProduct> products = orderBillDto.getProducts().stream()
                .map(a -> modelMapper.map(a, OrderedProduct.class)).collect(Collectors.toList());
        List<OrderedProduct> productList = orderedProductRepository.saveAll(products);
        Bill bill = new Bill();
        bill.setOrderId(orderBillDto.getOrderId());
        bill.setUserId(orderBillDto.getUserId());
        bill.setProducts(productList);
        bill.setTotalPrice(orderBillDto.getTotalPrice());
        billRepository.save(bill);
//        System.out.println(bill.getId() + "  id");
    }

}
