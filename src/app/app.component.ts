import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  //atributos
isAuthenticated: boolean = false;
loginUsuario: String | null = '';

constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.isAuthenticated = localStorage.getItem("access_token")!= null &&
                          localStorage.getItem("login_usuario")!= null;
    if(this.isAuthenticated){
      this.loginUsuario = localStorage.getItem("login_usuario");
    }
  }
  geraPdf():void {
    if(window.confirm('Gerar um PDF?')){
      this.httpClient.get(environment.url_geradora+'/pdf',
      {responseType: 'text'}).subscribe((data) => {
        alert (data);
        this.ngOnInit();
      },
      (e)=>{ console.log(e)})
    }
    }

  geraHtml():void {
    if(window.confirm('Gerar um HTML?')){
      this.httpClient.get(environment.url_geradora+'/html',
      {responseType: 'text'}).subscribe((data) => {
        alert (data);
        this.ngOnInit();
      },
      (e)=>{ console.log(e)})
    }
  }
//função para fazer o logout do ususario...
logout():void{
  if(window.confirm('Deseja realmente sair do sistema?')){
  //apagar as informações no local storage
  localStorage.removeItem('access_token');
  localStorage.removeItem('login_usuario');

  //redirecionar para pagina inicial do projeto (login)
  window.location.href="/";
  }
}

  title = 'projeto-prova';
}
