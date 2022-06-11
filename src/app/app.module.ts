import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { OrderModule } from 'ngx-order-pipe';

import { AccountComponent } from './account/account.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { EdicaoComponent } from './edicao/edicao.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path: 'consulta', component: ConsultaComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'edicao', component: EdicaoComponent},
  {path: 'account', component: AccountComponent},
  {path: 'login', component: LoginComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    LoginComponent,
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
