package com.erkmen.controller;

import com.erkmen.domain.Order;
import com.erkmen.dto.OrderElementInfoDTO;
import com.erkmen.dto.PayDTO;
import com.erkmen.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
@Api(tags = "orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/add")
    @ApiOperation(value = "${OrderController.add}")
    public ResponseEntity<?> add(HttpServletRequest req, @RequestBody @Valid OrderElementInfoDTO orderElementInfoDTO) {
        orderService.add(req, orderElementInfoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "${OrderController.edit}")
    public ResponseEntity<?> remove(HttpServletRequest req, @RequestBody @Valid OrderElementInfoDTO orderElementInfoDTO) {
        orderService.edit(req, orderElementInfoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pay")
    @ApiOperation(value = "${OrderController.pay}")
    public ResponseEntity<?> pay(HttpServletRequest req, @RequestBody @Valid PayDTO payDTO) {
        orderService.pay(payDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "${OrderController.getall}", response = Order.class, responseContainer = "List")
    public List<Order> getAll() {
        return orderService.getAll();
    }

}
