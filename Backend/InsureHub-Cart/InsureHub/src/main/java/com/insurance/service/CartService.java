package com.insurance.service;



import java.util.ArrayList;
import java.util.List;

import com.insurance.entity.CartRequest;
import com.insurance.model.Policy;
import com.insurance.contracts.ICartService;
import com.insurance.model.CartRequestPrice;
import com.insurance.model.CartResponse;
import com.insurance.repository.CartRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class CartService implements ICartService {


    @Autowired
    @Qualifier("webclient")
    private WebClient.Builder builder;
    @Autowired
    private final CartRequestRepo crepo;

    @Autowired
    public CartService(CartRequestRepo crepo) {
        this.crepo = crepo;
    }

    public String fallBackMethod() {
        return "Stay Tuned! Back Shortly";
    }

    @Override
    public String addToCart(Long policyId, CartRequestPrice req, Long userId) {
        crepo.save(CartRequest.builder().policyId(policyId).price(req.getPrice()).userId(userId).build());
        return "added to cart";
    }

    public void deleteCart() {
        crepo.deleteAll();
    }

    @Override
    public void deleteFromCart(Long policyId) {
        crepo.deleteById(policyId);
    }
    @Override
    public void deleteByCartId(Long cartRequestId) {
        crepo.deleteById(cartRequestId);
        System.out.println("Deleted");
    }


    @Override
    public List<CartResponse> getAllItems(Long userId) {
        List<CartRequest> cartRequests = (List<CartRequest>) crepo.findByUserId(userId);

        List<CartResponse> response=new ArrayList<>();
        WebClient client = WebClient.create();
        for (CartRequest request : cartRequests)
        {
            String getPolicyURL="http://localhost:9090/app/PolicyByPolicyId/"+request.getPolicyId();
            Mono<Policy> mono= client
                    .get()
                    .uri(getPolicyURL)
                    .retrieve()
                    .bodyToMono(Policy.class);
            response.add(CartResponse.builder().userId(userId).cartRequestId(request.getCartRequestId()).policy(mono.block())
                    .price(request.getPrice()).build());


        }
        return response;



    }



}
