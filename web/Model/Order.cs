﻿namespace web.Model;

public class Order
{
    public int Id { get; set; }
    public int CustomerId { get; set; }
    public Product Product { get; set; }
}