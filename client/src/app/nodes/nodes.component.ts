import {Component, OnInit, Input, EventEmitter, Output} from '@angular/core';


@Component({

  selector: 'nodes',
  template: '<div><ul *ngFor="let node of nodes"><li>{{node.companyName}} | {{node.estimatedAnnualEarnings}}' +
  '<a *ngIf="node.childs.length>0"> | {{node.estimatedAnnualEarningsForChilds}}</a> ' +

  ' <nodes [nodes]="node.childs"  *ngIf="node.childs"></nodes>' +
  '</li></ul></div>',
})
export class NodesComponent {

  @Input() nodes;

}
