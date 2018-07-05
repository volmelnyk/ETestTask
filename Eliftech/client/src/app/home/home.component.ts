import {Component, OnInit} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

export class Node {
  id: number;
  companyName: string;
  estimatedAnnualEarnings: number;
  estimatedAnnualEarningsForChilds: number;
  childs?: Node[];
}


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  companyName: string;
  estimatedAnnualEarnings: number;

  companiesForSelect: any;
  nodes: any;

  companyNameForEdit: any;
  estimatedAnnualEarningsForEdit: any;
  ownerId: number;

  dialog: NgbModalRef | null;
  idForDelete: number;

  constructor(private modalService: NgbModal, private http: HttpClient) {

  }

  ngOnInit() {
    this.idForDelete = 0;
    this.ownerId = 0;
    this.getAllNodes();
    this.getCompaniesForSelect();

  }

  getAllNodes() {
    this.http.get<Node[]>('http://localhost:8080/get-all-companies-for-tree')
      .subscribe(result => {

        this.nodes = result;
      });
  }

  getCompaniesForSelect() {
    this.http.get('http://localhost:8080/get-all-companies')
      .subscribe(result => {

        this.companiesForSelect = result;
      });
  }

  save() {
    this.http.post<any>('http://localhost:8080/add-company',
      {
        companyName: this.companyName,
        estimatedAnnualEarnings: this.estimatedAnnualEarnings,
        ownerID: this.ownerId
      }).subscribe(null, null, () => {
      console.log('before');
      this.getAllNodes();
      this.getCompaniesForSelect();
      if (this.dialog) {
        this.dialog.dismiss();
        this.dialog = null;
      }
    });

    this.companyName = undefined;
    this.estimatedAnnualEarnings = undefined;
    this.ownerId = 0;

  }

  open(content) {
    this.dialog = this.modalService.open(content);
  }

  getById(id) {
    console.log('edit');
    console.log(id);
    this.http.get<Node>('http://localhost:8080/get-company/' + this.ownerId).subscribe(
      data => {
        this.companyNameForEdit = data.companyName;
        this.estimatedAnnualEarningsForEdit = data.estimatedAnnualEarnings;
      }
    );
  }

  setForEdit() {
    this.getById(this.ownerId);
  }

  edidCompany() {

    if (this.ownerId != 0 && this.companyNameForEdit != undefined && this.estimatedAnnualEarningsForEdit != undefined)
      this.http.post<any>('http://localhost:8080/edit-company',
        {
          companyName: this.companyNameForEdit,
          estimatedAnnualEarnings: this.estimatedAnnualEarningsForEdit,
          id: this.ownerId
        }).subscribe(null, null, () => {
        this.getAllNodes();
        this.getCompaniesForSelect();
        if (this.dialog) {
          this.dialog.dismiss();
          this.dialog = null;
        }
      });

    this.ownerId = 0;
    this.companyNameForEdit = undefined;
    this.estimatedAnnualEarningsForEdit = undefined;
  }

  deleteCompany() {
    if (this.idForDelete != 0)
      this.http.get('http://localhost:8080/delete/' + this.idForDelete).subscribe(
        null, null, () => {
          this.getAllNodes();
          this.getCompaniesForSelect();
          if (this.dialog) {
            this.dialog.dismiss();
            this.dialog = null;
          }
        }
      );
    this.idForDelete = 0;

  }
}
