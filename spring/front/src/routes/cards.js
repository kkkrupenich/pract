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
      return <p>Loading...</p>;
    }

    return (
      <div className="App-intro">
        <h2>Cards List</h2>
        {cards.map(card =>
          <div key={card.id}>
            {card.id} {card.number}
          </div>
        )}

        <button onClick={addCardOnClick}>
            Add card
        </button>

        <button onClick={deleteCardOnClick}>
            Delete card
        </button>
      </div>
    );

    async function addCardOnClick() {
        navigate('/addcard');
    }
    async function deleteCardOnClick() {
      deleteCard(6);
    }

}

