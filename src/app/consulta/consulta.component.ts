import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {
  registro: any[] =[];
  page = 1;
  //injeção de dependência
  constructor(private httpClient: HttpClient) { }

  //Método executa quando o componente é aberto
  ngOnInit(): void {
    this.httpClient.get(environment.url+'/medicos').subscribe(
      data=> {this.registro = data as any[];
      },
      e => {console.log(e)}
    )
  }//fecha o método onInit
 

  //função pra fazer a exclusão do produto na API
  excluir(id_medico:number):void {
    if(window.confirm('Deseja realmente excluir o cadastro selecionado???')){
      this.httpClient.delete(environment.url+'/medicos/'+id_medico,
      {responseType: 'text'}).subscribe((data)=> {
        alert (data);      //exibir mensagem em uma janela popup
        this.ngOnInit();   //recarregar a consulta de produtos
      },
      (e)=>{ console.log(e)})
    }
  }
  key: string = 'crm'; // Define um valor padrão, para quando inicializar o componente
    reverse: boolean = false;
    sort(key: string) {
        this.key = key;
        this.reverse = !this.reverse;
    }

    handlePageChange(event: any): void {
      this.page = event;
    }
}
