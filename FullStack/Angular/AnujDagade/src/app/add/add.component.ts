import { Component } from '@angular/core';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  num1: number = 0;
  num2: number = 0;
  result: number = 0;
  showResult: boolean = false;

  addNumbers() {
    this.result = this.num1 + this.num2;
    this.showResult = true;
  }
}
