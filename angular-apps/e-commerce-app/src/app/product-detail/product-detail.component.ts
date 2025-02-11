import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';  // Keep ActivatedRoute from Angular
import { Product } from '../product';
import product_data from "../../../public/products.json";  // Assuming this is a local JSON
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';  // Correct Router import

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class ProductDetailComponent implements OnInit {
  product!: Product; 
  quantity: number = 1;  

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    const productId = +this.activatedRoute.snapshot.paramMap.get('id')!;
    
    const selectedProduct = product_data.find(product => product.Id === productId);
    
    if (selectedProduct) {
      this.product = selectedProduct; 
    }
  }

  addToCart(): void {
    console.log('Product added to cart:', this.product, 'Quantity:', this.quantity);
    this.router.navigate([`/home`]);  // Navigate to the home page after adding to cart
  }
}
