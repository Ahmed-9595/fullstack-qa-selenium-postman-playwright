import { expect, Page } from '@playwright/test';

export class InventoryPage {
  constructor(private readonly page: Page) { }

  async open() {
    await this.page.getByRole('link', { name: 'Inventory' }).click();
    await expect(this.page.getByRole('heading', { name: /inventory/i })).toBeVisible();
  }

  async addProduct(productName: string, price: string) {
    await this.page.getByLabel('Product Name').fill(productName);
    await this.page.getByLabel('Price').fill(price);
    await this.page.getByRole('button', { name: 'Save' }).click();
  }

  async expectSuccessToast() {
    await expect(
      this.page.getByRole('status').filter({ hasText: /success|saved/i })
    ).toBeVisible();
  }
}