import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GuardService {

  constructor() { }

  canActivate() {
    if(localStorage.getItem("token") != null && localStorage.getItem("user_rol")) {
      return true;
    }else {
      return false;
    }
  }
}
