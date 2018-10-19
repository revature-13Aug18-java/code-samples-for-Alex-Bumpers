import { Component, ViewEncapsulation } from '@angular/core';
import {  HttpModule } from '@angular/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [HttpModule]
})
export class AppComponent {
  title = 'project2-angular-anm';
}
