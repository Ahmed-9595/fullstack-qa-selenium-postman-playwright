import { test, Page } from '@playwright/test';
import { LoginPage } from '../pages/login.page';
import { InventoryPage } from '../pages/inventory.page';

test('Store Admin can create an inventory item', async ({ page }) => {
  const login = new LoginPage(page);
  const inventory = new InventoryPage(page);

  await page.goto('/login');

  await login.login(
    process.env.STORE_ADMIN_EMAIL || 'store-admin@example.com',
    process.env.STORE_ADMIN_PASSWORD || 'change-me'
  );

  await inventory.open();
  await inventory.addProduct('Wireless Mouse', '25.00');
  await inventory.expectSuccessToast();
});