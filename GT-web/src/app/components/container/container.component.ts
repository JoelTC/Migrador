import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { Router } from '@angular/router';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.scss']
})
export class ContainerComponent implements OnInit {

  public date: number;
  showFiller = false;
  mobileQuery: MediaQueryList;
  mobile: MediaQueryList;

  mobileListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private router: Router,) {
      this.date = Date.now();
  }

  ngOnInit(): void {
  }

  public logout(snav: any) {
    if (snav.opened === true) {
      snav.toggle();
    }
    sessionStorage.clear();
    localStorage.clear();
    this.router.navigate(['login']);
  }

}