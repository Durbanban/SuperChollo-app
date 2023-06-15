import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit {

  @Output() isLastPage = new EventEmitter<Boolean>;

  constructor() { }

  ngOnInit(): void {
  }
  

}
