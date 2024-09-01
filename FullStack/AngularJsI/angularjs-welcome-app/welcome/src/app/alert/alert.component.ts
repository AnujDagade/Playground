
import { Component } from '@angular/core';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent {
  showAlert() {
    alert("Welcome to School of Computer Science, NWC");
  }
}