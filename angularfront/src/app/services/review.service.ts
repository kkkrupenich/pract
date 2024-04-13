import { Injectable } from '@angular/core';
import axios from 'axios';
import { Review } from '../entities/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor() { }

  async getReviews() {
    let response = await axios.get(
      "http://localhost:8080/reviews"
    )
    return response.data
  }

  async getReviewsByUserId() {
    let response = await axios.get(
      "http://localhost:8080/myreviews"
    )
    return response.data
  }

  async addReview(review: Review, gameId: number) {
    console.log(review);
    let response = await axios.post(
      "http://localhost:8080/addreview/" + gameId,
      {
          "message": review.message,
          "rating": review.rating,
          "date": review.date
      }
    )
    return response.data
  }

  async updateReview(review: Review) {
    let response = await axios.put(
      "http://localhost:8080/updatereview/" + review.id,
      {
          "message": review.message,
          "rating": review.rating,
          "date": review.date
      }
    )
    console.log(response.data);
    return response.data
  }

  async deleteReview(id: number) {
    let response = await axios.delete(
      "http://localhost:8080/deletereview/" + id
    )
    return response.data
  }
}
