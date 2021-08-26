package by.dach.app.feign;

import by.dach.app.model.feign.Ip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient (value = "ip", url = "https://api.ipify.org?format=json")
public interface Ipify {
    @GetMapping
    Ip getIp();

}
