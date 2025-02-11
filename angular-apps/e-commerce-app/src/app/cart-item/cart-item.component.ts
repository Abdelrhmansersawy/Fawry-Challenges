import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { BehaviorSubject } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavBarComponent } from '../nav-bar/nav-bar.component';


@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css'],
  imports: [CommonModule, FormsModule, NavBarComponent]
})
export class CartItemComponent implements OnInit {
  private cartSubject = new BehaviorSubject<{ product: Product, quantity: number }[]>([]);
  cartItems: { product: Product, quantity: number }[] = [];

  constructor() {}

  ngOnInit(): void {
    this.cartSubject.subscribe(cartItems => {
      this.cartItems = cartItems;
    });
  }

  addToCart(product: Product, quantity: number): void {
    const currentCart = this.cartSubject.value;
    const existingProduct = currentCart.find(item => item.product.Id === product.Id);

    if (existingProduct) {
      existingProduct.quantity += quantity;
    } else {
      currentCart.push({ product, quantity });
    }

    this.cartSubject.next(currentCart);
  }

  removeFromCart(productId: number): void {
    const updatedCart = this.cartSubject.value.filter(item => item.product.Id !== productId);
    this.cartSubject.next(updatedCart);  // Emit updated cart
  }


  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => total + (item.product.Price * item.quantity), 0);
  }
}
