package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {

    public static final String PaymentSrv_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/del/{id}")
    public ResultData<String> deletePayInfo(@PathVariable("id") Integer id){
          restTemplate.delete(PaymentSrv_URL+"/pay/del/"+id,id);
          return ResultData.success("成功删除记录，id为："+id);
    }

    @GetMapping("/consumer/pay/update")
    public ResultData<String> updatePayInfo(PayDTO payDTO){
        restTemplate.put(PaymentSrv_URL+"/pay/update",payDTO);
        return ResultData.success("成功修改记录，id为："+payDTO.getId());
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/"+id, ResultData.class);
    }

    @GetMapping("/consumer/pay/getall")
    public ResultData getPayAll(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/getall",ResultData.class);
    }

}
