using DTO.Cart;
using System.Text.Json.Serialization;

namespace sep3.orders.Model;

public class CartItem
{
    public int Id { get; set; }
    
    public string ProductName { get; set; }
    
    public long ProductId { get; set; }
    public long VariantId { get; set; }
    
    public string Materials { get; set; }
    public string Size { get; set; }
    public int Quantity { get; set; }
    public double Price { get; set; }

    [JsonIgnore]
    public ShoppingCart ShoppingCart { get; set; }
    public int ShoppingCartId { get; set; }


    public static List<CartItem> ToModel(List<CreateCartItemDto> createCartItemDtos)
    {
        return createCartItemDtos
            .Select(cartItem => new CartItem
            {
                ProductId = cartItem.ProductId,
                ProductName = cartItem.ProductName,
                VariantId = cartItem.VariantId,
                Materials = cartItem.Materials,
                Size = cartItem.Size,
                Quantity = cartItem.Quantity,
                Price = cartItem.Price,
                
                
            })
            .ToList();
    }
}