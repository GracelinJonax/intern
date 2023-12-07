package com.example.cart.Service.impl;


import com.example.cart.Exception.BadRequestException;
import com.example.cart.Service.CartService;
import org.modelmapper.ModelMapper;
import com.example.cart.dto.ProductCartDTO;
import com.example.cart.dto.ProductCartResponseDTO;
import com.example.cart.dto.User;
import com.example.cart.model.ProductCart;
import com.example.cart.model.ProductCartDetails;
import com.example.cart.repository.ProductCartDetailsRepository;
import com.example.cart.repository.ProductCartRepository;
import com.example.cart.repository.service.ProductCartDetailsRepoService;
import com.example.cart.repository.service.ProductCartRepoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductCartServiceImpl implements CartService {

    private final ProductCartDetailsRepository productCartDetailsRepository;

    private final ProductCartRepository productCartRepository;

    private final ProductCartDetailsRepoService productCartDetailsRepoService;

    private final ProductCartRepoService productCartRepoService;

    private final ModelMapper modelMapper;

    @Autowired
    private HttpServletResponse productResponsePage;

    public ProductCartServiceImpl(ProductCartDetailsRepository productCartDetailsRepository,
            ProductCartRepository productCartRepository, ProductCartDetailsRepoService productCartDetailsRepoService,
            ProductCartRepoService productCartRepoService, ModelMapper modelMapper) {
        this.productCartDetailsRepository = productCartDetailsRepository;
        this.productCartRepository = productCartRepository;
        this.productCartDetailsRepoService = productCartDetailsRepoService;
        this.productCartRepoService = productCartRepoService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addCartItem(ProductCartDTO productCartDTO){

        ProductCartDetails productCartEntity = new ProductCartDetails();
        ProductCart productCart = new ProductCart();
        User user = new User();
        user.setUserId("123");
        Optional<ProductCart> optionalProductCart = productCartRepoService.findByUserIdAndBillingTypeAndOrderIdNull(
                user.getUserId(), productCartDTO.getBillingType());
        if (!optionalProductCart.isPresent()) {
            productCart.setUserId(user.getUserId());
            productCart.setBillingType(productCartDTO.getBillingType());
            productCartRepository.save(productCart);
        } else {
            productCart = optionalProductCart.get();
        }
        Optional<ProductCartDetails> existProductCartDetails = productCartDetailsRepoService.findByProductCartUserIdAndProductIdAndPlanIdAndProductCart(
                user.getUserId(), productCartDTO.getProductId(), productCartDTO.getPlanId(), productCart);
        if (existProductCartDetails.isPresent()) {
            productCartEntity = existProductCartDetails.get();
            if (productCartDTO.getQuantity() == 0) {
                productCartDetailsRepository.delete(productCartEntity);
                return "PRODUCT_REMOVED_FROM_CART";
            }
        }
        if (productCartDTO.getQuantity() == 0) {
            throw new BadRequestException("QUANTITY_HAS_TO_BE_MORE_THAN_ZERO");
        }
        productCartEntity.setProductId(productCartDTO.getProductId());
        productCartEntity.setPlanId(productCartDTO.getPlanId());
        if (productCartDTO.getApplicationId() != null) {
            productCartEntity.setApplicationId(productCartDTO.getApplicationId());
        }
        if (productCartDTO.getSkuId() != null) {
            productCartEntity.setSkuId(productCartDTO.getSkuId());
        }
        productCartEntity.setQuantity(productCartDTO.getQuantity());
        productCartEntity.setProductCart(productCart);
        productCartDetailsRepository.save(productCartEntity);
        return "PRODUCT_ADDED_INTO_CART";

    }

    @Override
    public List<ProductCartResponseDTO> getCartItems() {

        User user = new User();
        user.setUserId("123");
        Optional<List<ProductCartDetails>> productCartEntity = productCartDetailsRepository.findByProductCartUserIdAndProductCartOrderIdNull(
                user.getUserId());
        if (!productCartEntity.isPresent()) {
            throw new BadRequestException("NO_ITEMS_PRESENT_IN_GIVEN_USER");
        }
        List<ProductCartDetails> productCartDetailsEntities = productCartEntity.get();
        Map<String, List<ProductCartDetails>> productCartDetailsMap = productCartDetailsEntities.stream()
                .collect(Collectors.groupingBy(a -> a.getProductCart().getBillingType()));
        List<ProductCartResponseDTO> responseDTOs = new ArrayList<>();
        for (Map.Entry<String, List<ProductCartDetails>> productCartDetailsEntity : productCartDetailsMap.entrySet()) {
            List<ProductCartResponseDTO.Item> items = mapToProductCartResponseDTO(productCartDetailsEntity.getValue());
            ProductCartResponseDTO productCartResponseDTO = new ProductCartResponseDTO();
            productCartResponseDTO.setCartId(
                    productCartDetailsEntity.getValue().get(0).getProductCart().getProductCartId());
            productCartResponseDTO.setBillingType(productCartDetailsEntity.getKey());
            productCartResponseDTO.setItems(items);
            responseDTOs.add(productCartResponseDTO);
        }
        productResponsePage.addIntHeader("total-entry-count", productCartDetailsEntities.size());
        productResponsePage.addHeader("Access-Control-Expose-Headers", "total-entry-count");
        return responseDTOs;

    }

    private List<ProductCartResponseDTO.Item> mapToProductCartResponseDTO(
            List<ProductCartDetails> productCartDetailsEntities) {

        return productCartDetailsEntities.stream().map(a -> modelMapper.map(a, ProductCartResponseDTO.Item.class))
                .collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<String> temp() {
        RestTemplate rt=new RestTemplate();
        String url="http://localhost:8080/allEmployee";
        ResponseEntity<String> rs=rt.getForEntity(url,String.class);
        return rs;
    }
}


