import { Directive, ElementRef, HostListener, Input, OnInit, Renderer2, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appRoles]',
  standalone: true
})
export class RolesDirective implements OnInit{

  @Input('appRoles') roles: string[] = [];

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}


  ngOnInit(): void {
    this.updateView();
  }

  private updateView() {


    if (this.roles.includes(localStorage.getItem("role")!)) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}
