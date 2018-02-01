/**
 * Created by Владислав on 02.03.2017.
 */

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PetService} from "./pet.service";
import {Pet} from "./pet";
import {ModalComponent} from "ng2-bs3-modal/components/modal";
import {Image} from "./Image";
const SRC="data:image/png;base64,";

@Component({
  moduleId: module.id,
  selector: 'my-pet',
  templateUrl: 'html/pet.component.html',
  styleUrls: ['../shared_css/AppCss/app.component.css','../shared_css/app.css','../shared_css/bootstrap.css','css/pet.css'],
})
export class PetComponent implements OnInit {
  token: string;
  ssoId: string;
  pets: Pet[] = [];
  pet: Pet;
  modal:ModalComponent;
  images: Image[] = [];
  SRC=SRC;
  imagesId: ImageId[] = [];

    constructor(private activatedRoute: ActivatedRoute,private petService: PetService) { }

    ngOnInit() {
      this.pet = this.getDefaultModelPet();
    //  console.log(this.pet);
      this.updateTable();
    }

//TODO КОСЯК С ДАТОЙ
  private getDefaultModelPet(){
    return this.pet = {
      petId: 0,
      history: "",
      fullHistory: "",
      numberOfComplaints: 0,
      dataLastComplaints:  new Date(9/3/2010),
      nickName: "",
      kindOfAnimal: "",
      client: {
        clientId:0
      },
      employee:{
        employeeId:0
      },
    };
  }

  private updateTable(){
    this.activatedRoute.params.subscribe(params => {
      this.token = params['token'];
      this.ssoId = params['ssoId']
      this.petService.getAllPets(this.token).subscribe((data)=>{
        this.pets = data;
        // console.log(this.pets)
        // for (let i = 0; i < this.pets.length;i++){
        //   this.images[i] = {
        //     kindOfAnimal:"",
        //     image:null
        //   };
        //   this.images[i].kindOfAnimal = this.pets[i].kindOfAnimal;
        //   console.log( this.images[i].kindOfAnimal);
        // }
        for (let i = 0; i < this.pets.length;i++) {
          this.imagesId[i] = {
            id:"",
          };
          this.imagesId[i].id = this.pets[i].kindOfAnimal;
        }
        this.petService.getAllImage(this.token, this.imagesId).subscribe((data)=>{
          this.images = data;
         // console.log(this.images[0].image)
        })
      });
    });
  }

  protected openModal(pet: Pet,modal:ModalComponent) {
    this.pet = pet;
    this.modal = modal;
    modal.animation = true;
    modal.open();
  }

  protected closeModal(modal:ModalComponent) {
    this.pet = this.getDefaultModelPet();
    modal.close();
  }

}
