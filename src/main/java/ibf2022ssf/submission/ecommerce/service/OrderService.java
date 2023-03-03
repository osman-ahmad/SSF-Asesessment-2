package ibf2022ssf.submission.ecommerce.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import ibf2022ssf.submission.ecommerce.model.ItemOrder;

@Service
public class OrderService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save(final ItemOrder io){
        System.out.println("io" + io.toJSON().toString());
        redisTemplate.opsForValue().set(io.getInvoiceId(), io.toJSON().toString());
    }

    public ItemOrder findById(final String poId) throws IOException{
        String poStr = (String)redisTemplate.opsForValue().get(poId);
        ItemOrder i = ItemOrder.create(poStr);
        return i;
    }
}
