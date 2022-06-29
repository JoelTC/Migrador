import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'src/app/models/entities/Menu';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  public items: MenuItem[];
  public modules: any[];

  constructor(private router: Router) {
    this.initAccesoSistema(false);
  }

  ngOnInit() {
  }

  private initAccesoSistema(b: boolean) {
    this.modules = [
      {module: 1, state: false},
      {module: 2, state: false},
      {module: 3, state: false},
      {module: 4, state: false}];
    this.modules.forEach(value => {
      value.state = b;
    });
  }

  show(id: number) {
    this.modules.forEach(value => {
      if (value.module === id) {
        value.state = !value.state;
      } else {
        value.state = false;
      }
    });
  }

  public home() {
    this.router.navigate(['home']).then();
  }

  public logout() {
    /* sessionStorage.clear();
    localStorage.clear();
    this.serviceData.authenticated = false;
    this.router.navigate(['login']); */
  }
}
