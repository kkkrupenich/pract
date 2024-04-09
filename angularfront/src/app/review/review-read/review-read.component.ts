import { Component } from '@angular/core';
import { Review } from '../../entities/review';
import { ReviewService } from '../../services/review.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-read',
  standalone: true,
  imports: [],
  templateUrl: './review-read.component.html',
  styleUrl: './review-read.component.css'
})
export class ReviewReadComponent {
  reviews: Review[] = [];

  constructor(private reviewService: ReviewService, private _router: Router) {}

  async ngOnInit() {
    this.reviews = await this.reviewService.getReviewsByUserId();
    console.log(this.reviews);
  }

  updateReview(id: number, gameId: number) {
    console.log("update" + id);
    this._router.navigate(['/updatereview', {id: id, gameId: gameId}]);
  }

  async deleteReview(id: number) {
    console.log("delete" + id);
    await this.reviewService.deleteReview(id).then(() => {
      location.reload();
    });
  }
}
