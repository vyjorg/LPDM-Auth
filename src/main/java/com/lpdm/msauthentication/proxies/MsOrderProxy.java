package com.lpdm.msauthentication.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "zuul-server", url = "http://localhost:28090")
@RibbonClient(name = "ms-order")
public interface MsOrderProxy {

}
