import React, { useEffect, useState } from 'react';
import '../App.css';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { deleteCard } from '../services/cardService';


export default function Cards() {
  const [cards, setCards] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('cards')
      .then(response => {
        console.log(response.data);
        setCards(response.data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return (
      <div className="spinner-border">
        <span className="visually-hidden">Loading...</span>
      </div>
    );
  }

  return (
    <div className="App-intro">
      <h2>Cards List</h2>
      <div className="outerTable">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Number</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {cards.map(card =>
              <tr key={card.id}>
                <th scope="row">{card.id}</th>
                <td className="number">{card.number}</td>
                <td className="buttons">
                  <div className="btn-group" aria-label="Basic example">
                    <button type="button" className="btn btn-danger" onClick={() => deleteCardOnClick(card.id)}>
                      Delete card
                    </button>

                    <button type="button" className="btn btn-info" onClick={() => updateCardOnClick(card.id)}>
                      Update card
                    </button>
                  </div></td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
      <div className="button">
        <button type="button" className="btn btn-success" onClick={addCardOnClick}>
          Add card
        </button>
      </div>
    </div >
  );

  async function addCardOnClick() {
    navigate('/addcard');
  }
  async function deleteCardOnClick(id) {
    deleteCard(id)
      .then(cardResponse => {
        console.log(cardResponse);
      });
  }

  async function updateCardOnClick(id) {
    navigate('/updatecard', { state: { id: id } });
  }

}

