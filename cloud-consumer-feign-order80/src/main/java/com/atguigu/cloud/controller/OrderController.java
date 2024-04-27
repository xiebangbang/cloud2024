package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        ResultData<String> resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }
    @DeleteMapping("/feign/pay/del/{id}")
    public ResultData<Integer> deletePayInfo(@PathVariable("id") Integer id){
        ResultData<Integer> resultData = payFeignApi.deletePay(id);
        return resultData;
    }
    @PutMapping("/feign/pay/update")
    public ResultData<String> updatePayInfo(PayDTO payDTO){
        ResultData<String> resultData = payFeignApi.updatePay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        ResultData resultData ;
        try{
            System.out.println("-----调用开始："+ DateUtil.now());
            resultData = payFeignApi.getPayById(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("-----调用结束："+ DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    @GetMapping("/feign/pay/getall")
    public ResultData getPayAll(){
        ResultData resultData = payFeignApi.getPayAll();
        return resultData;
    }

    @GetMapping("/feign/pay/get/info")
    private String getInfoByConsul(){
        String info = payFeignApi.getInfoByConsul();
        return info;
    }

}
