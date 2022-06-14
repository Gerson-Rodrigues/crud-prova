import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { OrderModule } from 'ngx-order-pipe';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { EdicaoComponent } from './edicao/edicao.component';

const routes: Routes = [
  {path: 'consulta', component: ConsultaComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'edicao/:id_medico', component: EdicaoComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    ConsultaComponent,
    EdicaoComponent,
    CadastroComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    OrderModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
