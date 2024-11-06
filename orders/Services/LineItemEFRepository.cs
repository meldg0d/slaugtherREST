using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using sep3.orders.Model;
using sep3.orders.Infrastructure;

namespace sep3.orders.Services;

public class LineItemEFRepository : ILineItemRepository
{
    private readonly OrdersContext _context;

    public LineItemEFRepository(OrdersContext context)
    {
        _context = context;
        if (_context == null)
            _context = OrdersContext.GetInstance(null);
    }
    
    public async Task<LineItem> CreateLineItemAsync(LineItem lineItem)
    {
        _context.LineItems.Add(lineItem);
        await _context.SaveChangesAsync();
        return lineItem;
        // TODO: Event
    }

    public async Task<LineItem> CreateLineItemAsync(int? orderId, int? productId, int? quantity, double? price)
    {
        if (orderId.HasValue && productId.HasValue && quantity.HasValue && price.HasValue)
        {
            LineItem lineItem = new LineItem()
            {
                OrderId = orderId.Value,
                Price = price.Value,
                ProductId = productId.Value,
                Quantity = quantity.Value
            };
            _context.LineItems.Add(lineItem);
            await _context.SaveChangesAsync();
            return lineItem;
            //TODO: Event
        }
        else
            throw new ArgumentNullException();
    }

    public async Task<List<LineItem>> GetLineItemsAsync()
    {
        return await _context.LineItems.ToListAsync();
    }

    public async Task<LineItem> GetLineItemAsync(int? id)
    {
        if (id.HasValue)
            return await _context.LineItems.Where(l => l.Id == id.Value).FirstOrDefaultAsync();
        else
            throw new ArgumentNullException(nameof(id));
    }

    public async Task UpdateLineItemAsync(int? id, int? orderId, int? productId, int? quantity, double? price)
    {
        if (id.HasValue && orderId.HasValue && productId.HasValue && quantity.HasValue && price.HasValue)
        {
            LineItem lineItem = await GetLineItemAsync(id) ?? throw new InvalidOperationException();
            lineItem.OrderId = orderId.Value;
            lineItem.ProductId = productId.Value;
            lineItem.Quantity = quantity.Value;
            lineItem.Price = price.Value;
            await _context.SaveChangesAsync();
            //TODO: Event
        }
        else
            throw new ArgumentNullException();
    }

    public async Task DeleteLineItemAsync(int? id)
    {
        if (id.HasValue)
        {
            LineItem lineItem = await _context.LineItems.Where(l => l.Id == id.Value).FirstOrDefaultAsync() ?? throw new InvalidOperationException();
            _context.LineItems.Remove(lineItem);
            await _context.SaveChangesAsync();
            // TODO: Event
        }
        else
            throw new ArgumentNullException(nameof(id));
    }
}