package com.sliit.abc.Controler;

import com.sliit.abc.Dto.*;
import com.sliit.abc.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public class OrderControler {

	@Autowired
	private OrderServiceImp orderService;
	
	@PostMapping(consumes="application/json",produces="application/json")
	public @ResponseBody
	OrderResponse makeOrder(@RequestBody OrderRequest orderRequest) {
		System.out.println("Order Details: "+orderRequest);
		return orderService.makeOrder(orderRequest);
	}
	
}
