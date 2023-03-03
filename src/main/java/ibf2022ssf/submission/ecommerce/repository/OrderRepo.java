// package ibf2022ssf.submission.ecommerce.repository;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.expression.ParseException;
// import org.springframework.stereotype.Repository;

// import ibf2022ssf.submission.ecommerce.model.Order;

// @Repository
// public class OrderRepo {
    
//     @Autowired @Qualifier("my-redis")
//     private RedisTemplate<String, String> template;

//     public void addToCart(String item, String quantity) {
// 		 System.out.printf(">>> add to cart: %s, %d\n", item, quantity);
//         template.opsForValue().set(item, quantity);
//     }
// }
