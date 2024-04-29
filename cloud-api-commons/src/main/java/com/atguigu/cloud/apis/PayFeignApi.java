package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping("/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id);

    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    public ResultData getPayById(@PathVariable("id") Integer id);

    @GetMapping("/pay/getall")
    public ResultData getPayAll();

    @GetMapping("/pay/get/info")
    public String getInfoByConsul();

    //Resilience4j circuitbreaker的例子
    @GetMapping("/pay/circuit/{id}")
    public String myCircurt(@PathVariable("id") Integer id);

    //Resilience4j Bulkhead 的例子
    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();

}
