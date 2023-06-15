import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {
  
  @Output() mandarTabla = new EventEmitter<string>(); 
  

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  navegar(data: string) {
    this.router.navigateByUrl(`/management/${data}`);
  }

  navegarAInicio() {
    this.router.navigateByUrl(`/home`);
  }

  

  

}
