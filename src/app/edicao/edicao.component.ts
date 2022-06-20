import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-edicao',
  templateUrl: './edicao.component.html',
  styleUrls: ['./edicao.component.css']
})
export class EdicaoComponent implements OnInit {
  mensagem: string = '';

  constructor(private httpClient: HttpClient,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id_medico') as string;
    this.httpClient.get(environment.url+'/medicos/'+id).subscribe(
      (data:any) => {this.formEdicao.patchValue(data);},
      (e)=>{ console.log(e);});
  }

  //montando a estrutura do formulario
  formEdicao = new FormGroup({
    id_medico: new FormControl('', [Validators.required]),
    nome: new FormControl('', [Validators.required]),
    crm: new FormControl('', [Validators.required]),
    telefone: new FormControl('', [Validators.required]),
    tipo: new FormControl('', [Validators.required]),
  });

  get form(): any {
    return this.formEdicao.controls;
  }

  onEdit(): void {
    this.httpClient.put(environment.url+'/medicos', this.formEdicao.value,{responseType:'text'}).subscribe(data => {
      this.mensagem = data;
      this.formEdicao.reset();
    },
    e =>{
      this.mensagem = " Ocorreu um erro ao tentar editar os dados!!";
      console.log(e);
    }
    );
  }
}
