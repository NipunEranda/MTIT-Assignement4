package com.sliit.abc.controller;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.abc.model.ItemRequest;
import com.sliit.abc.model.ItemResponse;
import com.sliit.abc.services.ProductService;
import com.sliit.abc.services.ProductServiceImp;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class ProductManager {

	ProductService ps = new ProductServiceImp();

	@PostMapping(value = "/AddItem", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String registerItem(@RequestBody ItemRequest item) {
		return ps.registerItem(item.getName(), item.getItemType(), item.getDescription(),
				item.getPrice()).toString();
	}

	@PutMapping(value = "/updateItem", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String updateItem(@RequestBody ItemRequest item) {
		return ps.updateItemDetails(item.getName(), item.getItemType(), item.getDescription(),
				item.getPrice(), item.getId()).toString();
	}

	@DeleteMapping(value = "/deleteItem/{id}")
	public String deleteItem(@PathVariable String id) {
		return ps.deleteItem(id).toString();
	}

	@GetMapping(value = "/")
	public String getAllItems() {
		return ps.getItemList().toString();
	}

	@GetMapping(value = "/{id}")
	public String getItemById(@PathVariable String id) {
		return ps.getItemDetails(id).toString();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductManager.class, args);
	}

}