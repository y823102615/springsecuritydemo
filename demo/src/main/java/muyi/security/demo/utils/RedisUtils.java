package muyi.security.demo.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisUtils {
    private RedisTemplate redisTemplate;

    public void set(Object key,Object value){
         redisTemplate.opsForValue().set(key,value, Duration.ofMinutes(5));
    }
    public  Object get(Object key){
        return redisTemplate.opsForValue().get(key);
    }
}
