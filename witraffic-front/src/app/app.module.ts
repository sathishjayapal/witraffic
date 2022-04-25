import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TrafficListReaderComponent } from './traffic-list-reader/traffic-list-reader.component';
import {FormsModule} from "@angular/forms";
import {TrafficListReaderModule} from "./traffic-list-reader/traffic-list-reader.module";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'welcome', component: HomeComponent},
      {path: '', redirectTo: 'welcome', pathMatch: 'full'},
      {path: '**', redirectTo: 'welcome', pathMatch: 'full'}
    ]),
    TrafficListReaderModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
