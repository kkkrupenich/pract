import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Review } from '../../entities/review';
import { ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-review-update',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './review-update.component.html',
  styleUrl: './review-update.component.css'
})
export class ReviewUpdateComponent {
  review: Review = new Review();

  constructor (private reviewService: ReviewService, private _route: ActivatedRoute) {}
  
  updateReview() {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    this.review.date = new Date(mm + '-' + dd + '-' + yyyy);

    let id = this._route.snapshot.paramMap.get('id');
    let gameId = this._route.snapshot.paramMap.get('gameId');
    if (id !== null && gameId !== null) {
      this.review.id = +id;
      this.reviewService.updateReview(this.review, +gameId);
      window.location.href = '/reviews';
    } else {
      alert('Bad review');
    }
  }
}
