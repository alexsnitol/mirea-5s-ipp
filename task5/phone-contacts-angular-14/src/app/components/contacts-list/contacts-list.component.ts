import { Component, OnInit } from '@angular/core';
import { Contact } from "../../models/contact.model";
import { ContactService } from "../../services/contact.service";

@Component({
  selector: 'app-contacts-list',
  templateUrl: './contacts-list.component.html',
  styleUrls: ['./contacts-list.component.css']
})
export class ContactsListComponent implements OnInit {

  contacts?: Contact[];
  currentContact: Contact = {};
  currentIndex = -1;

  constructor(private contactService : ContactService) { }

  ngOnInit(): void {
    this.retrieveContacts();
  }


  retrieveContacts(): void {
    this.contactService.getAll()
      .subscribe({
        next: (data) => {
          this.contacts = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveContacts();
    this.currentContact = {};
    this.currentIndex = -1;
  }

  setActiveContact(contact: Contact, index: number): void {
    this.currentContact = contact;
    this.currentIndex = index;
  }

  removeAllContacts(): void {
    this.contactService.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }

}
