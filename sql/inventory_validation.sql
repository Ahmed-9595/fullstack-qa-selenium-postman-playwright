SELECT
    p.item_name,
    p.sku,
    p.quantity,
    p.price,
    c.category_name
FROM products p
INNER JOIN categories c
    ON c.id = p.category_id
WHERE p.sku = 'MS-001'
  AND c.category_name = 'Electronics';