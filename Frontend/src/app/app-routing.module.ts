import { CreateReviewTemplateComponent } from './xonomy/create-review-template/create-review-template.component';
import { EditCoverLetterComponent } from './xonomy/edit-cover-letter/edit-cover-letter.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {SignUpComponent} from './auth/sign-up/sign-up.component';
import {MyReviewsComponent} from './my-reviews/my-reviews.component';
import {MyPapersComponent} from './my-papers/my-papers.component';
import {AllPapersComponent} from './all-papers/all-papers.component';
import {AllReviewsComponent} from './all-reviews/all-reviews.component';
import {PapersToReviewComponent} from './papers-to-review/papers-to-review.component';
import {ReviewerSelectionComponent} from './reviewer-selection/reviewer-selection.component';
import {EditPaperComponent} from './xonomy/edit-paper/edit-paper.component';
import { EditReviewComponent } from './xonomy/edit-review/edit-review.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'all-papers', component: AllPapersComponent },
  { path: 'all-reviews', component: AllReviewsComponent },
  { path: 'my-papers', component: MyPapersComponent },
  { path: 'my-reviews', component: MyReviewsComponent },
  { path: 'papers-to-review', component: PapersToReviewComponent },
  { path: 'reviewer-selection', component: ReviewerSelectionComponent },
  { path: 'edit-paper', component: EditPaperComponent },
  { path: 'edit-paper/:id', component: EditPaperComponent },
  { path: 'edit-cover-letter/:id', component: EditCoverLetterComponent },
  { path: 'edit-review/:id', component: EditReviewComponent },
  { path: 'create-review-template/:id', component: CreateReviewTemplateComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
