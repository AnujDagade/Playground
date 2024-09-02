import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AlertComponent } from './alert/alert.component';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Import FormsModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AlertComponent,NgIf,FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'welcome';
  num1: number = 0;
  num2: number = 0;
  result: number = 0;
  showResult: boolean = false;

  addNumbers() {
    this.result = this.num1 + this.num2;
    this.showResult = true;
  }
}
