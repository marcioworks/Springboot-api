package com.marcio.springbootapi.dtos;

import java.io.Serializable;

import com.marcio.springbootapi.domain.Product;

public class ProductDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
	private String name;
	private Double price;

  public ProductDto(){}

  public ProductDto(Product obj){
    id = obj.getId();
    name = obj.getName();
    price = obj.getPrice();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  
}
