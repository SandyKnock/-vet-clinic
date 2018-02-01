import {Component } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'my-page',
  templateUrl: 'html/mainPage.component.html',
  styleUrls: ['css/mainPage.component.css'],
})
export class MainPageComponent  {
  title = 'Main Page';
  zoom: number = 15;
  lat: number = 51.667341;
  lon: number =  39.195059;
  imageMarker = 'app/mainPage/html/res/imgClinic.png';
  textStrong = "Удобный подбор компаний по параметрам и расположению ветеринарные клиники с отзывами пользователей, рейтингом и большими фото. "
  imageAnimal = ['app/mainPage/html/res/1.jpg','app/mainPage/html/res/2.jpg','app/mainPage/html/res/3.jpg','app/mainPage/html/res/4.jpg'];
  imageLogotip = 'app/mainPage/html/res/vistar.png';
}
