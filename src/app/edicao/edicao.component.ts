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
    const id = this.activatedRoute.snapshot.paramMap.get('id') as string;
    this.httpClient.get(environment.apiUrl+'/profissionais/'+id).subscribe(
      (data:any) => {this.formEdicao.patchValue(data);},
      (e)=>{ console.log(e);});
  }

  //montando a estrutura do formulario
  formEdicao = new FormGroup({
    id: new FormControl(''),
    nome: new FormControl('', [Validators.required]),
    cpf: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    telefone: new FormControl('', [Validators.required]),
    descricao: new FormControl('', [Validators.required]),
  });

  get form(): any {
    return this.formEdicao.controls;
  }

  onEdit(): void {
    this.httpClient.put(environment.apiUrl + '/profissionais', this.formEdicao.value,{responseType:'text'}).subscribe(data => {
      this.mensagem = data;

    },
    e =>{
      this.mensagem = " Ocorreu um erro ao tentar editar o profissional";
      console.log(e);
    }
    );
  }

}
