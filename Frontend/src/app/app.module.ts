import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import {ReactiveFormsModule} from '@angular/forms';
import { AllPapersComponent } from './all-papers/all-papers.component';
import { MyPapersComponent } from './my-papers/my-papers.component';
import { MyReviewsComponent } from './my-reviews/my-reviews.component';
import { PapersToReviewComponent } from './papers-to-review/papers-to-review.component';
import { ReviewerSelectionComponent } from './reviewer-selection/reviewer-selection.component';
import { AllReviewsComponent } from './all-reviews/all-reviews.component';
import { PaperSectionComponent } from './paper-section/paper-section.component';
import {
  MatButtonModule,
  MatCardModule, MatCheckboxModule,
  MatDatepickerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatNativeDateModule,
  MatToolbarModule
} from '@angular/material';
import { ReviewerSectionComponent } from './reviewer-section/reviewer-section.component';
import { HeaderComponent } from './header/header.component';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {AuthInterceptorService} from './services/auth-interceptor.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {DatePipe} from '@angular/common';
import {MatSelectModule} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    AllPapersComponent,
    MyPapersComponent,
    MyReviewsComponent,
    PapersToReviewComponent,
    ReviewerSelectionComponent,
    AllReviewsComponent,
    PaperSectionComponent,
    ReviewerSectionComponent,
    HeaderComponent
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatButtonModule,
        MatInputModule,
        MatDatepickerModule,
        MatIconModule,
        MatToolbarModule,
        MatNativeDateModule,
        NoopAnimationsModule,
        MatCheckboxModule,
        HttpClientModule,
        MatSelectModule
    ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }, DatePipe ],
  bootstrap: [AppComponent]
})
export class AppModule { }
