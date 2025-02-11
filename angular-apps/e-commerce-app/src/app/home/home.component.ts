import { Component } from '@angular/core';
import { NavBarComponent } from "../nav-bar/nav-bar.component";
import { Product } from '../product';
import { FormsModule } from '@angular/forms';
import { CommonModule} from '@angular/common';
import product_data from "../../../public/products.json"
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  imports: [NavBarComponent, CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  products: Product [] = product_data
  
  constructor(private router: Router){}
  navigateToProductDetail(productId: number): void {    
    this.router.navigate([`/product/${productId}`]);  // Navigate to the product detail page with the product ID
  }
}
