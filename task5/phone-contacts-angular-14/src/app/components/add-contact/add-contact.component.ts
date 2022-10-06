import { Component, OnInit } from '@angular/core';
import { Contact } from "../../models/contact.model";
import { ContactService } from "../../services/contact.service";

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent implements OnInit {

  contact: Contact = {
    lastName: '',
    firstName: '',
    patronymic: '',
    phone: ''
  };
  submitted = false;

  constructor(private contactService: ContactService) { }

  ngOnInit(): void {
  }

  saveContact(): void {
    const data = {
      lastName: this.contact.lastName,
      firstName: this.contact.firstName,
      patronymic: this.contact.patronymic,
      phone: this.contact.phone
    };

    this.contactService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newContact(): void {
    this.submitted = false;
    this.contact = {
      lastName: '',
      firstName: '',
      patronymic: '',
      phone: ''
    };
  }

}
