import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  mensagem: string = '';

  constructor(private httpClient: HttpClient) {}

  ngOnInit(): void {}
  //montando a estrutura do formulario
  formCadastro = new FormGroup({
    nome: new FormControl('', [Validators.required]),
    crm: new FormControl('', [Validators.required]),
    telefone: new FormControl('', [Validators.required]),
    tipo: new FormControl('', [Validators.required]),
  });
  //acessando o formulário/campos na página HTML
  get form(): any {
    return this.formCadastro.controls;
  }
  //função para fazer a chamada do cadastro na API
  onSubmit(): void {
    this.httpClient
      .post(`${environment.url}/medicos`, this.formCadastro.value, {
        responseType: 'text',
      })
      .subscribe(
        data => {
          this.mensagem = data;
          this.formCadastro.reset();
        },
        e => {
          this.mensagem = 'Erro ao cadastrar';
          console.log(e);
        }
      );
  }
}
