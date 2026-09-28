import { expect, Page } from '@playwright/test';

export class LoginPage {
  constructor(private readonly page: Page) {}

  async login(email: string, password: string) {
    await this.page.getByLabel('Email').fill(email);
    await this.page.getByLabel('Password').fill(password);
    // use Regix instead of string and i for ignore case
    await this.page.getByRole('button', { name: /log in|login|sign in/i }).click();
    await expect(this.page).toHaveURL(/dashboard|home/i);
  }
}