import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReviewService } from '../../services/review.service';
import { Review } from '../../entities/review';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-review-add',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './review-add.component.html',
  styleUrl: './review-add.component.css'
})
export class ReviewAddComponent {
  review: Review = new Review();

  constructor(private reviewService: ReviewService, private _route: ActivatedRoute) {
    console.log();
  }

  addReview() {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    this.review.date = new Date(mm + '-' + dd + '-' + yyyy);

    let id = this._route.snapshot.paramMap.get('id');
    if (id !== null) {
      this.reviewService.addReview(this.review, +id).then(() => {
        window.location.href = '/reviews';
      })
      
    } else {
      alert('Bad review');
    }
  }
}
