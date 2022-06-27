import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { OrderModule } from 'ngx-order-pipe';
import { TokenInterceptor } from './_interceptor/tokenInterceptor';
import { AccountComponent } from './account/account.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ConsultaComponent } from './consulta/consulta.component';
import { EdicaoComponent } from './edicao/edicao.component';
import { LoginComponent } from './login/login.component';
import { environment } from 'src/environments/environment';
import { EmailComponent } from './email/email.component';


const routes: Routes = [
  {path: 'consulta', component: ConsultaComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'edicao/:id_medico', component: EdicaoComponent},
  {path: 'account', component: AccountComponent},
  {path: '', component: LoginComponent},
  {path: 'email', component: EmailComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    ConsultaComponent,
    EdicaoComponent,
    CadastroComponent,
    AccountComponent,
    LoginComponent,
    EmailComponent,

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
  providers: [
    {
      //Configuração do uso do interceptor
      provide: HTTP_INTERCEPTORS,
      useClass:TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
