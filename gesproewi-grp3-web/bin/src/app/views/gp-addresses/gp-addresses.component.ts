import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {GpAddress} from 'src/app/models/gp-address';
import {GpAddressService} from 'src/app/services/gp-address.service';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-gp-addresses',
  templateUrl: './gp-addresses.component.html',
  styleUrls: ['./gp-addresses.component.scss'],
})
export class GpAddressesComponent implements OnInit {
  gpAddresses!: GpAddress[];

  constructor(private addrService: GpAddressService, private router: Router,
              private alertService: ToastrService) {
  }

  ngOnInit(): void {
    this.getAddresses();
  }

  getAddresses() {
    this.addrService.getAll().subscribe(
      (res) => {
        this.gpAddresses = res;
      },
      (err) => {
      }
    );
  }

  edit(id: any) {
    this.router.navigate(['/admin/addresses/', id]);
  }

  delete(id: any) {
    if (confirm(`Do want to delete item ${id}`)) {
      this.addrService.delete(id).subscribe((res) => {
          this.getAddresses();
        },
        (error) => {
          this.alertService.error(`${error.error.message.split(';', 1)}`, `${error.status}`);
        });
    }


  }
}
